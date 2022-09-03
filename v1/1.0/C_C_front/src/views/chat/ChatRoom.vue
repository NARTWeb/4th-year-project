<template>
  <div class="all flex">
    <div class="inner-all">
      <el-button class="to-setting" @click="toGroupInfo" plain><el-icon><Star /></el-icon></el-button>
      <el-scrollbar height="60vh" id="all" always>
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
import { ref, reactive, onMounted, onUpdated, onBeforeUnmount } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import { showGroupChatHistory, showFriendChatHistory } from "@/api/chat";
import  useUserStore  from "@/stores/userStore";
import { storeToRefs } from "pinia";
import ChatMessage from "@/components/ChatMessage.vue";
import ChatInputBox from "@/components/ChatInputBox.vue";
import { format } from "@/utils/time.js";

const { t } = useI18n();
const route = useRoute();
const router = useRouter();
const store = useUserStore();
const { token, avatar, name } = storeToRefs(store);
const counter = ref(0);
const mins = 30;
const msgList = reactive([]);
const isGroup = ref(false);
const roomId = ref("");
const loading = ref(false);
const nodata = ref(false);
const showSetting = ref("");
const showSetting2 = ref("");
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
            message: t("chatRoom.LoadError"),
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
          message: t("chatRoom.LoadError"),
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
function wsSend(input, type) {
  
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
function receiveMsg(msgInfo) {

}
function addPic(img) {
  sendMsg(img, "img");
}
function setParam() {
  let str = route.params.id;
  let temp = "";
  if (str[0] == "g") {
    isGroup.value = true;
    showSetting.value = "flex";
    showSetting2.value = "-webkit-flex";
  } else if (str[0] == "f") {
    isGroup.value = false;
    showSetting.value = "none";
    showSetting2.value = "none";
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
}
onMounted(() => {
  setParam();
});
onUpdated(() => {
  setParam();
  msgList.splice(0, msgList.length);
  tList();
});
function toGroupInfo() {
  router.push({ name: "groupChatInfo", params: {} });
}
</script>
<style scoped>
.all {
    width:100%;
    height: 100%;
}
.flex {
display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column nowrap;
  justify-content: flex-start;
}
#all {
    transform: rotate(180deg);
    direction: ltr;
}
.inner-all {
    width: 100%;
    height: 200px;
    flex: auto;
}
.infinite-list {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column-reverse;
  margin-left: -36px;
  margin-right: 26px;
}
.input-box {
  height: 170px;
}
.to-setting {
  display: v-bind(showSetting);
  display: v-bind(showSetting2);
  position: absolute;
  text-align: center;
  top: 50px;
  right: 50px;
  width: 25px;
  height: 25px;
  z-index: 5;
}
.el-scrollbar__bar .is-vertical {
  transform: rotate(180deg);
}
</style>


