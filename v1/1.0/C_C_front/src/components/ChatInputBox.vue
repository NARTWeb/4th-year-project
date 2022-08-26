<template>
  <div class="all">
    <div class="top">
      <div>
        <el-icon class="addp" :size="25" @click="addPhoto"><Picture /></el-icon>
      </div>
      <div><span style="color: darkgray"> Add Picture</span></div>
    </div>

    <div id="mid">
      <textarea rows="6" v-model="input" style="outline:none;" placeholder="Say something here..."></textarea>
    </div>

    <div class="bottom">
      <el-button round type="primary" @click="sendMsg">Send</el-button>
    </div>
  </div>
</template>
<script setup>
import {ref} from 'vue';
const emit = defineEmits(["addPic", "sendMsg"]);
const input = ref('');

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
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
  width: 75vw;
}
#mid {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  background-color: transparent;
  width: 100%;
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
  width: 100%;
  height: 100%;
  resize: none;
}
::placeholder {
  color: darkgray;
}
</style>
