<template>
  <div>
    <el-button @click="wSend">
      send
    </el-button>
  </div>
</template>
<script setup>
import { useRouter } from "vue-router";
import { onMounted, onUpdated, reactive, ref } from "vue";
import { RouterLink, RouterView } from "vue-router";
import  useUserStore  from "@/stores/userStore";
import { storeToRefs } from "pinia";

const router = useRouter();
const store = useUserStore();
const { token, avatar, name } = storeToRefs(store);
// const rv = ref(null);
// const fnoticeNew = ref(null);
// const gnoticeNew = ref(null);
// var friendShowAll = ref(false);
// var groupShowAll = ref(false);
// var friendParam = reactive({
//   page: {
//     pageSize: 10,
//     pageNum: 1,
//   },
// });
// var groupParam = reactive({
//   page: {
//     pageSize: 10,
//     pageNum: 1,
//   },
// });
// function showAllFriends() {
//   friendShowAll.value = friendShowAll.value == true ? false : true;
// }
// function showAllGroups() {
//   groupShowAll.value = groupShowAll.value == true ? false : true;
// }
// function PagePlus(isFriend) {
//   if(isFriend) {
//     friendParam.page.pageNum += 1;
//   } else {
//     groupParam.page.pageNum += 1;
//   }
// }
// function wSend(input) {
//   console.log(input);
//   ws.send(JSON.stringify(input));
// }
function wsSend(input, type) {
  let roomType = "friend";
  let json = {
    "msg": input,
    "msgType": type,
    "sender": token.value,
    "senderName": name.value,
    "senderAvatar": avatar.value,
    "receiver": "1606447871244648449",
    "receiverType": roomType
  };
  return json;
}

let url = "ws://192.168.0.1:8888/chat";
let connection_resolvers = [];
let ws = new WebSocket("ws://192.168.0.1:8888/pack/chat");

ws.onopen = function () {};
ws.onmessage = function(evt) {
  console.log('ws get msg');
}
ws.onclose = function() {
  console.log('ws close');
}

function wSend() {
  let msg = wsSend("just Test", "text")
  ws.onopen = () =>ws.send(JSON.stringify(msg));
}


// ws.onmessage = function (evt) {
//   alert("onMessage");
//   var dataStr = evt.data;

//   var res = JSON.parse(dataStr);
//   let rType = res.receiverType;
//   let gid = res.receiver;
//   let sid = res.sender;

//   if (rType == "friend") {
//     fnoticeNew.value.noticeNewMsg(true, sid, true);
//   } else {
//     gnoticeNew.value.noticeNewMsg(false, gid, true);
//   }

//   if (router.currentRoute.value.name == "chatRoom") {
//     let str = "f1606447871244648449"; //router.currentRoute.value.params.id;
//     let type = str[0];
//     let roomId = str.slice(1);
//     if (type == "f") {
//       if (sid == roomId && rType == "friend") {
//         fnoticeNew.value.noticeNewMsg(true, sid, false);
//         rv.value.receiveMsg(res);
//       }
//     } else {
//       if (gid == roomId && rType == "group") {
//         gnoticeNew.value.noticeNewMsg(false, gid, false);
//         rv.value.receiveMsg(res);
//       }
//     }
//   }
// };

ws.onclose = function () {};
</script>
<style scoped></style>
