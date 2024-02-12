import { useDropzone } from 'react-dropzone';
import {Card, CardHeader, CardBody, Button, Input} from "@nextui-org/react";
import { FaImage } from 'react-icons/fa';

export default function MyDropzone() {
 const {getRootProps, getInputProps} = useDropzone();

 return (

<div>
    <Card className="py-4" style={{backgroundColor: 'rgba(29, 34, 41, 0.3)', width: '60%', fontSize: 20, marginBottom: 30, marginLeft: 200, height: 600}}>
    <CardHeader className="pb-0 pt-2 px-4 items-start">
      <Button style={{marginRight: 20, backgroundColor: '#42A2C2', color: 'white'}}>Upload</Button>
      <Button style={{backgroundColor: '#1D2229', color: 'white'}}>Delete</Button>
    </CardHeader>
    <CardBody className="overflow-visible py-2">
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
        <div style={{ borderRadius: '10px', border: '2px solid #1D2229', height: 400, marginTop: 50, width: '70%'}}>
        <div {...getRootProps()}>
            <input {...getInputProps()} />

            <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        height: 300}}>
                <FaImage style={{fontSize: 200, color: 'rgba(255, 255, 255, 0.1)'}} />
            </div>
            <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        height: 20}}>
            <p>Drag 'n' drop some files here, or click to upload files</p>
            </div>
        </div>
        </div>
    </div>
    </CardBody>
  </Card>

  <Card className="py-4" style={{backgroundColor: 'rgba(29, 34, 41, 0.3)', width: '60%', fontSize: 20, marginBottom: 30, marginLeft: 200, height: 100}}>
    <CardHeader className="pb-0 pt-2 px-4 items-start">
      
    </CardHeader>
    <CardBody className="overflow-visible py-2">
    </CardBody>
  </Card>
  </div>

 );
}
