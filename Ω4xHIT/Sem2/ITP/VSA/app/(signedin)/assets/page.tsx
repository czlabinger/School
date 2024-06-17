import React from "react";
import AuthenticationProvider, { pushHome } from "../../../components/auth/AuthenticationProvider";
import AssetsComponent from "../../../components/sites/assetsComponent";

export default function AssetsPage() {
  return (
	<div className="min-h-screen">
		<AuthenticationProvider onlyViewWhenLoggedIn={true} executeWhenNotPermitted={pushHome}>
			<AssetsComponent />
		</AuthenticationProvider>
	</div>
  );
}
