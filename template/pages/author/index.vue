<template>
  <section class="container">
      <div>
        <b-card>
          <b-media>
            <template v-slot:aside>
              <img  src="/_nuxt/assets/img/avatar2.jpg" style="width:150px;height:150px;border-radius: 13px">
            </template>

            <h3 class="mt-0">Lebron James</h3>
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
                  <b-card>
                    <b-media>
                      <template v-slot:aside>
                        <b-avatar src="/_nuxt/assets/img/avatar2.jpg" size="4em"></b-avatar>
                      </template>
                      <router-link to="/article">
                        <h5 class="mt-0">Spring Cloud微服务架构学习之Ribbon负载均衡</h5>
                      </router-link>
                      <em> 2020-04-07 14:13:10</em>
                    </b-media>
                  </b-card>

                  <b-card>
                    <b-media>
                      <template v-slot:aside>
                        <b-avatar src="/_nuxt/assets/img/avatar2.jpg" size="4em"></b-avatar>
                      </template>
                      <router-link to="/article">
                        <h5 class="mt-0">Spring Cloud微服务架构学习之Ribbon负载均衡</h5>
                      </router-link>
                      <em> 2020-04-07 14:13:10</em>
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
  export default {
    components: {
      AppLogo
    },
    name: "index",
    data() {

      return{
        activeName: 'first'
      }

    },
    methods:{

      handleClick(tab, event) {
        console.log(tab, event);
      }


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
