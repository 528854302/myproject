<template>
  <section class="container">
    <div>
      <app-logo/>

      <div class="links">
        <b-card
          title="登录"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-bottom
          tag="article"
          style=""
          class="mb-2"
        >

          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="账户" prop="username">
              <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="记住我" prop="remenberMe">
              <el-switch v-model="ruleForm.remenberMe"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>

        </b-card>
      </div>
    </div>
  </section>
</template>

<script>
  import AppLogo from '~/components/AppLogo.vue'
  import axios from '~/plugins/axios'
  import cookie from 'js-cookie'
  export default {
    layout: 'loginAndSignup',
    components: {
      AppLogo
    },
    name: "index",
    data() {


      var validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账户'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };

      return {
        userInfo:{},
        ruleForm: {
          username: '',
          password: '',
          remenberMe:false,
          //userInfo:{}
        },
        rules: {
          username: [
            { validator: validateUsername, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ]
        }
      };



    },
    methods:{

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {

            // axios.post(`/user/login`,this.ruleForm).then(res=>{
            //   if (res.data.code==20000){
            //     let userToken = res.data.data;
            //     sessionStorage.setItem("userToken",userToken);
            //   }
            // });
            axios.post(`/user/login`,this.ruleForm).then(res=>{
              if (res.data.code==20000){
                var userToken = res.data.data;
                cookie.set("userToken",userToken);
                axios.defaults.headers.common["token"] = userToken;
                axios.get(`/user/info`).then(res1=>{
                    this.userInfo=res1.data.data;
                    cookie.set("userInfo",JSON.stringify(res1.data.data));
                    this.$router.push('./');
                })
              }else {
                alert(res.data.message);
              }
            });

            // if (this.ruleForm.password=='123456'&&this.resetForm.username=='123456'){
            //   sessionStorage.setItem("user_info1","aaaaaa");
            //   this.$router.push('./');
            // } else {
            //   alert("failed");
            // }


          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }


    }
  }
</script>

<style scoped>
  .container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }

  .links {
    padding-top: 15px;
  }
</style>
