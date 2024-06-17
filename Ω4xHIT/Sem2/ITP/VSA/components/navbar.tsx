import Link from "next/link";
import ThemeChanger from "./DarkSwitch";
import Image from "next/image"
import { Disclosure } from "@headlessui/react";
import { navigation } from "../data/navigation"
import { useAuthContext } from "./auth/AuthenticationProvider";
import { CiSettings } from "react-icons/ci";

const Navbar = () => {

	let isLoggedIn = useAuthContext();

	function signOut() {
		localStorage.removeItem("token");
		window.location.href = "/";
	}

	return (
		<div className="w-full">
			<nav className="container relative flex flex-wrap items-center justify-between p-8 mx-auto lg:justify-between xl:px-0">

				{/* Mobile Hamburger Menu */}
				<Disclosure>
					{({ open }) => (
						<>
							<div className="flex flex-wrap items-center justify-between w-full lg:w-auto">
								<Link href="/">
									<span className="flex items-center space-x-2 text-2xl font-medium text-indigo-500 dark:text-gray-100">
										<span className="hidden dark:block">
											<Image
												src="/img/logo_weis.png"
												alt="Logo"
												width="100"
												height="100"
											/>
										</span>
										<span className="block dark:hidden">
											<Image
												src="/img/logo_dark.png"
												alt="Logo"
												width="100"
												height="100"
											/>
										</span>
									</span>
								</Link>

								<div className="flex flex-row block lg:hidden">
									<ThemeChanger />
									<Disclosure.Button
										aria-label="Toggle Menu"
										className="ml-2 px-2 py-1 ml-auto text-gray-500 rounded-md lg:hidden hover:text-indigo-500 focus:text-indigo-500 focus:bg-indigo-100 focus:outline-none dark:text-gray-300 dark:focus:bg-trueGray-700">
										<svg
											className="w-6 h-6 fill-current"
											xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 24 24">
											{open && (
												<path
													fillRule="evenodd"
													clipRule="evenodd"
													d="M18.278 16.864a1 1 0 0 1-1.414 1.414l-4.829-4.828-4.828 4.828a1 1 0 0 1-1.414-1.414l4.828-4.829-4.828-4.828a1 1 0 0 1 1.414-1.414l4.829 4.828 4.828-4.828a1 1 0 1 1 1.414 1.414l-4.828 4.829 4.828 4.828z"
												/>
											)}
											{!open && (
												<path
													fillRule="evenodd"
													d="M4 5h16a1 1 0 0 1 0 2H4a1 1 0 1 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2z"
												/>
											)}
										</svg>
									</Disclosure.Button>
								</div>

								<Disclosure.Panel className="flex flex-wrap w-full my-5 lg:hidden">
									<>
										{navigation.map((item, index) => (
											<>
												{(!item.showOnlyLoggedIn || isLoggedIn) &&
													<Link key={index} href={item.link} className="w-full px-4 py-4 text-gray-500 rounded-md dark:text-gray-300 hover:text-indigo-500 focus:text-indigo-500 focus:bg-indigo-100 dark:focus:bg-gray-800 focus:outline-none">
														{item.name}
													</Link>
												}
											</>
										))}

										{isLoggedIn &&
											<div className="mt-3 flex flex-row w-full">
												<Link href="" onClick={signOut} className="w-11/12 px-6 py-2 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md">
													Sign Out
												</Link>
												<a className="inline-block pl-3 flex justify-center items-center" href="/settings">
													<CiSettings size={30} className={`text-black dark:text-white inline-block align-top`} />
												</a>
											</div>
										}
										{!isLoggedIn &&
											<div className="mt-3 flex flex-row w-full">
												<Link href="/signin" className="w-1/2 mr-1 px-6 py-2 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md">
													Sign In
												</Link>
												<Link href="/signup" className="w-1/2 ml-1 px-6 py-2 text-black bg-[#eee] rounded-md">
													Sign Up
												</Link>
											</div>
										}
									</>
								</Disclosure.Panel>
							</div>
						</>
					)}
				</Disclosure>

				{/* menu  */}
				<div className="hidden text-center lg:flex lg:items-center">
					<ul className="items-center justify-end flex-1 pt-6 list-none lg:pt-0 lg:flex">
						{navigation.map((menu, index) => (
							<>
								{(!menu.showOnlyLoggedIn || isLoggedIn) &&
									<li className="mr-3 nav__item" key={index}>
										<Link href={menu.link} className="inline-block px-4 py-2 text-lg font-normal text-gray-800 no-underline rounded-md dark:text-gray-200 hover:text-indigo-500 focus:text-indigo-500 focus:bg-indigo-100 focus:outline-none dark:focus:bg-gray-800">
											{menu.name}
										</Link>
									</li>
								}
							</>
						))}
					</ul>
				</div>

				<div className="hidden space-x-4 lg:flex nav__item">
					<ThemeChanger />
					{isLoggedIn &&
						<>
							<Link href="" onClick={signOut} className="px-6 py-2 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
								Sign Out
							</Link>
							<a className="inline-block pl-3 flex justify-center items-center" href="/settings">
								<CiSettings size={30} className={`text-black dark:text-white inline-block align-top`} />
							</a>
						</>
					}
					{!isLoggedIn &&
						<>
							<Link href="/signin" className="px-6 py-2 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5">
								Sign In
							</Link>
							<Link href="/signup" className="px-6 py-2 text-black bg-white rounded-md md:ml-5">
								Sign Up
							</Link>
						</>
					}
				</div>

			</nav>
		</div>
	);
}

export default Navbar;
