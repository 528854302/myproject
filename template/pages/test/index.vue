<template>
  <div>

    <el-container>
      <el-header>
        <label for="textarea-large"><h3>正在和对话</h3></label>
      </el-header>
      <el-main>

        <div id="chat-frame">


          <ul class="list-unstyled">

            <!--<b-media tag="li" class="my-4">-->
            <!--<template v-slot:aside>-->
            <!--<img src="~/assets/img/avatar1.jpg" width="64px" height="64px">-->
            <!--</template>-->
            <!--<h5 class="mt-0 mb-1">J. Circlehead：</h5>-->
            <!--<p class="mb-0">-->
            <!--Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin.-->
            <!--Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc-->
            <!--ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.-->
            <!--</p>-->
            <!--</b-media>-->

            <b-media v-for="(m,index) in msgs" :key="index" tag="li" class="my-4" :right-align="uid == m.from.id">
              <template v-slot:aside>
                <img v-if="uid == m.from.id" src="~/assets/img/avatar1.jpg" width="50px" height="50px">
                <img v-else src="~/assets/img/avatar3.jpg" width="50px" height="50px">
              </template>

              <div v-if="uid == m.from.id">
                <p style="float: right;color: white;padding: 1%;border-radius: 10px;background-color: rgba(0,137,222,0.68)" class="mb-0">
                  {{m.content}}
                </p>
                <span v-if="m.status==0" style="float: right">未读</span>
                <span v-else style="float: right">已读</span>
              </div>
              <div  v-else>
                <h5 class="mt-0 mb-1">{{m.from.name}} : <span style="float: right">{{m.sendDate}}</span></h5>
                <!--<p class="mb-0" style="color: black;padding: 1%;border-radius: 10px;background-color: gainsboro">-->
                <!--{{m.content}}-->
                <!--</p>-->
                <p style="float: left;color: black;padding: 1%;border-radius: 10px;background-color: rgba(214,221,227,0.68)" class="mb-0">
                  {{m.content}}
                </p>
              </div>

            </b-media>


          </ul>


          <b-form-textarea
            v-model="sendParams.msg"
            id="textarea-rows"
            placeholder="请输入。。。"
            rows="3"
          ></b-form-textarea>
          <b-button style="float: right" size="lg" variant="primary"  @click="send()">发送</b-button>
        </div>

      </el-main>
    </el-container>



  </div>
</template>

<script>
  import axios from '~/plugins/axios'
  export default {
    name: "index",
    data() {
      return {
        uid: 2,
        idRight:true,
        msgs:[

        ],
        sendParams:{
          msg: '',
          toId:1,
        },
        websock:null,
        sendBtnDisabled:false
      }
    },
    methods:{
      send(){
        if (this.sendParams.msg==null){
          alert("内容不能为空");
        } else {
          this.websock.send(JSON.stringify(this.sendParams));
          this.sendParams.msg=null;
          this.getmsgs();
        }
      },
      websocketonmessage(e){ //数据接收
        var redata = JSON.parse(e.data);
        this.getmsgs();
      },
      getmsgs(){
        axios.get(`message/2/1/1/15`).then(res=>{
          this.msgs=res.data.data.reverse();
        });
      }
    },
    mounted(){
      var ws = new WebSocket("ws://localhost:8081/ws/2");
      ws.onopen = function (evt) {
        //alert("连接成功");
      }
      this.websock = ws;
      this.websock.onmessage = this.websocketonmessage;
      this.getmsgs();
    },
    destroyed(){
      this.websock.close();
    },
  }
</script>

<style scoped>
  #chat-frame{
    margin: 0 auto;
    width: 90%;
    border: 1px solid gainsboro;
    border-radius: 5px;
    padding: 5%;
  }
  .chart-row{
    margin-bottom: 3%;
  }


</style>
