"use client";

import React from "react";
import SignUp from "../../../components/sites/signup";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";

export default function Home() {
  return (
    <AuthenticationProvider onlyViewWhenNotLoggedIn={true} executeWhenNotPermitted={pushHome}>
      <SignUp />
    </AuthenticationProvider>
  );
}
