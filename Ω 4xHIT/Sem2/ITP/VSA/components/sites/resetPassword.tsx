"use client";
import React, { useState } from "react";
import { Input, Button } from "@nextui-org/react";
import { useSearchParams } from 'next/navigation';
import { pushHome } from "../auth/AuthenticationProvider";
import { ip } from "../auth/AuthenticationProvider";

const ResetPasswordComponent = () => {

  const searchParams = useSearchParams();

  const email = searchParams?.get('email')
  const token = searchParams?.get('token')

  if(email == null) {
    pushHome();
    return <></>;
  }

  const [showSuccess, setShowSuccess] = useState(false);
  const handleClick = () => {
      setShowSuccess(true);
      setIsDisabled(true);
  };

  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handlePasswordChange = (event) => {
      setPassword(event.target.value);
      checkPasswordMatch();
  };

  const handleConfirmPasswordChange = (event) => {
      setConfirmPassword(event.target.value);
      checkPasswordMatch();
  };

  const checkPasswordMatch = () => {
      if (password !== confirmPassword) {
          return true
      } else {
          return false
      }

  };

  const [isDisabled, setIsDisabled] = useState(false);

  const isFormEmpty = () => {
      return !password || !confirmPassword;
  };

  async function postResetPassword() {

		const url = 'http://' + ip + '/auth/resetpassword';

		const headers = {
      'Content-Type': 'application/json'
    };

		const params = {
			token: token,
      email: email,
      newPassword: password,
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
            <h3 className="font-bold mb-6 text-3xl">Set your new Password for {email} !</h3>
            <p className="text-lg font-semibold pb-3">
              Please enter your new Password below!
            </p>
          </div>
          <form className="flex flex-col " method="post">
            <div className="mb-4 flex-1 pr-2">
              <Input
                isRequired
                disabled={isDisabled}
                label="Password"
                placeholder="Enter your password"
                type="password"
                variant="bordered"
                id="password"
                onChange={handlePasswordChange}
                onBlur={handlePasswordChange}
                isInvalid={checkPasswordMatch()}
                color={
                  showSuccess && !isFormEmpty()
                    ? "success"
                    : checkPasswordMatch()
                    ? "danger"
                    : "primary"
                }
              />
            </div>
            <div className="mb-4 flex-1 pr-2">
              <Input
                isRequired
                disabled={isDisabled}
                label="Confirm your Password"
                placeholder="Enter your password"
                type="password"
                variant="bordered"
                id="confirmPassword"
                isInvalid={checkPasswordMatch()}
                color={
                  showSuccess && !isFormEmpty()
                    ? "success"
                    : checkPasswordMatch()
                    ? "danger"
                    : "primary"
                }
                errorMessage={
                  checkPasswordMatch() && "Please enter a valid password"
                }
                onChange={handleConfirmPasswordChange}
                onBlur={handleConfirmPasswordChange}
              />
              {showSuccess && !isFormEmpty() && !checkPasswordMatch() && (
                <p className="pl-1 pt-1 text-green-500 text-xs">
                  Ihr neues Passwort wurde gesetzt!
                </p>
              )}
            </div>
            <div className="flex items-center justify-between">
              <Button
                color="primary"
                isDisabled={
                  isFormEmpty() || checkPasswordMatch() || showSuccess
                }
                onClick={postResetPassword}
              >
                Set new Password
              </Button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ResetPasswordComponent;
