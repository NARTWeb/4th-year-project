<template>
  <div>
    <el-container>
      <el-header class="header hh">
        <div class="header h1">
          <div class="header-avatar">
            <el-avatar :size="100" :src="img" />
          </div>
          <div class="header-edit">
            <div class="text">{{ $t("createGroup.avatarLabel") }}</div>
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
                <el-button type="primary" round class="header-btn">{{
                  $t("createGroup.addAvatar")
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
        <div class="header h2">
          <div class="text header-label">{{ $t("createGroup.groupName") }}</div>
          <div class="header-input">
            <el-input
              v-model="groupName"
              :placeholder="placeholder"
              size="large"
            ></el-input>
          </div>
        </div>
      </el-header>
      <el-main class="main">
        <div class="scroll">
          <el-scrollbar class="scroll-main-bar">
            <div class="scroll-bar">
              <div
                v-for="member in inviteList"
                :key="member.id"
                class="scroll-item"
              >
                <el-avatar
                  :size="60"
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                />
                <div class="memberName">{{ member.name }}</div>
              </div>
            </div>
          </el-scrollbar>
        </div>
        <div class="main-btn">
          <el-button type="success" round @click="toPopWin">{{
            $t("createGroup.addMember")
          }}</el-button>
          <el-button type="primary" round @click="create">{{
            $t("createGroup.create")
          }}</el-button>
        </div>
      </el-main>
    </el-container>
    <PopWinFriendList
      :dialog-visible="dialogFormVisible"
      :list="inviteList"
      @closeWin="closePop"
      @addFun="addToList"
    ></PopWinFriendList>
  </div>
</template>
<script setup>
import { computed, reactive, ref, watch } from "vue";
import { useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import { uploadPic } from "@/api/upload";
import { createNewGroup, sendGroupInvite } from "@/api/group";
import { storeToRefs } from "pinia";
import useUserStore from "@/stores/userStore";
import { ElMessage } from "element-plus";
import PopWinFriendList from "./PopWinFriendList.vue";

const dialogFormVisible = ref(false);
const store = useUserStore();
const router = useRouter();
const { token } = storeToRefs(store);
const { t } = useI18n();
const img = ref(
  "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
);
const uploadRef = ref("");
var gName = ref("");
const groupName = computed({
  get() {
    return gName.value;
  },
  set(newValue) {
    gName.value = newValue.trim();
  },
});
const placeholder = computed({
  get() {
    return t("createGroup.groupNameHolder");
  },
});
const inviteList = reactive([]);
const groupId = ref("");
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
        message: t("chatInputBox.uploadPicError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    });
}
function create() {
  let flag = false;
  createNewGroup(token.value, gName.value)
    .then((res) => {
      if (res.data.success) {
        groupId.value = res.data.data;
        flag = true;
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
        message: t("createGroup.createError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    });

  console.log(groupId.value);

  for (let i = 0; i < inviteList.length; i++) {
    let inviteInfo = {
      groupId: groupId.value,
      receiverId: inviteList[i].id,
      message: "",
    };
    sendGroupInvite(token.value, inviteInfo)
      .then((res) => {
        if (!res.data.success) {
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
          message: t("createGroup.inviteError"),
          showClose: true,
          grouping: true,
        });
        console.log(err);
      });
  }
  if(flag) {
    const info = {
    gid: groupId.value,
    note: "",
    gName: gName,
    gAvatar: img.value,
  };
  store.updategroupInfo(info);
  router.push({ name: "chatRoom", params: { id: "g" + groupId.value } });
  }
}
function addToList(obj) {
  inviteList.push(obj);
}
function toPopWin() {
  dialogFormVisible.value = true;
}
function closePop() {
  dialogFormVisible.value = false;
}
</script>
<style scoped>
.header {
  display: -webkit-flex; /* Safari */
  display: flex;
  align-items: center;
}
.hh {
  margin-top: 3vh;
  justify-content: space-between;
}
.h1 {
  flex: 1 1 auto;
}
.h2 {
  flex: 2 2 auto;
  justify-content: flex-end;
}
.header-edit {
  margin-left: 2vw;
}
.header-btn {
  margin-top: 1vh;
}
.header-input {
  margin-left: 2vw;
  width: 20vw;
}
.main {
  margin-top: 3vh;
}
.scroll {
  background-color: bisque;
  border-radius: 20px;
}
.scroll-bar {
  width: 100%;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
}
.scroll-item {
  margin-left: 10px;
  margin-top: 5px;
}
.main-btn {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  align-items: center;
  top: 10px;
  margin: 0;
  padding: 0;
}
.text {
  font-family: "Helvetica Neue";
  font-size: xx-large;
}
.memberName {
  text-align: center;
}
@media screen and (min-height: 850px) {
  .scroll-main-bar {
    height: 45vh;
  }
}
@media screen and (max-height: 849px) and (min-height: 720px) {
  .scroll-main-bar {
    height: 40vh;
  }
}
@media screen and (max-height: 719px) and (min-height: 620px) {
  .scroll-main-bar {
    height: 35vh;
  }
}
@media screen and (max-height: 619px) {
  .scroll-main-bar {
    height: 30vh;
  }
  .main-btn {
    top: 5px;
  }
}
</style>
