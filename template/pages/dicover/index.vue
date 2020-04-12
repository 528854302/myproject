<template>
  <div>

    <el-container id="main-content">

      <b-card-group columns>

        <b-card img-src="https://picsum.photos/400/400/?image=41" img-alt="Image" overlay></b-card>



        <b-card title="Title" img-src="https://placekitten.com/500/350" img-alt="Image" img-top>
          <b-card-text>
            This card has supporting text below as a natural lead-in to additional content.
          </b-card-text>
          <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
        </b-card>



      </b-card-group>


    </el-container>



  </div>
</template>

<script>
  import axios from '~/plugins/axios'
  export default {
    name: "index",
    data() {
      return {

      }
    },
    methods:{

    },
    mounted(){

    },
    destroyed(){

    },
  }
</script>

<style scoped>
  #main-content{
    padding: 5%;
  }


</style>
