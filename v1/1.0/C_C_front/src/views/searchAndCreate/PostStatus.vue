<template>
  <div class="all">
    <div class="mainpart">
        <div class="top">
            <textarea rows="6" v-model="msg" style="font-size:16px;color:red;font-family:Arial;font-weight:bold;outline:none;" :placeholder="t('postStatus.placeHolder')"></textarea>
        </div>
    </div>
    <div class="pics">
        <div class="mid">
            <span>{{ $t("postStatus.putPic") }}</span>
        </div>
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
            <el-image fit="fill" :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
    </div>
    <div class="bottom">
      <el-button round type="primary" id="post-btn" @click="post">{{ $t("postStatus.post") }}</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { useI18n } from "vue-i18n";
import  useUserStore from "@/stores/userStore";
import { storeToRefs } from "pinia";
import { postStatus } from "@/api/status";
import { uploadPic } from '@/api/upload';
import { ElMessage } from 'element-plus';

import type { UploadProps, UploadUserFile } from 'element-plus'

const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const counter = ref(0);

const fileList = ref<UploadUserFile[]>([
])
var pics = String[''];
var msg = ref("");

function uploadPics() {
  for(let i=0; i<fileList.value.length; i++) {
    uploadPic(fileList.value[i])
    .then((res) => {
      if(res.data.success) {
        pics.push(res.data.data);
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
    })
  }
}

function post(){
    uploadPics();
    postStatus(token, msg.value, pics)
    .then((res) =>{
        if(res.data.success) {
          alert(t('postStatus.succeed'))
        }else{
          ElMessage({
            type: "error",
            message: t('postStatus.err'),
            showClose: true,
          });
        }
    })
    .catch((err) => {
        ElMessage({
            type: "error",
            message: t('postStatus.err'),
            showClose: true,
        });
        console.log(err);
    })
    .finally(() => {
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
  justify-content: space-around;
  flex-flow: column nowrap;
  align-items: center;
  width: 100%;
  height: 100%;
}
.mainpart {
  flex: 3 3 auto;
  margin-left: 10%;
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
  width: 100%;
}
.top {
  width: 100%;
}
.pics {
  flex: 3 3 auto;
  margin-left: 10%;
  width: 100%;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column nowrap;
  justify-content: flex-start;
  align-items: flex-start;
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
  flex: auto;
  padding: 10px;
  width: 100%;
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: center;
}
#post-btn {
  width: 10%;
  min-width: 60px;
  height: 50%;
  min-height: 30px;
}
</style>
