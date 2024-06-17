import React, { useState } from "react";
import { Checkbox, Input, Link, Button } from "@nextui-org/react";
import { ip, pushHome } from '../auth/AuthenticationProvider';

const SignIn = () => {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [isLoggingIn, setLoggingIn] = useState(false);
	const [errorMessage, setErrorMessage] = useState("");

	const handleEmailChange = (event) => {
		setEmail(event.target.value);
	};
	const handlePasswordChange = (event) => {
		setPassword(event.target.value);
	};

	const validateEmail = (value) => {
		const emailRegex = /^[^\W_]+(\.[^\W_]+)*@[^\W_]+(\.[^\W_]+)*\.[^\W_]{2,}$/;
		return emailRegex.test(value);
	};

	const isInvalid = React.useMemo(() => {
		if (email === "") return false;
		return !validateEmail(email);
	}, [email]);

	const isFormEmpty = () => {
		return !email || !password;
	};

	const sendSignInRequest = () => {
		if(isLoggingIn) return;
		if(isFormEmpty()) return;

		setLoggingIn(true);

		const url = 'http://' + ip + '/auth/authenticate';
	
		const params = {
		  email: email,
		  password: password,
		};
		const headers = { 'Content-Type': 'application/json' };
		const options = {
		  method: 'POST',
		  body: JSON.stringify( params ),
		  headers: headers
		};
	
		fetch(url, options)
		  .then(response => response.json())
		  .then(data => {
			let token = data.token;
			localStorage.setItem("token", token);
	
			pushHome();
			setLoggingIn(false);
		  })
		  .catch(error => {
			setErrorMessage("Email or password is wrong. Try again or click Forgot password to reset it.");
			
			alert(error);

			setLoggingIn(false);
		  });
	}

	return (
		<div>
			<div className="min-h-screen flex items-center justify-center">
				<div className="max-w-4xl w-full max-h-3xl h-[500px] p-9 shadow-md rounded-md bg-opacity-60 bg-whiteVSA dark:bg-grayVSA flex justify-center items-center">
					<div className="w-full md:w-1/2 ">

						<h3 className="font-bold mb-6 text-3xl">
							Sign In
						</h3>

						<div className="flex py-2 px-1">
							New User?
							<Link color="primary" href="./signup" className="ps-1">
								Create an account
							</Link>
						</div>

						<form className="flex flex-col " method="post">
							<div
								className="mb-4 flex flex-col"
								style={{ paddingTop: "10px" }}
							>
								<Input
									autoFocus
									label="Email"
									placeholder="Enter your email"
									variant="bordered"
									isInvalid={isInvalid}
									color={isInvalid ? "danger" : "primary"}
									errorMessage={isInvalid && "Please enter a valid email"}
									value={email}
									onChange={handleEmailChange}
									type="email"
									id="email"
								/>
							</div>
							<div className=" flex flex-col">
								<Input
									label="Password"
									placeholder="Enter your password"
									variant="bordered"
									type="password"
									color="primary"
									id="password"
									errorMessage={errorMessage}
									onChange={handlePasswordChange}
								/>
							</div>

							<div
								className="flex py-2 px-1 justify-between"
								style={{ paddingTop: "10px" }}
							>
								<Checkbox
									classNames={{
										label: "text-small",
									}}
								>
									Remember me
								</Checkbox>

								<Link color="primary" href="/forgotpassword" size="sm" className="ps-1">
									Forgot password?
								</Link>
							</div>
							<div
								className="flex items-center justify-between"
								style={{ paddingTop: "10px" }}
							>
								<Button
									color="primary"
									isDisabled={isInvalid || isFormEmpty() || isLoggingIn}
									onClick={sendSignInRequest}
								>
									Sign in
								</Button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	);
};

export default SignIn;