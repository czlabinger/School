import React from "react";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";
import ForgotPasswordComponent from "../../../components/sites/forgotPasswordComponent";

export default function ContactPage() {
  return (
    <div>
      <AuthenticationProvider onlyViewWhenNotLoggedIn={true} executeWhenNotPermitted={pushHome}>
        <ForgotPasswordComponent />
      </AuthenticationProvider>
    </div>
  );
}
