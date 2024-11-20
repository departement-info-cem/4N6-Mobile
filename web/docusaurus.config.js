// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion
import { themes } from 'prism-react-renderer';

const siteConfig = require("./config");

const lightCodeTheme = themes.vsLight
const darkCodeTheme = themes.vsDark

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: siteConfig.nom,
  tagline: siteConfig.description,
  url: "https://info.cegepmontpetit.ca/",
  baseUrl: `/${siteConfig.nomUrl}/`,
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  favicon: "img/favicon.ico",

  organizationName: "departement-info-cem",
  projectName: siteConfig.nomUrl,
  deploymentBranch: "gh-pages",
  trailingSlash: false,

  i18n: {
    defaultLocale: "fr",
    locales: ["fr"],
  },

  presets: [
    [
      "classic",
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve("./sidebars.js"),
          routeBasePath: "/",
          editUrl: `https://github.com/departement-info-cem/${siteConfig.nomUrl}/tree/main/web`,
        },
        theme: {
          customCss: [require.resolve("./src/css/custom.css")],
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      docs: {
        sidebar: {
          hideable: true,
        },
      },
      navbar: {
        title: siteConfig.nom,
        logo: {
          alt: "Logo CEM",
          src: "img/logo.svg",
        },
        items: [
          {
            type: "doc",
            docId: "cours/revision",
            position: "left",
            label: "Cours",
          },
          {
            type: "docSidebar",
            position: "left",
            sidebarId: "tp",
            label: "Travaux Pratiques",
          },
          {
            type: "docSidebar",
            position: "left",
            sidebarId: "recettes",
            label: "Recettes",
          },
          {
            type: "doc",
            docId: "mantras",
            position: "right",
            label: "Mantras",
          },
          {
            type: "doc",
            docId: "problemes-frequents",
            position: "right",
            label: "Problèmes",
          },
          {
            label: "Installator 🆕",
            href: `https://github.com/departement-info-cem/scripts-mobile/releases/download/Script/ScriptSharp.exe`,
            position: "right",
          },
//          {
//            label: "Skill Tree",
//            href: `https://info.cegepmontpetit.ca/4N6-Mobile/file/4N6.html`,
//            position: "right",
//          },
        ],
      },
      footer: {
        style: "dark",
        links: [
          {
            title: "Sources",
            items: [
              {
                label: "GitHub",
                href: `https://github.com/departement-info-cem/${siteConfig.nomUrl}`,
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()}. ${
          siteConfig.nom
        }. CÉGEP Édouard-Montpetit.`,
      },
      // Décommenter et remplir pour activer l'indexation des pages par le moteur de recherche local
      // algolia: {
      //   appId: '',
      //   apiKey: '',
      //   indexName: '',
      //   contextualSearch: true,
      //   searchPagePath: 'search',
      // },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
        additionalLanguages: ["csharp", "java", "dart"],
      },
      metadata: [
        {
          name: "keywords",
          content: `${siteConfig.nom}, ${siteConfig.description}, informatique, technique, cégep, cegep, édouard-montpetit, edouard-montpetit, édouard montpetit, edouard montpetit`,
        },
      ],
    }),
};

module.exports = config;
