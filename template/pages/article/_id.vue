<template>
  <div>
    <el-container id="main-content">
      <el-container>
        <el-aside width="200px" >
          <b-card
            id="author-info-card"
            title="Lebron James"
            img-src="img/avatar1.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2">
            <b-card-text>
              原创 390  粉丝 1580  获赞 583  评论 579  访问 120
            </b-card-text>
            <div class="mt-3">
              <b-button-group>
                <b-button variant="success">关注</b-button>
                <b-button variant="warning">私信</b-button>
              </b-button-group>
            </div>
          </b-card>
        </el-aside>
        <el-main>

          <div>
            <b-card  :title="article.title"
                    style="min-height: 20%"
                    :sub-title="`最后发布于:`+ article.updatetime">
              <b-card-text>
               {{article.content}}
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
                        <b-img src="img/avatar3.jpg" width="50" alt="placeholder"></b-img>
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
                        <b-img src="img/avatar5.jpg" width="50" alt="placeholder"></b-img>
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

    },
    mounted(){
    },
    asyncData ({ params, env, error }) {
      return axios.get(`article/select/${params.id}`).then(res=>{
        return {
          article:res.data.data
        }
      });
    },
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
