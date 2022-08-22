<template>
  <div>
    <el-row>
      <div id="topRight">
        <el-icon v-if="isGroup" @click="popSetting"><Setting/></el-icon>
        <el-icon v-else @click="popSetting"><Setting /></el-icon>
      </div>

        <div>
            <div v-if="isMyMsg" id="right">
                <img :src="avatar" />
                <span>{{ name }}</span>
                <span>{{ message }}</span>
                <span>{{ time }}</span>
            </div>

            <div v-else id="left">
            <img :src="avatar" />
            <span>{{ name }}</span>
            <span>{{ message }}</span>
            <span>{{ time }}</span>
            </div>

            
        </div>
      
    </el-row>
  </div>
</template>
<script setup>
const props = defineProps({
  avatar: String,
  name: String,
  message: String,
  time: Date,
  isMe: Boolean,
  key: String,
});

function popSetting() {}
function isGroup() {}
function isMyMsg(){}
</script>
<style>
</style>