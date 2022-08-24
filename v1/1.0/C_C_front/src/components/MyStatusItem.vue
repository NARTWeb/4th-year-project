<template>
  <div class="all">
    <div id="left">
      <div>
        <span class="text">{{ message }}</span>
      </div>
      <div id="mid">
        <div v-for="pic in props.pictures" :key="pic">
          <img :src="pic" />
        </div>
      </div>
    </div>
    <div id="right">
      <el-icon id="icon" v-show="isMine" :size="40" @click="delStatus"><Delete /></el-icon>
    </div>
  </div>
</template>
<script setup>
const props = defineProps({
  isMine: Boolean,
  message: String,
  pictures: Array,
  id: String,
});

function delStatus() {
    alert('del');
}
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: row nowrap;
  align-items: center;
  width: 100%;
}

#mid {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  flex-flow: row wrap;
  align-items: center;
  width: 100%;
}
img {
  width: 150px;
  min-width: 100px;
  height: 150px;
  min-height: 100px;
  margin-left: 5px;
}
.text {
  font-size: 1.5em;
  font-weight: 300;
}
#icon {
    color: red;
}
#icon:hover {
    color: black;
}
</style>
