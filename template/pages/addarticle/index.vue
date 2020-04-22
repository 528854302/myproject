<template>
    <div>
      <b-container id="artcle-input" class="bv-example-row">
        <b-row>
          <b-col></b-col>
          <b-col cols="10">
            <b-row class="mt-2">
              <b-col sm="2">
                <label for="textarea-large">文章标题：</label>
              </b-col>
              <b-col sm="10">
                <div>
                  <b-form-input v-model="formData.title" placeholder="Enter your name"></b-form-input>
                </div>
              </b-col>
            </b-row>

            <b-row class="mt-2">
              <b-col sm="2">
                <label for="textarea-large">文章内容：</label>
              </b-col>
              <b-col sm="10">
                <b-form-textarea
                  v-model="formData.content"
                  id="textarea-rows"
                  placeholder="Tall textarea"
                  rows="8"
                ></b-form-textarea>
              </b-col>
            </b-row>
            <b-row class="mt-2">
              <b-col sm="2">
                <label for="textarea-large">文章分类：</label>
              </b-col>
              <b-col sm="10">
                <b-form-select v-model="selected" :options="options" multiple :select-size="4"></b-form-select>
                <div class="mt-3">Selected: <strong>{{ formData.type }}</strong></div>
              </b-col>
            </b-row>
            <b-row class="mt-2">
              <b-col sm="2"></b-col>
              <b-col sm="10">
                <b-button size="lg" variant="primary" @click="submit()">发布</b-button>
                <b-button size="lg" @click="cancle()">取消</b-button>
              </b-col>
            </b-row>

          </b-col>
          <b-col></b-col>
        </b-row>
      </b-container>


    </div>
</template>

<script>
  import cookie from 'js-cookie'
  import axios from '~/plugins/axios'
  import UserUtils from '~/utils/user'
  import loginUtils from '~/utils/loginUtils'
    export default {
        name: "index",
      data() {
        return {
          title: null,
          options: [
            { value: '1', text: '后端开发' },
            { value: '2', text: '前端开发' },
            { value: '3', text: '大数据'  },
            { value: '4', text: '区块链' },
            { value: '5', text: '人工智能' },
          ],
          selected: [1],
          formData:{
            userid:null,
            content: null,
            title: null,
            channelid: null,
          },
          userInfo:{}
        }
      },
      methods:{
        submit(){
          this.formData.channelid=this.selected[0];
          var userInfo = cookie.get("userInfo");
          if (userInfo){
            var obj = JSON.parse(userInfo);
            this.formData.userid=obj.id;
          }
          this.formData.userid = UserUtils.getUserInfo().id;
          axios.post(`/article/insert`,this.formData).then(res=>{
            if (res.data.code==20000){
              this.$alert('发布文章成功', '提示', {
                confirmButtonText: '确定',
              });
              this.$router.push('/');
            } else {
              this.$message.error(res.data.message);
            }
          })
        },
        cancle(){
            this.formData=null;
        },

        created(){
          loginUtils.checkLogin();
        }
      }
    }
</script>

<style scoped>
  #artcle-input{
    margin: 100px;
  }
</style>
