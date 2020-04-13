module.exports = {
  plugins: [
    {
     src: '~plugins/ElementUI',
      ssr: true // ssr: true表示这个插件只在服务端起作用
    },
    {
      src: '~plugins/bootstrapvue'
    }
    ],
  css: [
    'element-ui/lib/theme-chalk/index.css',
    'bootstrap-vue/dist/bootstrap-vue.css',
    'bootstrap/dist/css/bootstrap.css'
  ],


  /*
  ** Headers of the page
  */
  head: {
    title: 'myproject front !!!',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'myproject1' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    vendor: ['axios'],
    /*
    ** Run ESLint on save
    */
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

