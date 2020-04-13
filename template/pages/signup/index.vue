<template>
  <section class="container">
    <div>
      <app-logo/>

      <div class="links">
        <b-card
          title="注册"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-bottom
          tag="article"
          style=""
          class="mb-2"
        >

          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
            <el-form-item label="头像">
              <el-radio-group v-model="ruleForm.avatar">
                <el-radio label="/img/avatar1.jpg"><img src="/img/avatar1.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar2.jpg"><img src="/img/avatar2.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar3.jpg"><img src="/img/avatar3.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar4.jpg"><img src="/img/avatar4.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar5.jpg"><img src="/img/avatar5.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar6.jpg"><img src="/img/avatar6.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
                <el-radio label="/img/avatar7.jpg"><img src="/img/avatar7.jpg" style="width: 60px;height: 60px;border-radius: 10px"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="账号" prop="username">
              <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
              <el-input type="text" v-model="ruleForm.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
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
      var validateNickname = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入昵称'));
        } else {
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        ruleForm: {

          username: '',
          nickname: '',
          password: '',
          checkPass: '',
          avatar: '/img/avatar1.jpg',
          remenberMe:false
        },
        rules: {
          username: [
            { validator: validateUsername, trigger: 'blur' }
          ],
          nickname:[
            { validator: validateNickname, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass:[
            { validator: validatePass2, trigger: 'blur' }
          ]
        }
      };



    },
    methods:{

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post(`/user/insert`,this.ruleForm).then(res=>{
              if (res.data.code==20000){
                this.$message('注册成功，请登录');
                this.$router.push('/login');
              }else {
                this.$message(res.data.message);
              }
            })
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
