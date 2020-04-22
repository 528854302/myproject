<template>
  <b-container class="bv-example-row">
    <b-row style="margin-top: 2%">
      <b-col cols="8">
        <b-row>
          <b-col cols="12" >
            <h1>我的收藏</h1>
          </b-col>
          <b-col cols="12"  :key="index" v-for="(a,index) in articles">
            <b-card>
              <b-media>
                <template v-slot:aside>
                  <router-link :to="`/author/${a.userid}`">
                    <b-avatar :src="a.avatar" size="6rem"></b-avatar>
                  </router-link>

                </template>
                <router-link :to="`/article/${a.id}`"><h5 class="mt-0" :key="a.title">{{a.title}}</h5></router-link>
                <p :key="a.content" style="max-height:50px;overflow: hidden">
                     {{a.nickname}}: {{a.content}}
                </p>
                <b-button size="sm" variant="link" class="mb-2">
                  <b-icon icon="star" aria-hidden="true"></b-icon>
                </b-button>
                <em> 发布于{{a.createtime}}</em>

                <a style="float: right" href="`/articledetail`"><b-icon icon="chevron-down"></b-icon>阅读原文</a>
              </b-media>
            </b-card>
          </b-col>

          <h2 v-if="!this.articles">暂无收藏</h2>

        </b-row>
      </b-col>

    </b-row>



  </b-container>
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
    data(){
      return{
        activeName: '1',
        articles:[
        ],
      }
    },
    mounted(){
      loginUtils.checkLogin();
      // axios.get(`/article/select`).then(res=>{
      //   this.articles=res.data.data;
      // })
      this.getCollected();
    },
    methods:{

      getCollected(){
        var id = UserUtils.getUserInfo().id;
        axios.get(`/article/getCollected/${id}/`).then(res=>{
          this.articles=res.data.data;
        })
      }
    },

  }
</script>

<style>

</style>

