"use client"

import Footer from './components/Footer/footer';
import './components/popup';

import "./globals.css";
import { Inter } from "next/font/google";
import React from "react";
import {Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button, Tabs, Tab, User, Spinner} from "@nextui-org/react";
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import Popup from './components/popup';


const inter = Inter({ subsets: ["latin"] });
const variants = [
  "light",
];


export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {

  const currentPath = window.location.pathname;
  return (
    
    <html lang="en">
      <body className={inter.className}>
        <img src="../img/background.png" className="background-image"/>
        
        <Navbar className="bg-383B3C">
        <NavbarBrand className='text-xl'>
          <img src='../img/logo_blau.png' style={{height: 37}}/>
         
        </NavbarBrand>
        <NavbarContent className="sm:flex gap-4" justify="center">
          <NavbarItem  isActive={currentPath === '/'}>
            <Link color='foreground' href="/" >
              <p className={`text-xl ${currentPath === '/' ? 'active-link' : ''}`}>Home</p>
            </Link>
          </NavbarItem>
          <NavbarItem isActive={currentPath === '/assets'}> 
            <Link color="foreground" href="/assets">
            <p className={`text-xl ${currentPath === '/assets' ? 'active-link' : ''}`}>Assets</p>
            </Link>
          </NavbarItem>
          <NavbarItem  isActive={currentPath === '/features'}>
            <Link color="foreground" href="/features" >
            <p className={`text-xl ${currentPath === '/features' ? 'active-link' : ''}`}>Features</p>
            </Link>
          </NavbarItem>
          <NavbarItem>
            <Link color="foreground" href="#" >
            <p className='text-xl'>Prices</p>
            </Link>
          </NavbarItem>
      
        </NavbarContent>
        <NavbarContent justify="end">

          <NavbarItem className="hidden lg:flex">
            <Link href="#">Login</Link>
          </NavbarItem>
          <NavbarItem>
            <Button as={Link} color="primary" href="#" variant="flat">
              Sign In
            </Button>
          </NavbarItem>
        </NavbarContent>
      </Navbar>

      {children}

      <Popup/>

      <Footer></Footer>

      
      </body>
      
    </html>
  );
}