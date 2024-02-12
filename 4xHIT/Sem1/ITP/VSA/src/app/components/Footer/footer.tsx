import React from 'react';
import { AiOutlinePhone, AiFillFacebook, AiFillInstagram, AiOutlineMail } from 'react-icons/ai';

const Footer = () => {
  return (
    <footer style={{ backgroundColor: '#1D2229', color: 'white', marginTop: 'calc(40vh - 100px)',flexDirection: 'column',  display: 'flex', justifyContent: 'center', alignItems: 'center', position: '-webkit-sticky'}}>
    <img src={'../img/logo_blau.png'} alt="Footer Image" style={{ width: '100%', maxWidth: '200px', marginTop: '20px' }}/>
      
    <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', width: 400, marginBottom: 20 }}>
        <a href="https://example.com">Contact</a>
        <a href="https://example.com">Imprint</a>
        <a href="https://example.com">Terms of Use</a>
        <a href="https://example.com">Privacy Policy</a>
      </div>
      <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'space-between', width: '200px', marginBottom: 40 }}>
        <AiOutlinePhone size={30} />
        <AiFillFacebook size={30} />
        <AiFillInstagram size={30} />
        <AiOutlineMail size={30} />
      </div>
    </footer>
  );
};

export default Footer;
