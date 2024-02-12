const { nextui } = require("@nextui-org/react");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./*.{js,ts,jsx,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    fontSize: {
      sm: '0.8rem',
      base: '1rem',
      xl: '1.15rem',
      '2xl': '1.563rem',
      '3xl': '1.953rem',
      '4xl': '2.441rem',
      '5xl': '3.052rem',
    }
  },


  darkMode: "class",
  
  plugins: [
    nextui({
      themes: {
        light: {
          colors: {
            background: "#383B3C", // or DEFAULT
            foreground: "#FFFFFF", // or 50 to 900 DEFAULT
            color: "#EDF2F2",
            
            primary: {
              //... 50 to 900
              foreground: "#000000",
              DEFAULT: "#006FEE",
            },
            // ... rest of the colors
          },
        },
        dark: {
          colors: {
            background: "#000000", // or DEFAULT
            foreground: "#000000", // or 50 to 900 DEFAULT
            primary: {
              //... 50 to 900
              foreground: "#000000",
              DEFAULT: "#000000",
            },
          },
          // ... rest of the colors
        },
        mytheme: {
          // custom theme
          extend: "dark",
          colors: {
            primary: {
              DEFAULT: "#000000",
              foreground: "#000000",
            },
            focus: "#000000",
          },
        },
      },
    }),
  ],

  
};
