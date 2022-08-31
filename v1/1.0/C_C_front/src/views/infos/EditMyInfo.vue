<template>
  <div class="all flex">
    <div class="big center">
      <el-avatar class="img" :src="realAvatar" />
      <div class="small center">
        <el-upload
          ref="uploadRef"
          class="upload-demo"
          accept="image/jpeg,image/png,image/jpg"
          action="string"
          :http-request="uploadFun"
          :limit="1"
          :auto-upload="true"
          :show-file-list="false"
        >
          <template #trigger>
            <el-button type="primary" round class style="margin-top: 1vh">{{
              $t("groupSetting.addAvatar")
            }}</el-button>
          </template>
          <template #tip>
            <div class="el-upload__tip">
              {{ $t("buttons.picInfo") }}
            </div>
          </template>
        </el-upload>
      </div>
    </div>

    <div class="flex text">
      <div class="item-div flex item1">
        <div class="item">
          <info-item
            :label="t('infoItem.userName')"
            :reg="tt.reg1"
            can-change
            @changeFun="changef"
          ></info-item>
        </div>
        <div class="item">
          <info-item
            :label="t('infoItem.birthday')"
            :reg="tt.reg2"
            can-change
            @changeFun="changef"
          ></info-item>
        </div>
      </div>
      <div class="item-div flex">
        <div class="item">
          <info-item
            :label="t('infoItem.password')"
            can-change
            is-pwd
            @changePwd="changep"
          ></info-item>
        </div>
        <div class="item">
          <info-item
            :label="t('infoItem.phone')"
            :reg="tt.reg4"
            can-change
            @changeFun="changef"
          ></info-item>
        </div>
      </div>
      <div class="item-div flex">
        <div class="item">
          <info-item
            :label="t('infoItem.email')"
            :reg="tt.reg5"
            can-change
            @changeFun="changef"
          ></info-item>
        </div>
        <div class="item">
          <info-item
            :label="t('infoItem.address')"
            :reg="tt.reg6"
            can-change
            @changeFun="changef"
          ></info-item>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { onMounted } from "vue";
import { reactive, ref, watch } from "vue";
import { useI18n } from "vue-i18n";
import useUserStore from "@/stores/userStore";
import { storeToRefs } from "pinia";
import InfoItem from "@/components/InfoItem.vue";
import { format } from "@/utils/time.js";
import { uploadPic } from "@/api/upload";

const store = useUserStore();
const { avatar, name, email, tel, birthday, address} = storeToRefs(store);
const { t } = useI18n();
const realAvatar = ref(avatar);
const counter = ref(0);
const uploadRef = ref("");
const userInfo = reactive({
    uname: name,
    avatar: avatar,
    email: email, 
    phone: tel,
    address: address,
    birthday: birthday,
    oldPwd: '',
    pwd: ''
});

const tt = {
  reg1: /^[a-z0-9_-]{3,16}$/,
  reg2: /^\d{4}-\d{2}-\d{2}$/,
  reg4: /^[1-9]\d{2}\s\d{3}\s\d{4}/,
  reg6: /^[#.0-9a-zA-Z\s,-]+$/,
  reg5: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
};

function uploadFun() {
  uploadPic(uploadRef)
    .then((res) => {
      if (res.data.success) {
        realAvatar.value = res.data.data;
      } else {
        ElMessage({
          type: "error",
          message: t("chatInputBox.uploadPicError"),
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
  userInfo.avatar = realAvatar;
  store.changeUserInfo(userInfo);
}

function changef(label, input) {
  switch(label) {
    case tt.label1: userInfo.uname = input; break;
    case tt.label2: userInfo.birthday = input; break;
    case tt.label4: userInfo.phone = input; break;
    case tt.label5: userInfo.email = input; break;
    case tt.label6: userInfo.address = input; break;
  }
  store.changeUserInfo(userInfo);
}

function changep(oldPwd, newPwd) {
  userInfo.oldPwd = oldPwd;
  userInfo.pwd = newPwd;
  store.changeUserInfo(userInfo);
}

onMounted(() => {
  store.getUserInfo();
});
</script>
<style scoped>
.all {
  height: 100%;
}
.text {
  height: 75%;
}
.flex {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column nowrap;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.item-div {
  flex-flow: row nowrap;
  justify-content: space-around;
  height: 33.3%;
}
.item {
  width: 50%;
}
.center {
  text-align: center;
}
@media screen and (min-height: 670px) {
  .img {
    width: 150px;
    height: 150px;
  }
}
@media screen and (max-height: 669px) {
  .img {
    width: 100px;
    height: 100px;
  }
}
@media screen and (max-height: 599px) {
  .small {
    float: right;
    margin-left: 30px;
    margin-top: 20px;
  }
}
</style>
