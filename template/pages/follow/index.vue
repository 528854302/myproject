<template>
  <section class="container">
    <div>
      <div class="links">


      </div>
    </div>
  </section>
</template>

<script>
  import cookie from 'js-cookie'
  import AppLogo from '~/components/AppLogo.vue'
  import axios from '~/plugins/axios'
  export default {
    components: {
      AppLogo
    },
    name: "index",
    data() {





    },
    methods:{




    }
  }
</script>

<style scoped>
  .container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }

  .links {
    padding-top: 15px;
  }
</style>
