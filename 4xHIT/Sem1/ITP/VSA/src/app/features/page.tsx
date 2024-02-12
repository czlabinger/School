"use client"
import React from 'react';
import { Inter } from "next/font/google";
import {Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button, Tabs, Tab, User, Spinner} from "@nextui-org/react";
import {Card, CardHeader, CardBody} from "@nextui-org/react";
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';

const inter = Inter({ subsets: ["latin"] });
const variants = [
  "light",
];
export default function App() {



  return (
  <div>
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '20vh' }}>
    <h1>Features</h1>
    </div>
    <div>
           <table className='table_1' style={{backgroundColor: 'rgba(29, 34, 41, 0.2)'}}>
            <tr style={{fontSize: 40}}>
                <th style={{padding: '20px', backgroundColor: 'rgba(56, 59, 60, 0.1)'}}>Abonnements</th>
                <th style={{padding: '30px', backgroundColor: 'rgba(35, 105, 202, 0.3)'}}>Basic</th>
                <th style={{padding: '20px', backgroundColor: 'rgba(40, 202, 43, 0.3)'}}>Andvanced</th>
                <th style={{padding: '20px', backgroundColor: 'rgba(202, 40, 40, 0.3)'}}>Premium</th>
            </tr>

            <tr style={{height:300}}>
                <th style={{padding: '20px', fontSize: 40, backgroundColor: 'rgba(29, 34, 41, 0.1)' }}>Features</th>
                <td style={{padding: '30px', fontSize: 25, backgroundColor: 'rgba(35, 105, 202, 0.1)'}}>This allows it to store 5 assets of type text</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(40, 202, 43, 0.1)'}}>This allows it to store 20 assets of type text, audio, image</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(202, 40, 40, 0.1)'}}>This allows it to store 50 assets of type text, audio, image, video</td>
            </tr>

            <tr style={{height:300}}>
                <th style={{padding: '20px', fontSize: 40, backgroundColor: 'rgba(29, 34, 41, 0.1)'}}>File Size</th>
                <td style={{padding: '30px', fontSize: 25,  backgroundColor: 'rgba(35, 105, 202, 0.1)'}}>-</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(40, 202, 43, 0.1)'}}>-</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(202, 40, 40, 0.1)'}}>limited to 3MB</td>
            </tr>

            <tr style={{height:300}}>
                <th style={{padding: '20px', fontSize: 40, backgroundColor: 'rgba(29, 34, 41, 0.1)'}}>Prices</th>
                <td style={{padding: '30px', fontSize: 25,  backgroundColor: 'rgba(35, 105, 202, 0.1)'}}>Free</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(40, 202, 43, 0.1)'}}>5€ per Year</td>
                <td style={{padding: '20px', fontSize: 25, backgroundColor: 'rgba(202, 40, 40, 0.1)'}}>20€ per Year</td>
            </tr>
            
           </table>
       </div>


</div>
  )
}
