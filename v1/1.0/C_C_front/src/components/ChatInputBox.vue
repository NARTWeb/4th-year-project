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
          <el-upload
            ref="uploadRef"
            class="upload-demo"
            :on-change="addPhoto"
            action=""
            :auto-upload="false"
            accept="image/jpeg,image/png,image/jpg"
          >
            <template #trigger>
              <el-icon class="addp" :size="25"
                ><Picture /></el-icon></template
          ></el-upload>
        </div>
      </div>
      <div class="bottom">
        <el-button round type="primary" @click="sendMsg">Send</el-button>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref, watch} from "vue";
import type { UploadInstance } from 'element-plus'
import { uploadPic } from "../api/upload";
import { ElMessage } from "element-plus";
import { useI18n } from "vue-i18n";

const emit = defineEmits(["addPic", "sendMsg"]);
const { t } = useI18n();
const input = ref("");
const uploadRef = ref<UploadInstance>();
const uploaded = ref(false);
const img = ref('https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100');

function addPhoto() {
  //uploadFun();
  uploaded.value = true;
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
  if (e.ctrlKey && e.keyCode == 13) {
    sendMsg();
  }
}
function uploadFun() {
  uploadPic(uploadRef)
    .then((res) => {
      if (res.data.success) {
        img.value = res.data.data;
      } else {
        ElMessage({
          type: "error",
          message: res.data.msg,
          showClose: true,
          grouping: true,
        });
      }
    })
    .catch((err) => {
      ElMessage({
        type: "error",
        message: t('chatInputBox.uploadPicError'),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    }).finally(
        emit("addPic", img.value)
    );
}
watch(uploaded, (newValue) => {
  if(newValue) {
    uploadFun();
    uploadRef.value!.clearFiles();
    uploaded.value = false;
  }
})
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
