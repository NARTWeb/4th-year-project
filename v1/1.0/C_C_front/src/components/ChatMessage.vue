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



          <div v-if="isMe" class="bubble right">
            <div v-show="isGroup==false" @click="popInfo">
              <div class="avatar">
                <img :src="avatar"/>
              </div>
            </div>
            <div class="wrap">
              <div class="content">
                <div>{{ message }}</div>
                <div>{{ time }}</div>
              </div>
            </div>
          </div>

          <div class="bubble left">
            <div v-show="isGroup==false" @click="popInfo">
              <div class="avatar">
                <img :src="avatar"/>
              </div>
            </div>
            <div class="wrap">
              <div class="content">
                <div>{{ message }}</div>
                <div>{{ time }}</div>
              </div>
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

function popInfo(){}
function isGroup() {}

</script>
<style>
#left{

}
#right{

}

.bubble {
      padding: 6px;
      overflow: hidden;
      position: relative;
    }
    .bubble:after {
      clear: both;
      content: '';
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
      width: auto;
      padding: 0.7em 0.5em;
      font-size: 16px;
      border-radius: 6px;
      border: 1px solid;
      position: relative;
      display: inline-block;
    }
    .bubble .content:before {
      content: '';
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
    }
    .bubble.right {
      text-align: right;
    }
    .bubble.left .wrap {
      margin-left: 56px;
      margin-right: 56px;
    }
    .bubble.left .content {
        background: #fafafa;
        border-color: #c6c6c6;
    }
    .bubble.left .content:before {
      left: -6px;
      top: 16px;
      transform: rotate(135deg);
    }
    .bubble.right .wrap {
      margin-right: 56px;
      margin-left: 56px;
    }
    .bubble.right .content {
      background: #b3e866;
      border-color: #9ab96b;
      text-align: left;
    }
    .bubble.right .content:before {
      right: -6px;
      top: 16px;
      transform: rotate(-45deg);
    }
</style>