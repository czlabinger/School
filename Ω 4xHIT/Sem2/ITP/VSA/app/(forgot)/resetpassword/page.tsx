import React, { Suspense } from "react";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";
import ResetPasswordComponent from "../../../components/sites/resetPassword";

export default function ContactPage() {
  return (
    <div>
      <AuthenticationProvider onlyViewWhenNotLoggedIn={true} executeWhenNotPermitted={pushHome}>
        <Suspense>
          <ResetPasswordComponent />
        </Suspense>
      </AuthenticationProvider>
    </div>
  );
}
