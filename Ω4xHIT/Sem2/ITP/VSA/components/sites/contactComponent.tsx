"use client";

import React, { useMemo, useState } from "react";
import dynamic from 'next/dynamic';
import { Input, Button, Textarea } from "@nextui-org/react";
import "leaflet/dist/leaflet.css";

const MapComponent = dynamic(() => import('../mapComponent'), { ssr: false });

const ContactComponent = () => {
  const [email, setEmail] = useState("");
  const [value, setValue] = useState("");
  const [showSuccess, setShowSuccess] = useState(false);

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const validateEmail = (value) => {
    const emailRegex = /^[^\W_]+(\.[^\W_]+)*@[^\W_]+(\.[^\W_]+)*\.[^\W_]{2,}$/;
    return emailRegex.test(value);
  };

  const isInvalid = useMemo(() => {
    if (value === "") return false;
    return !validateEmail(value);
  }, [value]);

  const isFormEmpty = () => {
    return !email;
  };

  const handleClick = () => {
    setShowSuccess(true);
  };

  return (
    <div className="min-h-screen flex items-center justify-center ">
      <div className=" max-w-4xl w-full max-h-3xl h-[500px] shadow-md rounded-md bg-opacity-60 bg-whiteVSA dark:bg-grayVSA flex justify-center items-center">
        <div className="w-full md:w-1/2 p-10">
          <div className="">
            <h3 className="font-bold mb-6 text-3xl">Get in Touch</h3>
            <div className="flex flex-col items-start space-y-4 ">
              <div className="flex items-start space-x-2">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 0 0 2.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 0 1-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 0 0-1.091-.852H4.5A2.25 2.25 0 0 0 2.25 4.5v2.25Z"
                  />
                </svg>
                <span>+43 1 33126 - 0</span>
              </div>
              <div className="flex items-start space-x-2">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M21.75 6.75v10.5a2.25 2.25 0 0 1-2.25 2.25h-15a2.25 2.25 0 0 1-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0 0 19.5 4.5h-15a2.25 2.25 0 0 0-2.25 2.25m19.5 0v.243a2.25 2.25 0 0 1-1.07 1.916l-7.5 4.615a2.25 2.25 0 0 1-2.36 0L3.32 8.91a2.25 2.25 0 0 1-1.07-1.916V6.75"
                  />
                </svg>
                <span>info@tgm.ac.at</span>
              </div>
              <div className="flex items-start space-x-2">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M15 10.5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"
                  />
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M19.5 10.5c0 7.142-7.5 11.25-7.5 11.25S4.5 17.642 4.5 10.5a7.5 7.5 0 1 1 15 0Z"
                  />
                </svg>
                <span>Wexstraße 19-23, 1200 Wien</span>
              </div>
            </div>
          </div>
          <form className="flex flex-col " method="post">
            <div className="flex flex-row pt-10">
              <div className="mb-6 flex-1 pr-2">
                <Input
                  isRequired
                  autoFocus
                  label="Name"
                  placeholder="Enter your first name"
                  type="firstname"
                  variant="bordered"
                  color="primary"
                  id="firstname"
                />
              </div>
              <div className="mb-6 flex-1">
                <Input
                  isRequired
                  label="Email"
                  placeholder="Enter your email"
                  variant="bordered"
                  isInvalid={isInvalid}
                  color={isInvalid ? "danger" : "primary"}
                  errorMessage={isInvalid && "Please enter a valid email"}
                  onValueChange={setValue}
                  value={email}
                  onChange={handleEmailChange}
                  type="email"
                  id="email"
                />
              </div>
            </div>
            <div className="mb-6 flex-1">
              <Textarea
                isRequired
                label="Support Request"
                placeholder="Write your message here"
                type="message"
                variant="bordered"
                color="primary"
                id="message"
              />
            </div>
            <div className="flex items-center justify-between">
              <Button
                color="primary"
                isDisabled={isFormEmpty() || isInvalid}
                onClick={handleClick}
              >
                Send
              </Button>
            </div>
          </form>
        </div>
        <div className="w-0 md:w-1/2 z-0">
          <MapComponent />
        </div>
      </div>
    </div>
  );
};

export default ContactComponent;
