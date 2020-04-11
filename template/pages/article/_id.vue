<template>
  <div>
    <el-container id="main-content">
      <el-container>
        <el-aside width="200px" >
          <b-card
            id="author-info-card"
            title="Lebron James"
            img-src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2742459949,603881894&fm=26&gp=0.jpg"
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
            <b-card :title="article.title"
                    style="min-height: 20%"
                    :sub-title="`最后发布于:`+ article.updatetime">
              <b-card-text>
               {{article.content}}
              </b-card-text>
              <b-button size="sm" variant="link" class="mb-2">
                <b-icon icon="star" aria-hidden="true"></b-icon>
              </b-button>
              <b-link href="#" class="card-link">评论</b-link>
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
            </b-card>
          </div>
        </el-main>
      </el-container>
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
</style>
