"use client";

import React from 'react';
import {useState} from 'react';
import "./globals.css";
import { Inter } from "next/font/google";
import {Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button, Tabs, Tab, User, Spinner} from "@nextui-org/react";
import {Card, CardHeader, CardBody} from "@nextui-org/react";
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import { NextRequest, NextResponse } from 'next/server';

const inter = Inter({ subsets: ["latin"] });
const variants = [
  "light",
];


export default function App() {

  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  

  const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzQHZzYS5hdCIsImlhdCI6MTcwNzcyMzI2OSwiZXhwIjoxNzA3NzI0NzA5fQ.FujAz3muc3MHHh1GioQLFbEUb2z51Gc4KuC2k5bAy_o";
  
  const uploadFile = async () => {
    let url = `http://10.0.107.192`;
 
    let formData = new FormData();
    if (selectedFile) {
      formData.append("file", selectedFile);
      formData.append("filename", selectedFile.name);
    }

    await fetch(url + '/assets/upload', {
      method: 'POST',
      body: formData,
      headers: {
        "Authorization": "Bearer " + token, 
      },
    });
   
  };
 
  const onFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setSelectedFile(event.target.files[0]);
    }
  };

  const deleteFile = async () => {
    let url = `http://10.0.107.192`;
 
    let formData = new FormData();
    if (selectedFile) {
      formData.append("filename", selectedFile.name);
    }

    await fetch(url + '/assets/delete', {
      method: 'POST',
      body: formData,
      headers: {
        "Authorization": "Bearer " + token, 
      },
    });
   
  };

  const downloadFile = async () => {
    let urlOrigin = `http://10.0.107.192`;
  
    let formData = new FormData();
    if (selectedFile) {
      formData.append("filename", selectedFile.name);
    }
 
   const response = await fetch(urlOrigin + '/assets/download', {
     method: 'POST',
     body: formData,
     headers: {
        "Authorization": "Bearer " + token, 
     },
   });

  const blob = await response.blob();
  const url = window.URL.createObjectURL(blob);
  const link = document.createElement('a');
  link.href = url;
  if(selectedFile) {
    link.setAttribute('download', selectedFile.name); // or any other extension
    document.body.appendChild(link);
    link.click();
  }
    
};

  return (

  <div>

    <div>
     <input type="file" onChange={onFileChange} />
     <button onClick={uploadFile}>Upload</button>
     <button onClick={downloadFile}>Download</button>
     <button onClick={deleteFile}>Delete</button>
   </div>

    <div className="container">
        
    <h1 style={{marginTop: 100, width:900, marginLeft: 50}}>
    <span style={{marginRight:15}}>Digitales </span>
    <span style={{marginRight:15}}>Erbe </span>
    <span style={{marginRight:15}}>Gestalten: </span>
    <span style={{marginRight:15}}>Deine </span>
    <span style={{marginRight:15}}>Spuren, </span>
    <span style={{marginRight:15}}>deine </span>
    <span>Geschichte.</span>
  </h1>


  <div className="carousel-container" style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
  <Carousel autoPlay infiniteLoop interval={8000} width={700}>
 <div>
 <Card className="py-4" style={{width: 700, height: 400, marginTop: 100, marginLeft: 0,  backgroundImage: `url(/img/Sarg.jpg)`}}>
  
  <CardBody className="overflow-visible py-2">
    
  </CardBody>
</Card>
 </div>
 <div>
 <Card className="py-4" style={{width: 700, height: 400, marginTop: 100, marginLeft: 0,  backgroundImage: `url(/img/sozialmedia.png)`}}>

  <CardBody className="overflow-visible py-2">
    
  </CardBody>
</Card>
 </div>
</Carousel>
</div>
</div>

<div className="flex flex-wrap gap-4" style={{marginLeft: 50}}>
  {variants.map((variant) => (
    <Tabs key={variant} variant={variant} aria-label="Options">
      <Tab key="about us" title="About Us" style={{fontSize:17}}>
      <Card style={{backgroundColor: 'rgba(29, 34, 41, 0.3)', width: '93%', fontSize: 20, marginBottom: 30}}>
        <CardBody>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, 
        sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. 
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. 
        At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</CardBody>
      </Card>
      <div className='container' style={{width: '93%'}}>
      <Card className="py-4" style={{backgroundColor:  'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          <User   
              name="Markus Stuppnig"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Back-End</li>
                <li>Projektleiter</li>
              </ul>
          </CardBody>
      </Card>  
      <Card className="py-4" style={{backgroundColor: 'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          <User   
              name="Christof Zlabinger"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Back-End</li> 
              
              </ul>
          </CardBody>
      </Card>  
      <Card className="py-4" style={{backgroundColor:  'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start">
          <User   
              name="Pavel Bakshi"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Front-End</li>
              </ul>
          </CardBody>
      </Card>  
      <Card className="py-4" style={{backgroundColor:  'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start" >
          <User   
              name="Sara Maj"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Front-End</li>
               
              </ul>
          </CardBody>
      </Card>  
      <Card className="py-4" style={{backgroundColor:  'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start" >
          <User   
              name="Sebastian Pollak"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Front-End</li>
               
              </ul>
          </CardBody>
      </Card>  
      <Card className="py-4" style={{backgroundColor: 'rgba(29, 34, 41, 0.3)', width: '15%', fontSize: 20}}>
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-start" >
          <User   
              name="Benjamin Princ"
              avatarProps={{
              
              }}
            />
          </CardHeader>
          <CardBody className="overflow-visible py-2">
              <ul className='list-style'>
                <li>Back-End</li>
               
              </ul>
          </CardBody>
      </Card> 
      </div> 
      </Tab>
      <Tab key="videos" title="Video" style={{fontSize: 17}}>
      <Spinner color="primary"/>
      </Tab>
    </Tabs>
  ))}
</div>
  
</div>
  )
}
