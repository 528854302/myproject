<template>
  <section class="container">
      <div>
        <b-card>
          <b-media>
            <template v-slot:aside>
              <img  :src="user.avatar" style="width:150px;height:150px;border-radius: 13px">
            </template>

            <h3 class="mt-0">{{user.nickname}}</h3>
            <p style="float: left">我的简介：</p>
            <p>
              注册时间: {{user.createtime}}
            </p>
            <p>
              账号: {{user.username}}
            </p>
            <p>
              <b-button style="float: right" variant="outline-primary">编辑个人资料</b-button>
            </p>

            <b-media style="margin-top: 15%">
              <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="简历" name="first">
                  <b-card>
                    <b-media>
                      <template v-slot:aside>
                        <b-img :src="user.avatar" width="64" alt="placeholder"></b-img>
                      </template>

                      <h4 class="mt-0">James</h4>
                      <p>
                        男 / 22 / 南昌 /本科
                      </p>
                      <p>
                        <b-icon icon="phone"></b-icon>18379460803  &nbsp;&nbsp;&nbsp;
                        <b-icon icon="envelope-fill"></b-icon>528854302@qq.com
                      </p>

                      <b-media>

                        <h5 class="mt-0">求职意向</h5>
                        <p class="mb-0">
                          <b-icon icon="tag-fill"></b-icon>
                        </p>
                      </b-media>
                    </b-media>
                  </b-card>

                </el-tab-pane>
                <el-tab-pane label="动态" name="second">
                  <b-card v-for="(a,index) in articles" :key="index">
                    <b-media>
                      <template v-slot:aside>
                        <b-avatar :src="userInfo.avatar" size="4em"></b-avatar>
                      </template>
                      <router-link :to="`/article/${a.id}`">
                        <h5 class="mt-0">{{a.title}}</h5>
                      </router-link>
                      <em>{{a.createtime}}</em>
                    </b-media>
                  </b-card>

                </el-tab-pane>
                <el-tab-pane label="关注" name="three">内容2</el-tab-pane>

                <el-tab-pane label="收藏" name="four">内容4</el-tab-pane>
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
  import loginUtils from '~/utils/loginUtils'
  export default {
    components: {
      AppLogo
    },
    name: "index",
    data() {

      return{
        activeName: 'first',
        user:{
          id:'',
          nickname:'',
          createtime:'',
          avatar:''
        },
        userInfo:{},
        articles:[],
      }

    },
    methods:{

      handleClick(tab, event) {
        console.log(tab, event);
      },
      getUserDetailById(){
        axios.get(`/user/select/${this.userInfo.id}`).then(res=>{
          this.user=res.data.data;
        })
      },
      getArticleListByUid(){
        axios.get(`/article/selectByUid/${this.userInfo.id}`).then(res=>{
          this.articles=res.data.data;
        })
      },


    },
    created(){
      loginUtils.checkLogin();
      this.userInfo=UserUtils.getUserInfo();
      this.getUserDetailById();
      this.getArticleListByUid();
    }
  }
</script>

<style scoped>
  .container {
    min-height: 100vh;
    margin-top: 20px;
    justify-content: center;

  }

</style>
