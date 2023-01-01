<template>
  <div>
    <el-upload
      ref="upload"
      :limit="1"
      action="#"
      accept="image/png,image/gif,image/jpg,image/jpeg"
      :file-list="file"
      :auto-upload="false"
      :on-change="handleChange"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-button type="success" @click="submitUpload">upload</el-button>
  </div>
</template>
<script setup lang="ts">
import { uploadPic, deletePic } from "@/api/upload";
import { reactive, ref, watch } from "vue";
import { ElMessage } from "element-plus";
import { useI18n } from "vue-i18n";
import type { UploadInstance, UploadProps, UploadRawFile } from "element-plus";

const { t } = useI18n();

const file = reactive([]);
const upload = ref<UploadInstance>();

function handleChange(f, fileList) {
  let reader = new FileReader();
  reader.readAsDataURL(f.raw);
  reader.onload = (e) => {
    file.push({ name: f.raw.name, url: e.target.result });
  };
  file.push(f.raw);
  fileList = file;
}

function submitUpload() {
  let formData = new FormData();
  let f = file;
  for (let i = 0; i < f.length; i++) {
    formData.append("file", f[i]);
  }

  // send request
  uploadPic(formData, 3)
    .then((res) => {
      if (res.data.success) {
        realAvatar.value = res.data.data;
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
        message: t("chatInputBox.uploadPicError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    });
  file.length = 0;
  upload.value!.clearFiles();
}

function del(url:String) {
  if (url == "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png") {
    return;
  }
  let head = url.lastIndexOf("/") + 1;
  let tail = url.lastIndexOf(".");
  let id = url.substring(head, tail);

  deletePic(id)
    .then((res) => {
      if (res.data.success) {
        ElMessage({
          type: "success",
          message: t("chatInputBox.deletePicSuccess"),
          showClose: true,
          grouping: true,
        });
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
        message: t("chatInputBox.deletePicError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    });
}
</script>
<style scoped></style>
