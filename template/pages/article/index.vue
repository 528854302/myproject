<template>
  <div>
    <el-container id="main-content">
      <el-container>
        <el-aside width="230px" >
          <b-card style="width: 230px;padding: 10px">
            <img src="~assets/img/avatar1.jpg" width="170px" height="170px" style="text-align: center" alt="">
            <b-card-text>
              原创 390  粉丝 1580  获赞 583  评论 579  访问 120
            </b-card-text>
            <div class="mt-3">
              <b-button-group>
                <b-button variant="success">关注</b-button>
                <b-button variant="info">加好友</b-button>
                <b-button variant="warning">私信</b-button>
              </b-button-group>
            </div>
          </b-card>
        </el-aside>
        <el-main>
          <div>
            <b-card title="Spring Cloud微服务架构学习之Ribbon负载均衡"
                    sub-title="最后发布于2020-04-07 14:13:10 ">
              <b-card-text>
                <h5>Spring Cloud Ribbon简介：</h5>
                Spring Cloud Ribbon 是一个基于Http和TCP的客服端负载均衡工具，它是基于Netflix Ribbon实现的。
                它不像服务注册中心、配置中心、API网关那样独立部署，
                但是它几乎存在于每个微服务的基础设施中。包括前面的提供的声明式服务调用也是基于该Ribbon实现的。
                理解Ribbon对于我们使用Spring Cloud来讲非常的重要，
                因为负载均衡是对系统的高可用、网络压力的缓解和处理能力扩容的重要手段之一。
                在上节的例子中，我们采用了声明式的方式来实现负载均衡。
                实际上，内部调用维护了一个RestTemplate对象，
                该对象会使用Ribbon的自动化配置，同时通过@LoadBalanced开启客户端负载均衡。

              </b-card-text>

              <b-card-text> 负载均衡是对系统的高可用，
                网络压力的缓解和处理能力扩容的重要手段之一。
                我们通常所说的负载均衡指的是服务端的负载均衡，
                其中分为硬件负载均衡和软件负载均衡，硬件负载均衡主要通过在服务节点之间安装专门的负载均衡设备，
                比如F5，而软件的负载均衡则是通过在服务端安装一些具有均衡负载功能或者模块的软件来完成请求的分发工作，
                比如Nginx。
              </b-card-text>
              <b-button style="float: right"  size="lg" variant="link" class="mb-2">
                <b-icon icon="star" aria-hidden="true"></b-icon>收藏
              </b-button>
            </b-card>

            <b-card style="margin-top: 5px;">
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="Small textarea"
              ></b-form-textarea>
              <b-button size="sm" style="float: right" variant="info">
                发表评论
              </b-button>
              <div id="comment-content">
                <b-card>
                  <b-media>
                    <template v-slot:aside>
                      <router-link to="/author">
                        <b-img src="~assets/img/avatar3.jpg" width="50" alt="placeholder"></b-img>
                      </router-link>
                    </template>
                    <h5 class="mt-0">Nested Media <em style="float: right;color: #7f828b">2020-02-19 19:52:42</em></h5>
                    <p class="mb-0">
                      Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in
                      faucibus.
                      <el-button type="text">回复</el-button>
                      <el-button type="text">查看回复(1)</el-button>
                    </p>
                  </b-media>
                </b-card>
                <b-card>
                  <b-media>
                    <template v-slot:aside>
                      <router-link to="/author">
                        <b-img src="~assets/img/avatar5.jpg" width="50" alt="placeholder"></b-img>
                      </router-link>
                    </template>
                    <h5 class="mt-0">Kobe Brant <em style="float: right;color: #7f828b">2020-02-19 19:52:42</em></h5>

                    <p class="mb-0">
                      Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in
                      faucibus.
                      <el-button type="text">回复</el-button>
                      <el-button type="text">查看回复(1)</el-button>
                    </p>
                  </b-media>
                </b-card>
              </div>
            </b-card>
          </div>
        </el-main>
      </el-container>
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


      }



    },
    methods:{

    }
  }
</script>

<style scoped>
  #main-content{
    margin: 20px;
  }
  #comment-content{
    margin-top: 10%;
  }
</style>
