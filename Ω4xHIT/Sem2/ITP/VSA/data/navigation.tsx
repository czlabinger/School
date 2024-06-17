export interface Entry {
    name: string;
    link: string;
    showOnlyLoggedIn: boolean;
}

export const navigation: Entry[] = [
    { name: "Home", link: "/", showOnlyLoggedIn: false },
    { name: "Assets", link: "/assets", showOnlyLoggedIn: true },
    { name: "Pricing", link: "/pricing", showOnlyLoggedIn: false },
    { name: "Features", link: "/features", showOnlyLoggedIn: false },
];
