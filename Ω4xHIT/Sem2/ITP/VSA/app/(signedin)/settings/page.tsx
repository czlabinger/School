import React from "react";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";
import SettingsComponent from "../../../components/sites/settingsComponent";

export default function SettingsPage() {
  return (
    <div>
      <AuthenticationProvider onlyViewWhenLoggedIn={true} executeWhenNotPermitted={pushHome}>
        <SettingsComponent />
      </AuthenticationProvider>
    </div>
  );
}
