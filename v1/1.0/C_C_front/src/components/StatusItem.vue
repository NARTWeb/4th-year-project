<template>
  <div id="all">
    <div id="top">
      <div id="left">
        <el-avatar :src="avatar" :size="60" />
        <div class="name">{{ uname }}</div>
      </div>
      <div id="right">
        <span class="text">{{ message }}</span>
        <div class="img-box">
          <div v-for="(img, index) in props.pictures" :key="img">
            <el-image class="img" :src="img" :initial-index="index" :preview-src-list="props.pictures"/>
          </div>
        </div>
      </div>
    </div>
    <div id="down">
      <div :class="iconClass" @click="likeS" id="like"></div>
      <span v-show="like" style="font-weight:100">{{props.heartNum}}</span>
      <el-button round type="primary" @click="startCom">...</el-button>
    </div>
    <div id="bottom">
      <div id="bt1">
        <el-input
          v-model="input"
          :placeholder="t('statusItem.commentPlaceHolder')"
          @change="makeCom"
        />
      </div>
    </div>
    <div id="comments">
      <comment-list
        :comment-List="commentList"
        ></comment-list>
    </div>
  </div>
</template>
<script setup>
import { onMounted } from "vue";
import { reactive, ref } from "vue";
import { likeStatus } from "@/api/status";
import { postComment } from "@/api/comment";
import { useI18n } from "vue-i18n";
import CommentList from "./CommentList.vue";
import  useUserStore  from "@/stores/userStore";
import { storeToRefs } from "pinia";

const { t } = useI18n();
const commentDisplay = ref('none');
const props = defineProps({
  statusId: String,
  avatar: String,
  uname: String,
  message: String,
  pictures: Array,
  comments: Array,
  heart: Boolean,
  heartNum: Number,
  date: String,
  uid: String,
});
const store = useUserStore();
const { name, avatar, token } = storeToRefs(store);
const commentList = reactive(props.comments);
const like = ref(props.heart);
const iconClass = ref("iconfont icon-aixin");
const input = ref('');
function likeS() {
  like.value = like.value ? false : true;
  if (like.value) {
    iconClass.value = "iconfont icon-aixin_shixin like";
  } else {
    iconClass.value = "iconfont icon-aixin";
  }
}

function startCom() {
  if(commentDisplay.value == 'none') {
    commentDisplay.value = '';
  } else {
    commentDisplay.value = 'none';
  }
}

function makeCom() {
  alert(input.value);
  let comment = {
    id:"738291",
    uname:name,
    msg:input.value,
    date:'now'
  }
  commentList.unshift(comment);
  input.value = '';
}

onMounted(() => {
  if (like.value) {
    iconClass.value = "iconfont icon-aixin_shixin like";
  }
});
</script>

<style scoped>
@import url("@/assets/css/iconfont.css");
.like {
  color: red;
}
.img-box {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
  align-content: flex-start;
}
.name {
  text-align: center;
  font-size: 1em;
  font-weight: 500;
  margin-top: -0.5em;
}
#all {
  margin: 1em 0;
}
#top {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
}
#right {
  flex-grow: 5;
  margin-left: 20px;
}
#left {
  flex-grow: 1;
  text-align: center;
}
#down {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
#like {
  margin-right: 20px;
}
#bottom {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-around;
  width: 100%;
}
#bt1 {
  width:80%;
  display: v-bind(commentDisplay);
}
</style>
