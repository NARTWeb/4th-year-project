<div id="top">
        <el-icon v-if="isGroup" @click="popSetting"><Setting/></el-icon>
        <el-icon v-else @click="popSetting"><Setting /></el-icon>
</div>

<template>
  <div>

      <div>
          <div v-if="isMe" id="right">

            <div>
              <span>{{ name }}</span>
              <img :src="avatar" />
            </div>

            <div>
              <span>{{ message }}</span>
            </div>

            <div>
              <span>{{ time }}</span>
            </div> 

          </div>

          <div v-else id="left">

            <div @click="popInfo">
              <span>{{ name }}</span>
              <img :src="avatar" />
            </div>

            <div>
              <span>{{ message }}</span>
            </div>

            <div>
              <span>{{ time }}</span>
            </div> 

          </div>

          <div class="bubble right">
            <div @click="popInfo">
              <img :src="avatar" />
            </div>
            <div class="avatar" ><img :src="avatar"/></div>
            <div class="wrap">
              <div class="content"><span>{{ message }}</span><span>{{ time }}</span></div>
            </div>
          </div>
            
      </div>
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

</script>
<style>

</style>