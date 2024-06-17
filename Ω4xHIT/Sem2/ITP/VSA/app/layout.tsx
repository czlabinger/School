"use client";
import "../css/tailwind.css";

import Navbar from "../components/navbar";
import Footer from "../components/footer";
import PopupWidget from "../components/popupWidget";
import { ThemeProvider } from "next-themes";
import AuthenticationProvider from "../components/auth/AuthenticationProvider";


export default function RootLayout({ children }) {
  
  	return (
    	<html lang="en">
      	<body className="min-h-screen">
        	<ThemeProvider attribute="class">
				
				<AuthenticationProvider onlyViewWhenLoggedIn={false}>
		  			
 						<Navbar />
 						<main>
    						{children}
 						</main>
 						<Footer />
 						<PopupWidget />

				</AuthenticationProvider>
        	</ThemeProvider>
      	</body>
    	</html>
  	);
}
