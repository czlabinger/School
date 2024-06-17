import { useState, useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import { Card, CardHeader, CardBody, Button, Input } from "@nextui-org/react";
import { FaImage } from 'react-icons/fa';
import React from 'react';

export default function MyDropzone({ onFileDrop }) {
  const { getRootProps, getInputProps } = useDropzone({
    onDrop: acceptedFiles => {
      onFileDrop(acceptedFiles);
}});


  const [file, setFile] = useState(null);	
  const [files, setFiles] = useState([]);
  const [fileSizes, setFileSizes] = useState([]);

  const removeAllFiles = () => {
    setFiles([]);
    setFileSizes([]);
  };




  return (
    <div className='mx-8'>
      <div className='w-1/6'>
          <Button className='text-white bg-gradient-to-r from-indigo-500 to-blue-500' onClick={removeAllFiles}>Delete</Button>
      </div>
          <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <div style={{ borderRadius: '10px', border: '3px solid rgb(255,255,255, 0.3)', height: 400, marginTop: 50, width: '90%' }}>
              <div {...getRootProps()}>
                <input {...getInputProps()} />
                <div style={{
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                  height: 300
                }}>
                  <FaImage style={{ fontSize: 200, color: 'rgba(255, 255, 255, 0.1)' }} />
                </div>
                <div style={{
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                  height: 20
                }}>
                  <div className="md:block hidden">
                      <p>Drag 'n' drop some files here, or click to upload files</p>
                  </div>

                  <div className="md:hidden block">
                       <p>Click to upload files</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </div>
  );
}

function bytesToMB(bytes) {
  var megabytes = bytes / (1024 * 1024);
  return Number(megabytes.toFixed(2));
}
