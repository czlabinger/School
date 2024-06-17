import Head from "next/head";
import Hero from "../components/hero";
import Navbar from "../components/navbar";
import Featured from "../components/featuredSolutions";

import { benefitOne, benefitTwo } from "../components/data";
import Video from "../components/video";
import Benefits from "../components/benefits";
import Footer from "../components/footer";
import Footer_O from "../components/footer_old";
import Testimonials from "../components/testimonials";
import Cta from "../components/cta";
import Faq from "../components/faq";
import MessageWidget from "../components/message_widget";
import {Divider} from "@nextui-org/react";
import ProductSpotlight from "../components/product_spotlight";

const Home = () => {
  return (
    <>
      <Head>
        <title>VSA</title>
        <meta
          name="description"
          content="Nextly is a free landing page template built with next.js & Tailwind CSS"
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>
    
      <Navbar />
      <Hero />
      {/* <div className="flex justify-center"> */}
      {/* <Divider className="my-0 w-32 ring-offset-2 ring-1 rounded-lg" /> */}
      {/* </div> */}
      <Featured />
      {/* <Divider className="my-0 w-32 ring-offset-2 ring-1 rounded-lg" /> */}
      {/* <Benefits data={benefitOne} /> */}
      {/* <Benefits imgPos="right" data={benefitTwo} /> */}
      {/* <SectionTitle
        pretitle="Watch a video"
        title="Learn how to fullfil your needs">
        This section is to highlight a promo or demo video of your product.
        Analysts says a landing page with video has 3% more conversion rate. So,
        don&apos;t forget to add one. Just like this.
      </SectionTitle>
      <Video /> */}
      <ProductSpotlight />

      <Benefits data={benefitOne} />
      <Benefits imgPos="right" data={benefitTwo} />

      <Faq />
      <Testimonials />
      
      
      {/* <Cta /> */}
      <Footer />
      {/* <Footer_O /> */}
      <MessageWidget />
    </>
  );
}

export default Home;