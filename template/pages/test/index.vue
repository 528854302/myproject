<template>
  <div>

    <el-container>
      <el-header>
        <label for="textarea-large"><h3>正在和J. Circlehead对话</h3></label>
      </el-header>
      <el-main>

        <div id="chat-frame">
          <ul class="list-unstyled">
            <b-media tag="li" class="my-4">
              <template v-slot:aside>
                <img src="~/assets/img/avatar1.jpg" width="64px" height="64px">
              </template>
              <h5 class="mt-0 mb-1">J. Circlehead：</h5>
              <p class="mb-0">
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin.
                Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc
                ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </p>
            </b-media>

            <b-media tag="li" class="my-4" :right-align="idRight">
              <template v-slot:aside>
                <img src="~/assets/img/avatar2.jpg" width="64px" height="64px">
              </template>

              <h5 class="mt-0 mb-1">Lebron James:</h5>
              <p class="mb-0">
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin.
                Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc
                ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </p>
            </b-media>

            <b-media tag="li" class="my-4">
              <template v-slot:aside>
                <img src="~/assets/img/avatar1.jpg" width="64px" height="64px">
              </template>

              <h5 class="mt-0 mb-1">J. Circlehead：</h5>
              <p class="mb-0">
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin.
                Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc
                ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </p>
            </b-media>
          </ul>


          <b-form-textarea
            v-model="sendParams.msg"
            id="textarea-rows"
            placeholder="Tall textarea"
            rows="3"
          ></b-form-textarea>
          <b-button style="float: right" size="lg" variant="primary" @click="send()">发送</b-button>
        </div>

      </el-main>
    </el-container>



  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        idRight:true,
        sendParams:{
          msg: '',
          toId:1,
        },
        websock:null
      }
    },
    methods:{
      send(){
        this.websock.send(JSON.stringify(this.sendParams));
      },
      websocketonmessage(e){ //数据接收
        var redata = JSON.parse(e.data);
        alert(redata.content);
      },
    },
    mounted(){
      var ws = new WebSocket("ws://localhost:8888/ws/2");
      ws.onopen = function (evt) {
        alert("连接成功");
      }
      this.websock = ws;
      this.websock.onmessage = this.websocketonmessage;

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
