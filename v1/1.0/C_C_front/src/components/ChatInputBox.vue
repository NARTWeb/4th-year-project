<template>
  <div class="all">
    <div id="left">
      <textarea
        @keydown.enter="keyDown"
        rows="6"
        v-model="input"
        style="outline: none"
        placeholder="Say something here..."
      ></textarea>
    </div>
    <div id="right">
      <div class="top">
        <div>
          <el-icon class="addp" :size="25" @click="addPhoto"
            ><Picture
          /></el-icon>
        </div>
      </div>
      <div class="bottom">
        <el-button round type="primary" @click="sendMsg">Send</el-button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref } from "vue";
const emit = defineEmits(["addPic", "sendMsg"]);
const input = ref("");

function addPhoto() {
  emit("addPic");
}
function clearText() {
  input.value = "";
}
function saveToSession(msg) {}
function sendMsg() {
  emit("sendMsg", input.value, "text");
  clearText();
}
function keyDown(e) {
  if(e.ctrlKey && e.keyCode==13) {
	  sendMsg();
	}
}
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 75vw;
  height: 10px;
}
#left {
  background-color: transparent;
  width: 100%;
  height: 100%;
}
#right {
  height: 100%;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  justify-content: space-between;
}
.top {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  width: 100%;
}
.bottom {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  width: 100%;
}
.addp {
  size: 500px;
  color: cadetblue;
}
textarea:valid {
  border: 2px solid transparent;
  background-color: transparent;
  font-size: larger;
  width: 100%;
  height: 100%;
  resize: none;
}
::placeholder {
  color: darkgray;
}
</style>
