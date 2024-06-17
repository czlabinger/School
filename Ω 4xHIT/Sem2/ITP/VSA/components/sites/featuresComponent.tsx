"use client";

import { FaCheck } from "react-icons/fa";
import { getCustomerPortalLink, useAuthContext } from "../auth/AuthenticationProvider";
import { Button } from "@nextui-org/react";
import { HiMiniXMark } from "react-icons/hi2";

const FeaturesComponent = () => {

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
			<h1 className="text-5xl">Compare our pricing plans</h1>
			<p className="mt-[1rem] text-xl pb-20">
				Lorem Ipsum Lorem Ipsum Lorem Ipsum
			</p>

			<div className="w-2/3 hidden lg:block mx-auto relative overflow-x-auto shadow-md sm:rounded-lg">
				<table className="w-full text-md text-truegray-500 dark:text-white">
					<thead className="text-xl text-truegray-500 uppercase bg-gray-50 dark:bg-[#222] dark:text-truegray-400 text-center">
						<tr>
							<th scope="col" className="px-6 py-3">
								Features
							</th>
							<th scope="col" className="px-6 py-3">
								Basic
							</th>
							<th scope="col" className="px-6 py-3">
								Advanced
							</th>
							<th scope="col" className="px-6 py-3">
								Premium
							</th>
						</tr>
					</thead>
					<tbody>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 1
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<HiMiniXMark
										size={30}
										className={`text-black dark:text-white`}
									/>
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<HiMiniXMark
										size={30}
										className={`text-black dark:text-white`}
									/>
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 2
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<HiMiniXMark
										size={30}
										className={`text-black dark:text-white`}
									/>
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<HiMiniXMark
										size={30}
										className={`text-black dark:text-white`}
									/>
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 3
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<HiMiniXMark
										size={30}
										className={`text-black dark:text-white`}
									/>
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 4
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 5
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<th
								scope="row"
								className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white text-center"
							>
								Feature 6
							</th>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
							<td className="px-6 py-4">
								<div className="flex items-center justify-center">
									<FaCheck size={20} className={`text-black dark:text-white`} />
								</div>
							</td>
						</tr>
						<tr className="odd:bg-white odd:dark:bg-trueGray-900 even:bg-gray-50 even:dark:bg-trueGray-800">
							<td className="py-10"></td>
							<td className="py-10">
								<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
							</td>
							<td className="py-10">
								<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
							</td>
							<td className="py-10">
								<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
									{!isLoggedIn && <p>Get Started</p>}
									{isLoggedIn && <p>Manage</p>}
								</Button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div className="w-2/3 block lg:hidden mx-auto relative overflow-x-auto shadow-md sm:rounded-lg">
				<div className="mt-[1rem] grid grid-cols-1 xl:grid-cols-3 w-full">
					<div className="flex justify-center items-center flex-col">
						<div className="flex justify-center items-center bg-gray-100 mb-10 rounded-md dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit w-4/5 flex-col">
							<h2 className="mt-10 text-3xl font-lg">Basic</h2>
							<p className="mt-5">minimal Lorem ipsum text</p>

							<ul className="pt-8">
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 1</p>
								</li>
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 2</p>
								</li>
							</ul>
							<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
								{!isLoggedIn && <p>Get Started</p>}
								{isLoggedIn && <p>Manage</p>}
							</Button>
							<div className="pb-[5rem]" />
						</div>
						<div className="flex justify-center items-center bg-gray-100 mb-10 rounded-md dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit w-4/5 flex-col">
							<h2 className="mt-10 text-3xl font-lg">Advanced</h2>
							<p className="mt-5">minimal Lorem ipsum text</p>

							<ul className="pt-8">
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 1</p>
								</li>
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 2</p>
								</li>
							</ul>
							<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
								{!isLoggedIn && <p>Get Started</p>}
								{isLoggedIn && <p>Manage</p>}
							</Button>
							<div className="pb-[5rem]" />
						</div>
						<div className="flex justify-center items-center bg-gray-100 rounded-md mb-10 dark:bg-[#222] hover:bg-gray-200 hover:dark:bg-[#333] min-w-fit w-4/5 flex-col">
							<h2 className="mt-10 text-3xl font-lg">Premium</h2>
							<p className="mt-5">minimal Lorem ipsum text</p>

							<ul className="pt-8">
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 1</p>
								</li>
								<li className="flex items-center p-2">
									<FaCheck
										size={15}
										className={`text-black dark:text-white mr-2`}
									/>
									<p className="text-lg">Feature 2</p>
								</li>
							</ul>
							<Button onClick={sendButtonPress} className="px-8 py-4 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
								{!isLoggedIn && <p>Get Started</p>}
								{isLoggedIn && <p>Manage</p>}
							</Button>
							<div className="pb-[5rem]" />
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default FeaturesComponent;