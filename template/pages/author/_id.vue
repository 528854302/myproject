<template>
  <section class="container">
    <div>
      <b-card>
        <b-media>
          <template v-slot:aside>
            <img  :src="author.avatar" style="width:150px;height:150px;border-radius: 13px">
          </template>

          <h3 class="mt-0">{{author.nickname}}</h3>
          <p style="float: left">ta的简介：</p>
          <p>
            暂无内容。
          </p>
          <p>
            <b-button-group style="float: right">
              <b-button variant="success">关注</b-button>
              <b-button variant="info">加好友</b-button>
              <b-button variant="warning">私信</b-button>
            </b-button-group>
          </p>

          <b-media style="margin-top: 15%">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="动态" name="first">
                <b-card v-for="(a,index) in articles" :key="index">
                  <b-media>
                    <template v-slot:aside>
                      <b-avatar :src="author.avatar" size="4em"></b-avatar>
                    </template>
                    <router-link :to="`/article/${a.id}`">
                      <h5 class="mt-0">{{a.title}}</h5>
                    </router-link>
                    <em>{{a.createtime}}</em>
                  </b-media>
                </b-card>


              </el-tab-pane>
              <el-tab-pane label="关注" name="second">内容2</el-tab-pane>
              <el-tab-pane label="回答" name="third">内容3</el-tab-pane>
              <el-tab-pane label="提问" name="fourth">内容4</el-tab-pane>
              <el-tab-pane label="收藏" name="five">内容5</el-tab-pane>
            </el-tabs>


          </b-media>
        </b-media>
      </b-card>
    </div>
  </section>
</template>

<script>
  import AppLogo from '~/components/AppLogo.vue'
  import axios from '~/plugins/axios'
  import UserUtils from '~/utils/user'
  export default {
    components: {
      AppLogo
    },
    name: "index",
    data() {
      return{
        activeName: 'first',
        articles:[],
      }

    },
    methods:{
      handleClick(tab, event) {
        console.log(tab, event);
      },
      getArticleListByUid(){
        axios.get(`/article/selectByUid/${this.author.id}`).then(res=>{
          this.articles=res.data.data;
        })
      }

    },
    mounted(){
      this.getArticleListByUid();

    },
    asyncData ({ params, env, error }) {
      return axios.get(`user/selectAuthor/${params.id}`).then(res=>{
        return {
          author:res.data.data
        }
      });
    }
  }
</script>

<style scoped>
  .container {
    min-height: 100vh;
    margin-top: 20px;
    justify-content: center;
    align-items: center;
    text-align: center;
  }

</style>
