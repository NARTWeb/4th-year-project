<template>
  <div class="all">
    <div class="inner-all">
      <div ref="scroll" class="pulldown-wrapper">
        <div class="pulldown-content">
        <ul class="infinite-list pulldown-list">
          <li v-for="msg in msgList" :key="msg.msgId" class="pulldown-list-item">
            <chat-message
              :uid="msg.senderId"
              :avatar="msg.senderAvatar"
              :name="msg.senderName"
              :message="msg.msg"
              :type="msg.msgType"
              :time="msg.sentDate"
              :isMe="msg.isMe"
              :is-group="isGroup"
              :id="msg.msgId"
            ></chat-message>
          </li>
        </ul>
        </div>
      </div>
    </div>
    <div>
      <chat-input-box @add-pic="console.log('!')" @send-msg="sendMsg"> </chat-input-box>
    </div>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
import { ref, reactive, onMounted, watch } from "vue";
import { useRoute } from "vue-router";
import { useI18n } from "vue-i18n";
import { showGroupChatHistory, showFriendChatHistory } from "../api/chat";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import ChatMessage from "../components/ChatMessage.vue";
import ChatInputBox from "../components/ChatInputBox.vue";
import { format } from "../utils/time";
import BScroll from "@better-scroll/core";
import MouseWheel from "@better-scroll/mouse-wheel";
import PullDown from "@better-scroll/pull-down";
import ScrollBar from '@better-scroll/scroll-bar'

BScroll.use(MouseWheel);
BScroll.use(PullDown);
BScroll.use(ScrollBar);

const route = useRoute();
const store = useUserStore();
const { token, avatar, name } = storeToRefs(store);
const counter = ref(0);
const mins = 30;
const page = reactive({
  pageNum: 0,
  pageSize: 3,
});

export default {
  data() {
    return {
      msgList: [],
      isGroup: false,
      roomId: "",
      loading: false,
      nodata: false,
    }
  },
  mounted() {
    this.initBscroll();
    this.setParams();
    this.tList();
  },
  components: {
    ChatMessage,
    ChatInputBox
  },
  methods: {
    initBscroll() {
      this.bscroll = new BScroll(this.$refs.scroll, {
        startY: -600,
        scrollY: true,
        useTransition: false,
        pullDownRefresh: true,
        mouseWheel: true,
        click: true
      });
      this.bscroll.on("pullingDown", this.pullingDownHandler);
    },
    setParams() {
      let str = this.$route.params.id;
      let temp = "";
      if (str[0] == "g") {
        this.isGroup = true;
      } else if (str[0] == "f") {
        this.isGroup = false;
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
      this.roomId = temp.toString();
    },
    async pullingDownHandler() {
      console.log("trigger pullDown");
      this.tList();
      this.bscroll.finishPullDown();
    },
    load() {
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
                msgList.push(...res.data.data);
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
    },
    sendMsg(input, type) {
      let date = new Date();
      let tempMsg = {
        msgId: "-1",
        senderId: "-1",
        senderName: name,
        senderAvatar: avatar,
        sentDate: format({
          year: date.getFullYear(),
          month: (date.getMonth() + 1),
          day: date.getDate(),
          hour: (date.getHours() + 1),
          min: (date.getMinutes() + 1),
        }, false),
        isMe: true,
        msg: input,
        msgType: type,
      };
      this.msgList.push(tempMsg);
    },
    tList() {
      if (this.msgList.length > 30) {
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
            sentDate: format({
              year: 2022,
              month: 8,
              day: 24,
              hour: 6,
              min: mins + counter.value,
            }, false),
            isMe: false,
            msg: "cfgvhjbfghjkljhgfdghyjukjyhtgrthyuytrgtyuiythgrfthyjukjyhgffghjjkhgfdfghjkhjgfhdgfhgjhkgjfhdgknkljhuigyutfyrtyctvybuogvyficdtxrutcgihvoufydcitxgckhvyfditxgjhvlfyodickhyfdoitxcydidyci",
            msgType: "text",
          },
          {
            msgId: (counter.value + 1).toString(),
            senderId: "3721893",
            senderName: "zenk",
            senderAvatar: "https://s1.ax1x.com/2022/07/28/vpOkbn.jpg",
            sentDate: format({
              year: 2022,
              month: 8,
              day: 24,
              hour: 6,
              min: mins + 1 + counter.value,
            }, false),
            isMe: true,
            msg: "https://s1.ax1x.com/2022/07/28/vpOkbn.jpg",
            msgType: "img",
          },
        ];
        this.msgList.unshift(...test);
        counter.value += 2;
      }
      console.log("list.length: " + this.msgList.length);
    }
  },
  watch: {
    msgList: {
      handler(newMsgList, oldMsgList) {
        this.bscroll && this.bscroll.refresh();
      },
      deep: true
    }
  },
  setup() {
    const { t } = useI18n();
  },
};
</script>
<style scoped>
.all {
  width: 75vw;
}
.inner-all {
  width: 100%;
}
.infinite-list {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  height: 100%;
}
.loading {
  height: 65vh;
}
.pulldown-wrapper {
  height: 50vh;
  overflow-y: scroll;
  overflow-x: visible;
}
</style>
