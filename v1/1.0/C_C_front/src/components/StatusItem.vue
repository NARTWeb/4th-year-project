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
          <div v-for="img in props.pictures" :key="img">
            <img :src="img" />
          </div>
        </div>
      </div>
    </div>
      <div id="down">
        <div :class="iconClass" @click="test" id="like"></div>
        <el-button round type="primary" @click="makeComment">...</el-button>
      </div>
  </div>
</template>
<script setup>
import { onMounted } from 'vue';
import { reactive, ref } from "vue";

const props = defineProps({
  statusId: String,
  avatar: String,
  uname: String,
  message: String,
  pictures: Array,
  heart: Boolean,
  heartNum: Number,
  date: Date,
  key: String,
});

const like = ref(props.heart);
const iconClass = ref("iconfont icon-aixin");
function test() {
  like.value = like.value ? false : true;
  if(like.value) {
    iconClass.value = "iconfont icon-aixin_shixin like";
  } else {
    iconClass.value = "iconfont icon-aixin";
  }
}

function makeComment() {}

onMounted(() => {
  if(like.value) {
    iconClass.value = "iconfont icon-aixin_shixin like";
  }
});
</script>

<style>
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
img {
  width: 150px;
  min-width: 100px;
  height: 150px;
  min-height: 100px;
  margin-left: 5px;
}
.name{
  text-align: center;
  font-size: 1em;
  font-weight: 500;
  margin-top: -1.5em;
}
.text {
  font-size: 1.5em;
  font-weight: 300;
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
</style>
