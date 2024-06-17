"use client";

import { getCustomerPortalLink, ip, useAuthContext } from "../auth/AuthenticationProvider";
import { Button } from "@nextui-org/react";

const PricingComponent = () => {

	let isLoggedIn = useAuthContext();

	function sendButtonPress() {
		if (!isLoggedIn) {
			window.location.href = "/signin";
			return;
		}

		getCustomerPortalLink();
	}


	return (
		<div className="mt-[5rem] text-center justify-center items-center">
			<h1 className="text-5xl p-15">Choose a plan that's right for you</h1>
			<p className="mt-[1rem] text-xl">Lorem Ipsum Lorem Ipsum Lorem Ipsum</p>

			<div className="flex justify-center items-center w-full">
				<div className="flex justify-center items-center md:w-4/5 w-full">
					<div className="mt-[5rem] grid grid-cols-1 xl:grid-cols-3 gap-[1rem] w-full">
						<div className="flex justify-center items-center">
							<div className="flex justify-center items-center bg-gray-100 rounded-md dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit w-4/5 flex-col">
								<h2 className="mt-10 text-3xl font-lg">Basic</h2>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<div className="flex mt-10">
									<h2 className="text-3xl font-semibold">Free</h2>
									<p className="mt-3 font-bold">/month</p>
								</div>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<Button onClick={sendButtonPress} className="mt-10 px-8 py-6 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
								<p className="mt-10 pb-[5rem]">minimal Lorem Ipsum test</p>
							</div>
						</div>
						<div className="flex justify-center items-center">
							<div className="flex justify-center items-center bg-gray-100 rounded-md dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit w-4/5 flex-col">
								<h2 className="mt-10 text-3xl font-lg">Advanced</h2>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<div className="flex mt-10">
									<h2 className="text-3xl font-semibold">Free</h2>
									<p className="mt-3 font-bold">/month</p>
								</div>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<Button onClick={sendButtonPress} className="mt-10 px-8 py-6 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
								<p className="mt-10 pb-[5rem]">minimal Lorem Ipsum test</p>
							</div>
						</div>
						<div className="flex justify-center items-center">
							<div className="flex justify-center items-center bg-gray-100 rounded-md dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit	 w-4/5 flex-col">
								<h2 className="mt-10 text-3xl font-lg">Premium</h2>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<div className="flex mt-10">
									<h2 className="text-3xl font-semibold">Free</h2>
									<p className="mt-3 font-bold">/month</p>
								</div>
								<p className="mt-5">minimal Lorem ipsum text</p>
								<Button onClick={sendButtonPress} className="mt-10 px-8 py-6 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
								<p className="mt-10 pb-[5rem]">minimal Lorem Ipsum test</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default PricingComponent;