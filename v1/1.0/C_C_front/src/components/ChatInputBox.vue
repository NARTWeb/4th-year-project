<template>
  <div class="all">
    <div class="top">
      <div>
        <el-icon class="addp" :size="25" @click="addPhoto"><Picture /></el-icon>
      </div>
      <div><span style="color: darkgray"> Add Picture</span></div>
    </div>

    <div id="mid">
      <textarea rows="6" placeholder="Say something here..."></textarea>
    </div>

    <div class="bottom">
      <el-button round type="primary" @click="sendMsg">Send</el-button>
    </div>
  </div>
</template>
<script setup>
const emit = defineEmits(["addPic"]);

function addPhoto() {
  emit("addPic");
}
function clearText() {}
function saveToSession(msg) {}
function sendMsg() {}
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
  width: 100vw;
}
#mid {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  background-color: transparent;
  width: 100vw;
}
.top {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  width: 100vw;
}
.bottom {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  width: 100vw;
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
