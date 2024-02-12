"use client"
import { Inter } from "next/font/google";
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure} from "@nextui-org/react";
import React, { useEffect } from 'react';


const inter = Inter({ subsets: ["latin"] });
const variants = [
  "light",
];
export default function Popup() {

    const {isOpen, onOpen, onClose} = useDisclosure();
    const [backdrop, setBackdrop] = React.useState('opaque')
  
    const backdrops = [ "blur"];
  
    const handleOpen = (backdrop) => {
      setBackdrop(backdrop)
      onOpen();
    }

    useEffect(() => {
      let pop_status = localStorage.getItem('pop_status');
      if(!pop_status){
        onOpen();
        localStorage.setItem('pop_status',1);
      } else if(pop_status === 'decline') {
        onOpen();
        localStorage.removeItem('pop_status');
      }
    }, []);


  return (
  <div>
    
    
      <Modal backdrop={backdrop} isOpen={isOpen} onClose={onClose}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1" style={{backgroundColor: '#1D2229'}}>Cookies</ModalHeader>
              <ModalBody  style={{backgroundColor: '#1D2229'}}>
                <p> 
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Nullam pulvinar risus non risus hendrerit venenatis.
                  Pellentesque sit amet hendrerit risus, sed porttitor quam.
                </p>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Nullam pulvinar risus non risus hendrerit venenatis.
                  Pellentesque sit amet hendrerit risus, sed porttitor quam.
                </p>
                <p>
                  Magna exercitation reprehenderit magna aute tempor cupidatat consequat elit
                  dolor adipisicing. Mollit dolor eiusmod sunt ex incididunt cillum quis. 
                  Velit duis sit officia eiusmod Lorem aliqua enim laboris do dolor eiusmod. 
                  Et mollit incididunt nisi consectetur esse laborum eiusmod pariatur 
                  proident Lorem eiusmod et. Culpa deserunt nostrud ad veniam.
                </p>
              </ModalBody>
              <ModalFooter  style={{backgroundColor: '#1D2229'}}>
              <Button color="danger" variant="light" onPress={() => {
                onClose();
                localStorage.setItem('pop_status', 'decline');
              }}>
                Decline
              </Button>
                <Button color="primary" onPress={onClose}>
                  Accept
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
  </div>
  )
}
