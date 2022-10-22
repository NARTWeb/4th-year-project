<template>
  <div id="all">
    <div v-if="isMe" class="bubble right">
      <div>
        <div class="avatar">
          <el-avatar :src="props.avatar" />
        </div>
      </div>
      <div class="wrap w1">
        <div class="content">
          <div v-if="props.type == 'text'" class="text">
            {{ props.message }}
          </div>
          <div v-else-if="props.type == 'picture'">
            <el-image
              class="img"
              :src="props.message"
              :z-index="100"
              preview-teleported
              :preview-src-list="[props.message]"
            />
          </div>
        </div>
        <div>
          <div>{{ time }}</div>
        </div>
      </div>
    </div>

    <div v-else class="bubble left">
      <div @click="popInfo">
        <div class="avatar">
          <el-avatar :src="avatar" @click="popInfo" />
        </div>
      </div>
      <div class="wrap w2">
        <div class="content">
          <div v-if="props.type == 'text'" class="text">
            {{ props.message }}
          </div>
          <div v-else-if="props.type == 'picture'">
            <el-image
              class="img"
              :src="props.message"
              :z-index="100"
              preview-teleported
              :preview-src-list="[props.message]"
            />
          </div>
        </div>
        <div>
          <div>{{ time }}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const props = defineProps({
  avatar: String,
  name: String,
  message: String,
  time: String,
  isMe: Boolean,
  isGroup: Boolean,
  type: String,
  id: String,
  uid: String,
});

function popInfo() {
  if (props.isGroup) {
    return;
  } else {
    router.push({ name: "friendInfo", params: { id: props.uid } });
  }
}
</script>
<style scoped>
.content {
  max-width: 55vw;
}
.text {
  word-wrap: break-word;
}
.bubble {
  overflow: hidden;
  position: relative;
  padding-right: 50px;
  width: 100%;
  min-width: 580px;
}
.bubble:after {
  clear: both;
  content: "";
}
.bubble div.avatar {
  display: block;
  height: 44px;
  width: 44px;
  border-radius: 2px;
  overflow: hidden;
}
.bubble div.avatar > img {
  width: 100%;
  height: 100%;
}
.bubble.left div.avatar {
  float: left;
}
.bubble.right div.avatar {
  float: right;
}
.bubble .content {
  padding: 0.7em 0.5em;
  width: auto;
  font-size: 16px;
  border-radius: 6px;
  border: 1px solid;
  position: relative;
  display: inline-block;
}
.bubble .content:before {
  content: "";
  display: inline-block;
  box-sizing: border-box;
  width: 12px;
  height: 12px;
  border: 1px solid transparent;
  border-radius: 3px;
  position: absolute;
  background-color: inherit;
  border-right-color: inherit;
  border-bottom-color: inherit;
}
.bubble.left {
  text-align: left;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
}
.bubble.right {
  text-align: right;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row-reverse nowrap;
}
.bubble.left .wrap {
  margin-left: 16px;
  margin-right: 56px;
}
.bubble.left .content {
  background: #fdf6ec;
  border-color: #f3d19e;
}
.bubble.left .content:before {
  left: -6px;
  top: 16px;
  transform: rotate(135deg);
}
.bubble.right .wrap {
  margin-right: 16px;
  margin-left: 56px;
}
.bubble.right .content {
  background: #ecf5ff;
  border-color: #a0cfff;
  text-align: left;
}
.bubble.right .content:before {
  right: -6px;
  top: 16px;
  transform: rotate(-45deg);
}
</style>
