<template>
  <div>

    <div>
      <b-button block variant="outline-secondary" @click="getRadomArticles">换一批</b-button>
    </div>
    <el-container id="main-content">

      <b-card-group columns>

        <!--<b-card width="200px" img-src="https://picsum.photos/400/400/?image=41" img-alt="Image" overlay></b-card>-->



        <b-card v-for="(a,index) in articles" :key="index" :title="a.title" :img-src="a.avatar" img-alt="Image" img-top>
          <b-card-text>
            {{a.content}}
          </b-card-text>
          <b-card-text class="small text-muted">{{a.createtime}}</b-card-text>
        </b-card>



      </b-card-group>


    </el-container>



  </div>
</template>

<script>
  import cookie from 'js-cookie'
  import axios from '~/plugins/axios'
  export default {
    name: "index",
    data() {
      return {
        articles:[
        ]
      }
    },
    methods:{
      getRadomArticles(){
        axios.get(`/article/discover/10`).then(res=>{
          this.articles=res.data.data;
        })
      }
    },
    mounted(){
      this.getRadomArticles();
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
