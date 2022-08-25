<template>
  <div class="all">
    <div class="mainpart">
        <div class="top">
            <textarea rows="6" style="font-size:16px;color:red;font-family:Arial;font-weight:bold;outline:none;" placeholder="How's your day?"></textarea>
        </div>
        <div class="mid">
            <span>put photo here...</span>
        </div>
    </div>
    <div class="pics">
        <el-upload
            v-model:file-list="fileList"
            action=""
            list-type="picture-card"
            mutiple
            show-file-list
            :auto-upload="false"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :limit="9"
        >
            <el-icon><Plus /></el-icon>
        </el-upload>

        <el-dialog v-model="dialogVisible">
            <img w-full :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
    </div>
    <div class="bottom">
      <el-button round type="primary" @click="post">Post</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { useI18n } from "vue-i18n";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import { postStatus } from "../api/status";
import { ElMessage } from 'element-plus';

import type { UploadProps, UploadUserFile } from 'element-plus'

const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const counter = ref(0);

const fileList = ref<UploadUserFile[]>([
])

function post(msg, pics){
    postStatus(token, msg, pics)
    .then((res) =>{
        if(res.data.success) {
          alert("post succeed")
        }else{
          ElMessage({
            type: "error",
            message: res.data.msg,
            showClose: true,
          });
        }
    })
    .catch((err) => {
        ElMessage({
            type: "error",
            message: err.data.msg,
            showClose: true,
        });
        console.log(err);
    })
    .finally(() => {
        alert("post succeed")
    });
}
const dialogImageUrl = ref('')
const dialogVisible = ref(false)

const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
  width: 100%;
}
.mainpart {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
  width: 100%;
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
.bottom {
  padding: 10px;
}
</style>
