import React, { useState } from "react";
import { Input, Link, Button } from "@nextui-org/react";
import { DatePicker } from "@nextui-org/date-picker";
import { COUNTRIES } from './countries';
import CountrySelector from "./countrySelector";
import { SelectMenuOption } from "./countrySelectorTypes";
import { ip } from "../auth/AuthenticationProvider";

const SignUp = () => {
	const [email, setEmail] = useState("");
	const [isSigningUp, setIsSigningUp] = useState(false);
	const [errorMessage, setErrorMessage] = useState("");


	const handleEmailChange = (event) => {
		setEmail(event.target.value);
		setErrorMessage("");
		setErrorMessage("");
	};

	const validateEmail = (value) => {
		const emailRegex = /^[^\W_]+(\.[^\W_]+)*@[^\W_]+(\.[^\W_]+)*\.[^\W_]{2,}$/;
		return emailRegex.test(value);
	};

	const isInvalid = React.useMemo(() => {
		if (email === "") return false;
		return !validateEmail(email);
	}, [email]);

	const [password1, setPassword1] = useState("");
	const [password2, setPassword2] = useState("");

	const handlePassword1Change = (event) => {
		setPassword1(event.target.value);
	};

	const handlePassword2Change = (event) => {
		setPassword2(event.target.value);
	};

	const passwordsMatch = () => {
		return password1 === password2;
	};

	const [selectedDate, setSelectedDate] = useState(null);

	const handleValueChange = (newDate) => {
		setSelectedDate(newDate);
	};

	const toDateString = (rawDate) => {
		var date = new Date(rawDate);
		var mm = date.getMonth() + 1; // getMonth() is zero-based
		var dd = date.getDate();
	  
		return [date.getFullYear(),
				(mm>9 ? '' : '0') + mm,
				(dd>9 ? '' : '0') + dd
			   ].join('-');
	  };

	const calculateAge = (birthday) => {
		const today = new Date();
		const birthDate = new Date(birthday);
		let age = today.getFullYear() - birthDate.getFullYear();
		const monthDiff = today.getMonth() - birthDate.getMonth();
		if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
			age--;
		}
		return age;
	};

	const checkAgeFrontend = () => {
		if (!selectedDate) return true;
		const age = calculateAge(selectedDate);
		console.log(age);
		return age >= 16;
	};

	const checkAge = () => {
		if (!selectedDate) return false;
		const age = calculateAge(selectedDate);
		console.log(age);
		return age >= 16;
	};

	const [firstname, setFirstname] = useState("");
	const handleFirstnameChange = (event) => {
		setFirstname(event.target.value);
	};

	const [lastname, setLastname] = useState("");
	const handleLastnameChange = (event) => {
		setLastname(event.target.value);
	};

	const isFormEmpty = () => {
		return (
			!email ||
			!password1 ||
			!password2 ||
			!password1 ||
			!password2 ||
			!email ||
			!firstname ||
			!lastname ||
			!selectedDate
		);
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
	

	const sendSignUpRequest = () => {
		if (isSigningUp) return;
		if (isFormEmpty()) return;
		if (!checkAge()) return;
		if (!isValidPasswordRegex()) return;
		if (!passwordsMatch) return;
		if (!selectedDate) return;

		setIsSigningUp(true);

		const url = 'http://' + ip + '/auth/register';

		const params = {
			email: email,
			password: password1,
			birthdate: toDateString(selectedDate),
			name: firstname + " " + lastname,
			country: country,
		};
		const headers = { 'Content-Type': 'application/json' };
		const options = {
			method: 'POST',
			body: JSON.stringify(params),
			headers: headers
		};

		fetch(url, options)
			.then(response => response.json())
			.then(data => {
				let token = data.token;
				console.log(token);

				localStorage.setItem("token", token);

				window.location.href = "/";
			})
			.catch(error => {
				console.error(error);
				setErrorMessage("User with this Email already exists.");
				setIsSigningUp(false);
			});
	}

	const [isOpen, setIsOpen] = useState(false);
	// Default this to a country's code to preselect it
	const [country, setCountry] = useState('AT');

	return (
		<div>
			<div className="min-h-screen flex items-center justify-center">
				<div className="max-w-4xl w-full max-h-3xl h-[700px] p-9 shadow-md rounded-md bg-opacity-60 bg-whiteVSA dark:bg-grayVSA flex justify-center items-center">
					<div className="w-full md:w-1/2 ">
						<h3 className="font-bold mb-6 text-3xl">Sign Up</h3>
						<form className="flex flex-col " method="post">
							<div className="flex flex-row">
								<div className="mb-6 flex-1 pr-2">
									<Input
										isRequired
										autoFocus
										label="First Name"
										placeholder="Enter your first name"
										type="firstname"
										variant="bordered"
										color="primary"
										id="firstname"
										onChange={handleFirstnameChange}
									/>
								</div>
								<div className="mb-6 flex-1">
									<Input
										isRequired
										label="Last Name"
										placeholder="Enter your last name"
										type="lastname"
										variant="bordered"
										color="primary"
										id="lastname"
										onChange={handleLastnameChange}
									/>
								</div>
							</div>

							<div className="mb-6 flex-1">
								<CountrySelector
									id={'countries'}
									open={isOpen}
									onToggle={() => setIsOpen(!isOpen)}
									onChange={val => setCountry(val)}
									selectedValue={COUNTRIES.find(option => option.value === country) as SelectMenuOption}
								/>
							</div>

							<div className="mb-4 flex flex-col">
								<Input
									isRequired
									label="Email"
									placeholder="Enter your email"
									variant="bordered"
									isInvalid={isInvalid}
									color={isInvalid ? "danger" : "primary"}
									errorMessage={isInvalid && "Please enter a valid email" || errorMessage }
									onValueChange={setEmail}
									value={email}
									onChange={handleEmailChange}
									type="email"
									id="email"
								/>
							</div>
							<div className="mb-6 flex flex-col">
								<Input
									isRequired
									label="Password"
									placeholder="Enter your password"
									type="password"
									variant="bordered"
									id="password"
									onChange={handlePassword1Change}
									onBlur={handlePassword1Change}
									isInvalid={!isValidPassword1ForFrontend()}
									color={!isValidPassword1ForFrontend() ? "danger" : "primary"}
									errorMessage={
										!isValidPassword1ForFrontend() && "Please enter a valid password."
									}
								/>
							</div>
							<div className="mb-6 flex flex-col">
								<Input
									isRequired
									label="Confirm your Password"
									placeholder="Enter your password"
									type="password"
									variant="bordered"
									id="confirmPassword"
									onChange={handlePassword2Change}
									onBlur={handlePassword2Change}
									isInvalid={!isValidPassword2ForFrontend()}
									color={!isValidPassword2ForFrontend() ? "danger" : "primary"}
									errorMessage={
										!isValidPassword2ForFrontend() && "Passwords must match."
									}
								/>
							</div>

							<div className=" flex flex-col">
								<DatePicker
									label="Select your Birth Date"
									variant="bordered"
									showMonthAndYearPickers
									onChange={handleValueChange}
									isInvalid={!checkAgeFrontend()}
									color={!checkAgeFrontend() ? "danger" : "primary"}
									errorMessage={!checkAgeFrontend() && "Please enter a valid age."}
								/>
							</div>

							<div className="flex py-2 px-1 justify-between">
								<Link color="primary" href="./signin" size="sm">
									Already have an account?
								</Link>
							</div>
							<div className="flex items-center justify-between">
								<Button
									color="primary"
									isDisabled={
										!checkAge() ||
										isFormEmpty() ||
										!passwordsMatch() ||
										isInvalid ||
										isSigningUp
									}
									onClick={sendSignUpRequest}
								>
									Sign up
								</Button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	);
};

export default SignUp;

