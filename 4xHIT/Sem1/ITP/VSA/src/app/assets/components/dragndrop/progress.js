import ProgressBar from 'react-bootstrap/ProgressBar';
import { useState } from 'react';

export default function MyProgressBar() {
 const [progress, setProgress] = useState(0);

 // Update the progress state here

 return <ProgressBar now={progress} />;
}
