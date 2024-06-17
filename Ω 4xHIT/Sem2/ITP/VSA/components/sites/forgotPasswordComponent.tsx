"use client";

import React, { useState } from "react";
import { Input, Link, Button } from "@nextui-org/react";
import { ip } from "../auth/AuthenticationProvider";

const ForgotPasswordComponent = () => {
  const [email, setEmail] = useState("");
  const [value, setValue] = React.useState("");
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const validateEmail = (value) => {
    const emailRegex = /^[^\W_]+(\.[^\W_]+)*@[^\W_]+(\.[^\W_]+)*\.[^\W_]{2,}$/;
    return emailRegex.test(value);
  };

  const isInvalid = React.useMemo(() => {
    if (value === "") return false;
    return !validateEmail(value);
  }, [value]);

  const isFormEmpty = () => {
    return !email;
  };

  const [showSuccess, setShowSuccess] = useState(false);

  async function postForgotPassword() {

		const url = 'http://' + ip + '/auth/forgotpassword';

		const headers = {
      'Content-Type': 'application/json'
    };

		const params = {
			email: email,
		}

		const options = {
			method: 'POST',
			headers: headers,
			body: JSON.stringify(params),
		};

		return fetch(url, options)
			.then(response => {
        alert(response.status)
        if(response.status == 200) {
          setShowSuccess(true);
          return response.json();
        }else {
          setShowSuccess(false);
        }

			}).catch(error => {
        setShowSuccess(false);
				console.error(error);
			});
	}

  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="max-w-4xl w-full max-h-3xl h-[500px] shadow-md rounded-md bg-opacity-60 bg-whiteVSA dark:bg-grayVSA flex justify-center items-center">
        <div className="w-full md:w-1/2 ">
          <div className="pl-1">
            <h3 className="font-bold mb-6 text-3xl">Forgot your Password?</h3>
            <p className="text-lg pb-3">
              Please enter your Email address
            </p>
          </div>
          <form className="flex flex-col " method="post">
            <div className="mb-4 flex-1 pr-2">
              <Input
                isRequired
                autoFocus
                label="Email"
                placeholder="Enter your email"
                variant="bordered"
                isInvalid={isInvalid}
                color={
                  showSuccess && !isFormEmpty()
                    ? "success"
                    : isInvalid
                    ? "danger"
                    : "primary"
                }
                errorMessage={isInvalid && "Please enter a valid email"}
                onValueChange={setValue}
                value={email}
                onChange={handleEmailChange}
                type="email"
                id="email"
              />
              {showSuccess && !isInvalid && !isFormEmpty() && (
                <p className="successMessage">
                  E-Mail zum Zurücksetzen des Passworts wurde gesendet!
                </p>
              )}
            </div>
            <div className="flex items-center justify-between">
              <Button
                color="primary"
                isDisabled={isFormEmpty() || isInvalid}
                onClick={postForgotPassword}
              >
                Request reset Link
              </Button>
            </div>
            <div className="flex py-2 px-1 justify-between">
                <Link color="primary" href="./signin" size="sm">
                  Back to Login
                </Link>
              </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ForgotPasswordComponent;
