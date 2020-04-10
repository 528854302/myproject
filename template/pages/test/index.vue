<template>
  <b-container class="bv-example-row">


    <b-row style="margin-top: 2%">
      <b-col cols="8">
        <b-row>
          <b-col cols="12" >
            <b-nav tabs>
              <b-nav-item active>后端开发</b-nav-item>
              <b-nav-item>前端开发</b-nav-item>
              <b-nav-item>大数据</b-nav-item>
              <b-nav-item>人工智能</b-nav-item>
              <b-nav-item>区块链</b-nav-item>
            </b-nav>
          </b-col>
          <b-col cols="12"  :key="index" v-for="(a,index) in articles">
            <b-card>
              <b-media>
                <template v-slot:aside>
                  <b-avatar src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2742459949,603881894&fm=26&gp=0.jpg" size="6rem"></b-avatar>
                </template>
                <a :href="`/articledetail`"><h5 class="mt-0" :key="a.title">{{a.title}}</h5></a>
                <p :key="a.content">
                  {{a.content}}
                </p>
              </b-media>
            </b-card>
          </b-col>
          <!--待删除的静态数据-->
          <b-col cols="12" >
            <b-card>
              <b-media>
                <template v-slot:aside>
                  <a href="http://www.baidu.com"><b-avatar src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=895187916,3765627625&fm=26&gp=0.jpg" size="6rem"></b-avatar></a>
                </template>
                <a href="/articledetail"><h5 class="mt-0">Spring Cloud微服务架构学习之Ribbon负载均衡</h5></a>
                <p >
                  Spring Cloud Ribbon 是一个基于Http和TCP的客服端负载均衡工具，它是基于Netflix Ribbon实现的。
                  它不像服务注册中心、配置中心、API网关那样独立部署.....
                </p>
              </b-media>
            </b-card>
          </b-col>
          <b-col cols="12">
            <b-card>
              <b-media>
                <template v-slot:aside>
                  <a href="http://www.baidu.com"><b-avatar src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2742459949,603881894&fm=26&gp=0.jpg" size="6rem"></b-avatar></a>
                </template>
                <a href="/articledetail"><h5 class="mt-0">Spring Cloud微服务架构学习之Ribbon负载均衡</h5></a>
                <p >
                  Spring Cloud Ribbon 是一个基于Http和TCP的客服端负载均衡工具，它是基于Netflix Ribbon实现的。
                  它不像服务注册中心、配置中心、API网关那样独立部署.....
                </p>
              </b-media>
            </b-card>
          </b-col>
        </b-row>
      </b-col>

      <b-col cols="4">
        <b-row>
          <b-col cols="12">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 85%;"
              class="mb-2 right-card"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button-group>
                <b-button variant="success" href="/addarticle">写文章</b-button>
                <b-button variant="info">问答</b-button>
              </b-button-group>
            </b-card>
          </b-col>
          <b-col cols="12">
            <b-card
              class="right-card-down"
              style="max-width: 85%;"
              title="今日推荐">
              <ul class="list-unstyled">
                <b-media tag="li">
                  <template v-slot:aside>
                    <b-img blank blank-color="#abc" width="25" alt="placeholder"></b-img>
                  </template>

                  <p style="overflow: hidden;max-width: 90%; max-height: 20px" class="mb-0">
                    <a href="">SpringMVC使用@CrossOrigin解决跨域无效问题的解决方案</a>
                  </p>
                </b-media>

                <b-media tag="li" class="my-4">
                  <template v-slot:aside>
                    <b-img blank blank-color="#cba" width="25" alt="placeholder"></b-img>
                  </template>

                  <p class="mb-0" style="overflow: hidden;max-width: 85%;  max-height: 30px">
                    <a href="">nginx 代理路径</a>
                  </p>
                </b-media>

                <b-media tag="li">
                  <template v-slot:aside>
                    <b-img blank blank-color="#bac" width="25" alt="placeholder"></b-img>
                  </template>

                  <p class="mb-0" style="overflow: hidden;max-width: 85%;  max-height: 30px">
                    <a href="">SpringMVC使用@CrossOrigin解决跨域无效问题的解决方案</a>
                  </p>
                </b-media>
              </ul>
            </b-card>
          </b-col>

        </b-row>
      </b-col>
    </b-row>



  </b-container>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'
import axios from '~/plugins/axios'

export default {
  components: {
    AppLogo
  },
  data(){
    return{
      articles:[
      ],
    }
  },
  mounted(){
    axios.get(`/article/select`).then(res=>{
      this.articles=res.data.data;
    })
  }
}
</script>

<style>

</style>

