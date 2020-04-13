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
    export default {
        name: "index",
      data() {
        return {

          title: null,
          options: [
            { value: 'a', text: 'Java' },
            { value: 'b', text: 'C++' },
            { value: 'c', text: 'Python' },
            { value: 'e', text: 'C#' },
            { value: 'f', text: '人工智能' },
            { value: 'g', text: '大数据' }
          ],
          selected: [],
          formData:{
            userid:null,
            content: null,
            title: null,
            type: null,
          }
        }
      },
      methods:{
        submit(){
          this.formData.type=this.selected[0];
          axios.post(`/save`,this.formData).then(res=>{
            if (res.data.code==20000){
              alert("success");
            } else {
              alert("failed");
            }
          });
        },
        cancle(){
            this.formData=null;
        }
      }
    }
</script>

<style scoped>
  #artcle-input{
    margin: 100px;
  }
</style>
