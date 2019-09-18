<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
	<button @click="sentMsg('客户端发送了消息')">发送</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  sockets: {
      disconnect () {
          console.log('socket 断开连接了！'); // 监听socket断开
      },
      connect () {
         // 监听socket连接
          console.log('socket 已经连接了！');
         // 给后台的user_info这个方法传递参数并输出回调信息
		 //向后台发送消息 并接受回调消息
         this.$socket.emit('users', "hellovue", (data) => {
             console.log('users执行回调数据：', data);
         });
      },
      // 监听后台事件，接受服务端发来的消息
	  //messageevent 对应后台的 onEvent
      messageevent (data) {
        console.log(' 接收服务端发来的消息 messageevent--------');
		console.log(data)
      },
	  users (data) {
	    console.log(' 接收服务端发来的消息 users');
	  	console.log(data)
	  },
  },
  mounted() {
  	//this.$socket.emit('connect'); // 此处需要再调用一次connect方法；
  }
  ,methods:{
	  sentMsg(val){
		  this.$socket.emit('users',val);
	  }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
