<template>
  <div>

    <b-navbar toggleable="lg" type="dark" variant="primary">
      <b-navbar-brand href="/">NavBar</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/">首页</b-nav-item>
          <b-nav-item v-b-toggle.sidebar-1 @click="getFriendList" >通讯录</b-nav-item>
          <b-nav-item to="/dicover">发现</b-nav-item>
          <b-nav-item to="/follow">关注</b-nav-item>
          <b-nav-item to="/collect">收藏</b-nav-item>
          <b-nav-item to="/job">招聘</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">搜索</b-button>
          </b-nav-form>


          <b-nav-item-dropdown right v-if="userInfo.id">
            <template v-slot:button-content>
              <b-avatar variant="info" :src="userInfo.avatar"></b-avatar>
              <em>{{userInfo.nickname}}</em>
            </template>
            <b-dropdown-item to="/myinfo">我的主页</b-dropdown-item>
            <b-dropdown-item @click="logout">退出</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right v-else>
            <!-- Using 'button-content' slot -->
            <template v-slot:button-content>
              <em>请登录</em>
            </template>
            <b-dropdown-item to="/login">登录</b-dropdown-item>
            <b-dropdown-item to="/signup">注册</b-dropdown-item>
          </b-nav-item-dropdown>

        </b-navbar-nav>
      </b-collapse>
    </b-navbar>


    <b-sidebar id="sidebar-1" title="通讯录" shadow>
    <div class="px-3 py-2">
      <b-list-group style="max-width: 300px;">
        <b-list-group-item v-for="(f,index) in friends" :key="index" class="d-flex align-items-center">
          <b-avatar class="mr-3" :src="f.avatar"></b-avatar>
          <span class="mr-auto">{{f.nickname}}</span>
          <b-badge>{{f.unreadmsg}}</b-badge>
          <b-button variant="light" size="sm" :href="'/chat/'+f.friendid">发消息</b-button>
        </b-list-group-item>
      </b-list-group>
    </div>
  </b-sidebar>


    <nuxt/>

    <h5 style="margin-top: 100px;margin-bottom: 20px;text-align: center;">MIT Licensed | Copyright © 2020 Author:528854302@qq.com</h5>

  </div>
</template>
<script>
  import axios from '~/plugins/axios'
  import UserUtils from '~/utils/user'
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        userInfo:{
          id:'',
          token:'',
          avatar: '',
          nickname :''
        },
        friends:[]

      };
    },
    methods: {
      logout(){
        UserUtils.removeUser();
        this.$router.go(0);
      },
      showUserInfo(){
        // var userInfo = cookie.get("userInfo");
        // if (userInfo){
        //   this.userInfo=JSON.parse(userInfo);
        // }
        this.userInfo = UserUtils.getUserInfo()
      },
      getFriendList(){
        axios.get(`/friend/selectByUserId/${this.userInfo.id}`).then(res=>{
          if (res.data.code==20000){
            this.friends=res.data.data;
          } else {
            alert(res.data.message);
          }
        })
      }
    },
    created(){
      this.showUserInfo();
    }
  }
</script>
<style>
html {
  font-family: "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 16px;
  word-spacing: 1px;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: border-box;
  margin: 0;
}

.button--green {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #3b8070;
  color: #3b8070;
  text-decoration: none;
  padding: 10px 30px;
}

.button--green:hover {
  color: #fff;
  background-color: #3b8070;
}

.button--grey {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #35495e;
  color: #35495e;
  text-decoration: none;
  padding: 10px 30px;
  margin-left: 15px;
}

.button--grey:hover {
  color: #fff;
  background-color: #35495e;
}
</style>

