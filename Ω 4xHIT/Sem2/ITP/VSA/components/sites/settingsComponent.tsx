'use client';

import { Button, Input } from "@nextui-org/react";
import { ip } from "../auth/AuthenticationProvider";
import { useEffect, useState } from "react";
import IntervalSelector from "../intervalSelector";
import { INTERVALS } from "../intervals";
import { SelectMenuOption } from "../intervalSelectorTypes";

const SettingsComponent = () => {


	const [password1, setPassword1] = useState("");
	const [password2, setPassword2] = useState("");
	const [intervalData, setIntervalData] = useState("");
	const [interval, setInterval] = useState("days");
	const [name, setName] = useState("");
	const [birthdate, setBirthdate] = useState("");
	const [email, setEmail] = useState("");
	const [country, setCountry] = useState("");

	const [isOpen, setIsOpen] = useState(false);

	const [customerPortalLoading, setCustomerPortalLoading] = useState(false);

	const [showSuccessPasswordChange, setShowSuccessPasswordChange] = useState(false);

	const handleIntervalDataChange = (event: any) => {
		setIntervalData(event.target.value)
	}

	const handleFirstPasswordChange = (event: any) => {
		setPassword1(event.target.value)
		setShowSuccessPasswordChange(false);
	}

	const handleSecondPasswordChange = (event: any) => {
		setPassword2(event.target.value);
		setShowSuccessPasswordChange(false);
	};

	const passwordsMatch = () => {
		return password1 === password2;
	};

	function isValidPasswordRegex() {
		const regex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\-_=\+\\|[\]{};:'",<.>\/?]).{8,}$/;
		return regex.test(password1);
	}

	function isValidPassword1ForFrontend() {
		if (password1 == "") {
			return true;
		}
		return isValidPasswordRegex();
	}

	function isValidPassword2ForFrontend() {
		if (password2 == "") {
			return true;
		}
		return passwordsMatch();
	}

	function clearPasswordFields() {
		setPassword1("");
		setPassword2("");
	}

	async function fetchUserData() {
		const url = 'http://' + ip + '/auth/getuserdata';
		const headers = { 'Authorization': 'Bearer ' + localStorage.getItem("token") };
		const options = {
			method: 'GET',
			headers: headers,
		};

		return fetch(url, options)
			.then(response => {
				return response.json();
			}).catch(error => {
				console.error(error);
			});
	}

	async function forwardToCustomerPortal() {

		if(customerPortalLoading) return;
		setCustomerPortalLoading(true);

		const url = 'http://' + ip + '/payment/customerportal';
		const headers = { 'Authorization': 'Bearer ' + localStorage.getItem("token") };
		const options = {
			method: 'GET',
			headers: headers,
		};

		return fetch(url, options)
			.then(response => response.json())
			.then(data => {
				window.location.href = data.customerportalurl;
			})
			.catch(error => {
				console.error(error);
			});
	}

	async function sendChangePasswordRequest() {
		if (!isValidPasswordRegex()) return;
		if (!passwordsMatch()) return;

		const url = 'http://' + ip + '/auth/changepassword';

		const headers = {
			'Authorization': 'Bearer ' + localStorage.getItem("token"),
			'Content-Type': 'application/json'
		};

		const params = {
			password: password1,
		}

		clearPasswordFields();

		const options = {
			method: 'POST',
			headers: headers,
			body: JSON.stringify(params),
		};

		fetch(url, options)
			.then(response => {
				if (response.status == 200) {
					setShowSuccessPasswordChange(true);
				} else {
					setShowSuccessPasswordChange(false);
				}
			})
			.catch(error => {
				console.error(error);
				setShowSuccessPasswordChange(false);
			});
	}

	async function sendChangeIntervalRequest() {
		const url = 'http://' + ip + '/auth/changeinterval';

		const headers = {
			'Authorization': 'Bearer ' + localStorage.getItem("token"),
			'Content-Type': 'application/json'
		};

		let intervalDays = 0;
		if (interval == "") return;

		if (interval == "days") {
			intervalDays = parseInt(intervalData);
		} else if (interval == "weeks") {
			intervalDays = parseInt(intervalData) * 7;
		} else {
			intervalDays = parseInt(intervalData) * 31;
		}

		const params = {
			interval: intervalDays,
		}

		const options = {
			method: 'POST',
			headers: headers,
			body: JSON.stringify(params),
		};

		fetch(url, options).then((data) => {
			return data.json();
		}).catch((error) => {
			console.error(error);
		});
	}

	useEffect(() => {
		fetchUserData().then(data => {
			setName(data.name);
			setBirthdate(data.birthDate);
			setEmail(data.email);
			setCountry(data.country);
			setIntervalData(data.interval);
		});
	}, []);


	return (
		<div className="min-h-screen flex justify-center w-full text-center">
			<div className="mt-[5rem] w-5/6 md:w-1/2">
				<h1 className="text-4xl">Settings</h1>

				<div className="mt-[3rem]">
					<h2 className="text-2xl">Your Account</h2>
					<div className="flex flex-row mt-[1rem]">
						<Input label="Name" placeholder="Name" value={name} disabled />
					</div>
					<div className="flex flex-row mt-[1rem]">
						<Input label="Birthdate" className="w-1/2 mr-[1rem]" placeholder="Birthdate" value={birthdate} disabled />
						<Input label="Country" className="w-1/2 ml-[1rem]" placeholder="Country" value={country} disabled />
					</div>

					<div className="mt-[1rem]">
						<Input label="Email address" placeholder="Email address" value={email} disabled />
					</div>

					<Button
						className="mt-[1rem] bg-indigo-600 text-white"
						onClick={forwardToCustomerPortal}
						isDisabled={customerPortalLoading}
					>
						Manage Subscription
					</Button>
				</div>

				<div className="mt-[3rem]">
					<h2 className="text-2xl">Change Password</h2>
					<Input type="password" className="mt-[1rem]" placeholder="New Password" value={password1 || ''} isInvalid={!isValidPassword1ForFrontend()}
						color={!isValidPassword1ForFrontend() ? "danger" : "default"} onChange={handleFirstPasswordChange}
						errorMessage={
							!isValidPassword1ForFrontend() && "Please enter a valid password."
						}
					/>
					<Input type="password" className="mt-[1rem]" placeholder="Confirm New Password" value={password2 || ''} isInvalid={!isValidPassword2ForFrontend()}
						color={!isValidPassword2ForFrontend() ? "danger" : "default"} onChange={handleSecondPasswordChange}
						errorMessage={
							!isValidPassword2ForFrontend() && "Passwords must match."
						}
					/>
					{showSuccessPasswordChange && (
						<p className="successMessage">
							Ihr Passwort wurde erfolgreich geändert!
						</p>
					)}
					<Button
						className="mt-[1rem] bg-indigo-600 text-white"
						type="submit"
						onClick={sendChangePasswordRequest}
						isDisabled={!isValidPasswordRegex() || !passwordsMatch()}
					>
						Change Password
					</Button>
				</div>

				<form className="mt-[3rem] flex flex-col">
					<h2 className="text-2xl ">Interval Settings</h2>

					<div className="mt-[3rem] justify-center">

						<div className="flex flex-row justify-center">
							<p className="mt-[1rem] mr-[2rem]"> Set your time interval </p>
							<Input
								label="Interval"
								type="number"
								placeholder="3"
								className="w-1/4 mr-[2rem]"
								value={intervalData || ''}
								onChange={handleIntervalDataChange}
							/>

							<div className="w-[10rem]">
								<IntervalSelector
									id={'intervals'}
									open={isOpen}
									onToggle={() => setIsOpen(!isOpen)}
									onChange={val => setInterval(val)}
									selectedValue={INTERVALS.find(option => option.value === interval) as SelectMenuOption}
								/>
							</div>
						</div>

						<Button className="mt-[1rem] bg-indigo-600 text-white" type="submit" onClick={sendChangeIntervalRequest}>Update Interval</Button>

					</div>
				</form>
			</div>
		</div>
	);
}

export default SettingsComponent;
