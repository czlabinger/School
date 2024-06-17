import { ThemeProvider } from "next-themes";
import "../css/tailwind.css";
import AuthenticationProvider from "../components/auth/AuthenticationProvider";

function MyApp({ Component, pageProps }) {
  return (
    <ThemeProvider attribute="class">
      <AuthenticationProvider>
        <Component {...pageProps} />
      </AuthenticationProvider>
    </ThemeProvider>
  );
}

export default MyApp;
