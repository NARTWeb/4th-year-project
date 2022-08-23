<template>
  <div>
    <div class="all">
      <div id="left">
        <el-avatar :size="80" :src="props.avatar" />
        <span class="text">{{ props.username }}</span>
      </div>
      <div id="mid">
        <el-button round type="primary" size="large" @click="pressBtn">{{
          props.buttonLabel
        }}</el-button>
      </div>
      <div id="right">
        <el-icon @click="deleteItem" color="red" :size="30" class="icon"
          ><Close
        /></el-icon>
      </div>
    </div>
  </div>
</template>
<script setup>
const props = defineProps({
  avatar: String,
  username: String,
  buttonLabel: String,
  id: String,
});
const emit = defineEmits(["delItem", "btnFunc"]);

function deleteItem() {
  emit("delItem");
}
function pressBtn() {
  emit("btnFunc");
}
</script>
<style scoped>
.all {
  background-color: #d9ecff;
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#mid {
  flex-grow: 1;
}
#right {
  flex-grow: 1;
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: center;
}
#left {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 10px 0;
  margin-left: 20%;
  flex-grow: 6;
}
.text {
  margin-left: 20px;
  font-size: x-large;
  font-weight: 600;
}
.icon:active {
    color: black;
}
</style>
