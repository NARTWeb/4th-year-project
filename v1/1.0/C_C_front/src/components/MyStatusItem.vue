<template>
  <div class="all">
    <div id="left">
      <div>
        <span class="text">{{ message }}</span>
      </div>
      <div id="mid">
        <div v-for="pic in props.pictures" :key="pic">
          <el-image class="img" :src="img" :preview-src-list="props.pictures"/>
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
#icon {
    color: red;
}
#icon:hover {
    color: black;
}
</style>
