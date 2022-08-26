<template>
  <div class="all flex">
    <div class="inner-all">
      <el-scrollbar height="60vh" id="all">
        <ul v-infinite-scroll="tList" class="infinite-list">
          <li v-for="msg in msgList" :key="msg.msgId">
            <chat-message
              :avatar="msg.senderAvatar"
              :name="msg.senderName"
              :message="msg.msg"
              :type="msg.msgType"
              :time="format(msg.sentDate, false)"
              :isMe="msg.isMe"
              :is-group="isGroup"
              :id="msg.msgId"
              :uid="msg.senderId"
            ></chat-message>
          </li>
        </ul>
      </el-scrollbar>
    </div>
    <div class="input-box">
        <chat-input-box
            @add-pic="addPic"
            @send-msg="sendMsg">
        </chat-input-box>
    </div>
  </div>
</template>
<script setup>
import { ElMessage } from "element-plus";
import { ref, reactive, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useI18n } from "vue-i18n";
import { showGroupChatHistory, showFriendChatHistory } from "../api/chat";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import ChatMessage from "../components/ChatMessage.vue";
import ChatInputBox from "../components/ChatInputBox.vue";
import { format } from "../utils/time.js";

const { t } = useI18n();
const route = useRoute();
const store = useUserStore();
const { token, avatar, name } = storeToRefs(store);
const counter = ref(0);
const mins = 30;
const msgList = reactive([]);
const isGroup = ref(false);
const roomId = ref("");
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageNum: 0,
  pageSize: 10,
});

function tList() {
  if(msgList.length > 30) {
    return;
  }
  for (let i = 0; i < page.pageSize; i++) {
    let test = [
      {
        msgId: counter.value.toString(),
        senderId: "114514",
        senderName: "holk",
        senderAvatar:
          "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
        sentDate: {
          year: 2022,
          month: 8,
          day: 24,
          hour: 6,
          min: mins + counter.value,
        },
        isMe: false,
        msg: "cfgvhjbfghjkljhgfdghyjukjyhtgrthyuytrgtyuiythgrfthyjukjyhgffghjjkhgfdfghjkhjgfhdgfhgjhkgjfhdgknkljhuigyutfyrtyctvybuogvyficdtxrutcgihvoufydcitxgckhvyfditxgjhvlfyodickhyfdoitxcydidyci",
        msgType: "text",
      },
      {
        msgId: (counter.value + 1).toString(),
        senderId: "3721893",
        senderName: "zenk",
        senderAvatar: "https://s1.ax1x.com/2022/07/28/vpOkbn.jpg",
        sentDate: {
          year: 2022,
          month: 8,
          day: 24,
          hour: 6,
          min: mins + 1 + counter.value,
        },
        isMe: true,
        msg: "https://s1.ax1x.com/2022/07/28/vpOkbn.jpg",
        msgType: "img",
      },
    ];
    msgList.unshift(...test);
    counter.value += 2;
  }
}
function load() {
  if (!nodata.value && !loading.value) {
    let result;
    if (isGroup.value) {
      result = showGroupChatHistory(token, roomId);
    } else {
      result = showFriendChatHistory(token, roomId);
    }
    result
      .then((res) => {
        if (res.data.success) {
          if (res.data.data.length <= 0) {
            nodata.value = true;
          } else {
            nodata.value = false;
            msgList.unshift(...res.data.data);
            pageN.value += 1;
          }
        } else {
          ElMessage({
            type: "error",
            message: res.data.msg,
            showClose: true,
            grouping: true,
          });
        }
      })
      .catch((err) => {
        let msg;
        if (isGroup.value) {
          msg = t("chatRoom.friendLoadError");
        } else {
          msg = t("chatRoom.groupLoadError");
        }
        ElMessage({
          type: "error",
          message: msg,
          showClose: true,
          grouping: true,
        });
        console.log(err);
      })
      .finally(() => {
        loading.value = false;
      });
  }
}
function sendMsg(input, type) {
    let date = new Date();
    let tempMsg = {
        msgId : "-1",
        senderId: "-1",
        senderName: name,
        senderAvatar: avatar,
        sentDate: {
          year: date.getFullYear(),
          month: date.getMonth() + 1,
          day: date.getDate(),
          hour: date.getHours(),
          min: date.getMinutes() + 1,
        },
        isMe: true,
        msg: input,
        msgType: type,
    }
    msgList.push(tempMsg);
}
function addPic() {

}
onMounted(() => {
  let str = route.params.id;
  let temp = "";
  if (str[0] == "g") {
    isGroup.value = true;
  } else if (str[0] == "f") {
    isGroup.value = false;
  } else {
    ElMessage({
      type: "error",
      message: t("chatRoom.roomTypeError"),
      showClose: true,
    });
  }
  for (let i = 1; i < str.length; i++) {
    temp += str[i];
  }
  roomId.value = temp.toString();
});
</script>
<style scoped>
.all {
    width:100%;
    height: 100%;
}
.flex {
display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  justify-content: flex-start;
}
#all {
    transform: rotate(180deg);
    direction: ltr;
}
.inner-all {
    width: 100%;
    flex-grow: 1;
    flex-shrink: 1;
}
.infinite-list {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column-reverse;
  margin-left: -36px;
  margin-right: 26px;
}
.input-box {
  min-height: 100px;
  flex-grow: 1;
  flex-shrink: 1;
}
</style>
