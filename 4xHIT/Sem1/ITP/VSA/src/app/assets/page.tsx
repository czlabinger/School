"use client"
import React from 'react';
import { Inter } from "next/font/google";
import { useDropzone } from 'react-dropzone';
import 'react-responsive-carousel/lib/styles/carousel.min.css';

import MyDropzone from './components/dragndrop/dragndrop';
import MyProgressBar from './components/dragndrop/progress';


const inter = Inter({ subsets: ["latin"] });
const variants = [
  "light",
];
export default function App() {


  return (
  <div>
    <div style={{ display: 'flex', justifyContent: 'left', alignItems: 'center', height: '20vh', marginLeft: 200 }}>
    <h2>Add new Assets</h2>
    </div>

    <div>
       <MyDropzone />
       <MyProgressBar />
       
     </div>
  </div>
  )
}
