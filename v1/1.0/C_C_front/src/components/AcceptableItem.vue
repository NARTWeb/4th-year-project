<template>
  <div>
    <div class="all">
      <div id="left">
        <div class="ava"><el-avatar :src="props.avatar" :size="80" /></div>
        <div class="label">
          <div class="top">
            <span id="s2" v-if="props.groupName == undefined">{{ props.senderName }}</span>
            <span id="s2" v-else>{{ props.groupName}}</span>
            <span id="s4" v-show="props.groupName != undefined">{{ props.senderName }}</span>
          </div>
          <div class="bottom">
            <span id="s3">{{ props.message }}</span>
          </div>
        </div>
      </div>
      <div id="right">
        <div>
          <el-button round type="primary" size="large" @click="acceptBtn">{{
            props.buttonLabel1
          }}</el-button>
        </div>
        <div class="right-btn1">
          <el-button round type="primary" size="large" @click="rejectBtn">{{
            props.buttonLabel2
          }}</el-button>
        </div>
      </div>
    </div>
    <el-divider />
  </div>
</template>

<script setup>
const props = defineProps({
  avatar: String,
  groupName: String,
  senderName: String,
  message: String,
  buttonLabel1: String,
  buttonLabel2: String,
  id: String,
});
const emit = defineEmits(["accept", "reject"]);

function acceptBtn() {
  emit("accept", props.id);
}
function rejectBtn() {
  emit("reject", props.id);
}
</script>

<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-around;
  align-items: center;
}
#left {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  align-items: center;
}
.top {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#right {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.ava {
  margin-left: 5vw;
}
.label {
  margin-left: 20px;
}
span {
  font-size: x-large;
}
#s2 {
  font-weight: 600;
}
#s3 {
  float: left;
}
#s4 {
  margin-left: 20px;
  color:  #c8c9cc;
}
.right-btn1 {
  margin-left: 5vw;
  margin-right: 10vw;
}
</style>
