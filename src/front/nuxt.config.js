
export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,
  generate: {
    dir: '../main/resources/static',
    fallback: true
  },

  router: {
    middleware: ['auth-gard'],
  },

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    titleTemplate: '%s - rakuriku',
    title: 'rakuriku',
    htmlAttrs: {
      lang: 'ja'
    },
    pwa: {
      manifest: {
        lang: 'ja',
      },
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    { src: '~/plugins/request-headers.ts' },
    { src: '~/plugins/Validation.ts' },
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: [
    {
      path: '@/components/',
      pathPrefix: false
    },
  ],


  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/typescript
    '@nuxt/typescript-build',
    // https://go.nuxtjs.dev/vuetify
    '@nuxtjs/vuetify',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    "@nuxtjs/axios",
  ],

  axios: {
    proxy: true,
    prefix: '/api',
  },

  // Vuetify module configuration: https://go.nuxtjs.dev/config-vuetify
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  },

  proxy: {
    '/management-account/': {
      target: 'http://sb:8080/',
      headers: { 'X-Forwarded-Host': 'localhost:3000' }
    },
    '/api/': {
      target: 'http://sb:8080/',
      headers: { 'X-Forwarded-Host': 'localhost:3000' }
    }
  }
}
