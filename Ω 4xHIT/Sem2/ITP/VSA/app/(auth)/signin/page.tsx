"use client";

import React from "react";
import SignIn from "../../../components/sites/signin";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";

export default function Home() {
  return (
    <AuthenticationProvider onlyViewWhenNotLoggedIn={true} executeWhenNotPermitted={pushHome}>
      <SignIn />
    </AuthenticationProvider>
  );
}
