<template>
  <div>
    <el-container id="main-content">

        <el-aside width="200px" >
          <b-card
            id="author-info-card"
            :title="article.nickname"
            :img-src="article.avatar"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2">
            <b-card-text>
              <router-link :to="'/author/'+article.userid">
                ta的主页
              </router-link>
            </b-card-text>
            <div class="mt-3">
              <b-button-group>
                <b-button variant="success">关注</b-button>
                <b-button variant="info" @click="addFriend(article.userid)">加好友</b-button>
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
              <div class="social-share" data-sites="weibo,qq,qzone,wechat" data-url="/myproject" :data-title="article.title" style="float: right"></div>
              <b-button style="float: right"  size="lg" @click="collectAriticle" variant="link" class="mb-2">
                <b-icon icon="star" aria-hidden="true"></b-icon>收藏
              </b-button>
            </b-card>
            <b-card style="margin-top: 5px;">

              <div id="comment-content">
                <b-card v-for="(c,index) in comments" :key="index">
                  <b-media>
                    <template v-slot:aside>
                      <router-link to="/author">
                        <b-img :src="c.avatar" width="50" alt="placeholder"></b-img>
                      </router-link>
                    </template>
                    <h5 class="mt-0">{{c.nickname}}<em style="float: right;color: #7f828b">{{c.createtime}}</em></h5>
                    <p class="mb-0">
                     {{c.content}}
                      <el-button type="text" @click="commentReply(c.id,c.nickname)">回复</el-button>
                      <el-button type="text">查看回复(1)</el-button>
                    </p>
                  </b-media>
                </b-card>

                <b-form-textarea
                  v-model="commentParams.content"
                  id="textarea-small"
                  size="sm"
                  placeholder="Small textarea"
                ></b-form-textarea>
                <b-button size="sm" style="float: right" @click="addComment" variant="info">
                  发表评论
                </b-button>

              </div>
            </b-card>
          </div>



        </el-main>






      <el-dialog title="添加好友" :visible.sync="dialogFormVisible">
        <h3>{{article.nickname}}</h3>
        <el-form>
          <el-form-item label="验证信息">
            <el-input v-model="addFriendParams.content" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitAddFriend">确 定</el-button>
        </div>
      </el-dialog>

    </el-container>





  </div>
</template>

<script>
  import axios from '~/plugins/axios'
  import UserUtils from '~/utils/user'
  export default {
    name: "index",
    data() {
      return {
        addFriendParams:{
          userid:'',
          friendid:'',
          content:'你好啊，可以认识下吗',
        },
        dialogFormVisible:false,
        url: '',
        comments:[],
        commentParams:{
          articleid:'',
          content:'',
          userid:'',
        }
      }
    },
    methods:{
      submitAddFriend(){
        this.addFriendParams.friendid = this.article.userid;
        this.addFriendParams.userid = UserUtils.getUserInfo().id;

        axios.post(`/friend/insert`,this.addFriendParams).then(res=>{
          if (res.data.code==20000){
            this.$notify({
              title: '提示',
              message: '发送朋友验证成功，等待通过',
              duration: 0
            });
          }else {
            this.$notify({
              title: '提示',
              message: res.data.message,
              duration: 0
            });
          }
        });
        this.dialogFormVisible=false;
      },
      addFriend(userid){
        this.dialogFormVisible=true;

      },
      getComments(){
        axios.get(`/comment/selectByArticleId/${this.article.id}`).then(res=>{
          this.comments=res.data.data;
        })
      },
      addComment(){
        this.commentParams.userid = UserUtils.getUserInfo().id;
        this.commentParams.articleid = this.article.id;
        axios.post(`/comment/insert/`,this.commentParams).then(res=>{
          if (res.data.code==20000){
            this.comments=res.data.data;
            this.getComments();
            this.commentParams.content=null;
            this.$message('评论成功');
          }else {
            this.$message.error(res.data.message);
          }
        })
      },
      commentReply(id,nickname){
        this.commentParams.content="@"+nickname+"： ";
      },
      collectAriticle(){
        var userid= UserUtils.getUserInfo().id;
        axios.get(`/article/collect/${userid}/${this.article.id}`).then(res=>{
          if (res.data.code==20000){
            this.$message("收藏成功");
          } else {
            this.$message(res.data.message);
          }
        })
      }

    },
    created(){
      this.getComments();
    },
    asyncData ({ params, env, error }) {
      return axios.get(`article/select/${params.id}`).then(res=>{
        return {
          article:res.data.data
        }
      });
    },
    head: {
      script: [
        { src: 'https://cdn.bootcss.com/social-share.js/1.0.16/js/social-share.min.js' },
        { src: 'https://cdn.bootcss.com/qrcode-generator/1.3.1/qrcode.js' }
      ],
      link: [
        { rel: 'stylesheet', href: 'https://cdn.bootcss.com/social-share.js/1.0.16/css/share.min.css' }
      ]
    }
  }
</script>

<style scoped>
  #main-content{
    margin: 20px;
  }
  #comment-content{
    margin-top: 10px;
  }
</style>
