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
              原创 390  粉丝 1580  获赞 583  评论 579  访问 120
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
  import cookie from 'js-cookie'
  import axios from '~/plugins/axios'
  export default {
    name: "index",
    data() {
      return {
        addFriendParams:{
          userid:'',
          friendid:'',
          content:'你好啊，可以认识下吗'
        },
        dialogFormVisible:false,
        url: ''
      }
    },
    methods:{
      submitAddFriend(){
        var userInfostr = cookie.get("userInfo");
        if (userInfostr){
          var userInfo = JSON.parse(userInfostr);
          this.addFriendParams.friendid = this.article.userid;
          this.addFriendParams.userid = userInfo.id;
        }
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
    margin-top: 10%;
  }
</style>
