"use client";

import { useEffect, useState, createContext, useContext } from "react";

export let AuthContext = createContext(false);
// export const ip = "10.0.105.159:8080";
export const ip = "127.0.0.1:8080";

const AuthenticationProvider = ({
		onlyViewWhenLoggedIn,
		onlyViewWhenNotLoggedIn,
		executeWhenNotPermitted,
		children,
  	}: {
		onlyViewWhenLoggedIn?: boolean,
		onlyViewWhenNotLoggedIn?: boolean,
		executeWhenNotPermitted?: Function,
		children: React.ReactNode
  	}) => {

	const [signingIn, setSigningIn] = useState(false);
	const [loggedIn, setLoggedIn] = useState(false);

	const checkLogIn = () => {
		let jsonToken = localStorage.getItem("token");

		const url = 'http://' + ip + '/auth/checklogin';
		const headers = { 'Authorization': 'Bearer ' + jsonToken };

		setSigningIn(true);

		const options = {
			headers: headers,
		};

		fetchWithTimeout(url, options, 500)
			.then(response => {
				console.log(response);
				if(response.status == 200) {
					setLoggedIn(true);
					if(onlyViewWhenNotLoggedIn && executeWhenNotPermitted) {
						executeWhenNotPermitted();
					}
				}else {
					setLoggedIn(false);
					if(onlyViewWhenLoggedIn && executeWhenNotPermitted) {
						executeWhenNotPermitted();
					}
				}
				setSigningIn(false);
			})
			.catch(error => {
				console.error(error);
				setLoggedIn(false);
				setSigningIn(false);
				if(onlyViewWhenLoggedIn && executeWhenNotPermitted) {
					executeWhenNotPermitted();
				}
			});
	}

	useEffect(() => {
		checkLogIn();
	}, []);

	if(signingIn) {
		console.log("Establishing Connection with Backend");
		return (<AuthContext.Provider value={loggedIn}></AuthContext.Provider>);
	}

	
	if (typeof onlyViewWhenLoggedIn !== 'undefined') {
		if(loggedIn && onlyViewWhenLoggedIn) {
			return (<AuthContext.Provider value={loggedIn}>{children}</AuthContext.Provider>);
		}
		if(!loggedIn && onlyViewWhenLoggedIn) {
			return (<AuthContext.Provider value={loggedIn}></AuthContext.Provider>);
		}
	}
	if (typeof onlyViewWhenNotLoggedIn !== 'undefined') {
		if(loggedIn && onlyViewWhenNotLoggedIn) {
			return (<AuthContext.Provider value={loggedIn}></AuthContext.Provider>);
		}
		if(!loggedIn && onlyViewWhenNotLoggedIn) {
			return (<AuthContext.Provider value={loggedIn}>{children}</AuthContext.Provider>);
		}
	}

	return (<AuthContext.Provider value={loggedIn}>{children}</AuthContext.Provider>);
}

export function useAuthContext() {
	return useContext(AuthContext);
}

export function pushHome() {
	window.location.href = "/";
}

export async function fetchWithTimeout(url, options, timeout = 7000): Promise<Response> {
	const controller = new AbortController();
    const id = setTimeout(() => controller.abort(), timeout);

	const response = await fetch(url, {
        ...options,
        signal: controller.signal
    });
    
    clearTimeout(id);
    
    return response;
}

export async function getCustomerPortalLink() {
	const url = 'http://' + ip + '/payment/customerportal';

	const headers = {
		'Authorization': 'Bearer ' + localStorage.getItem("token"),
		'Content-Type': 'application/json'
	};

	const options = {
		method: 'GET',
		headers: headers,
	};

	fetch(url, options)
		.then(response => {
			if (response.status == 200) {
				return response.json();
			}
		})
		.then(data => {
			console.log(data);
			window.location.href = data.customerportalurl;

		})
		.catch(error => {
			console.error(error);
			alert(error);
		});
}

export default AuthenticationProvider;
