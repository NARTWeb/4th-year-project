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
                  jpg/png files with a size less than 500kb
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
          <el-scrollbar height="45vh">
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
          <el-button type="success" round @click="addMember">{{
            $t("createGroup.addMember")
          }}</el-button>
          <el-button type="primary" round @click="create">{{
            $t("createGroup.create")
          }}</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import { uploadPic } from "../api/upload";
import { createNewGroup, sendGroupInvite } from "../api/group";
import { storeToRefs } from "pinia";
import { useUserStore } from "../stores/userStore";
const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const img = ref(
  "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
);
const uploadRef = ref("");
const groupName = ref("");
const placeholder = t("createGroup.groupNameHolder");
const inviteList = reactive([]);
const groupId = ref("");
function uploadFun() {
  alert("here!");
  uploadPic(uploadRef)
    .then((res) => {
      if (res.data.success) {
        img = res.data.data;
      } else {
        this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
      }
    })
    .catch((err) => {
      this.$message({
        type: "error",
        message: err.data.msg,
        showClose: true,
      });
      console.log(err);
    });
}
function addMember() {
  inviteList.push(
    ...[
      {
        id: "783921",
        name: "mike",
        avatar: "dshjaokda",
      },
      {
        id: "8342910",
        name: "mtony",
        avatar: "dshja",
      },
    ]
  );
  console.log(inviteList.value);
}
function create() {
    createNewGroup(token, groupName.value)
    .then((res)=> {
        if(res.data.success) {
            groupId.value = res.data.data;
        } else {
            this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
        }
    })
    .catch((err) => {
      this.$message({
        type: "error",
        message: err.data.msg,
        showClose: true,
      });
      console.log(err);
    });

    for(i=0; i<inviteList.length; i++) {
        let inviteInfo = {
            groupId: groupId.value,
            //receiverId = inviteList[i].id,
            message: "",
        };
        sendGroupInvite(token, inviteInfo)
        .then((res)=> {
            if(!res.data.success) {
                this.$message({
                    type: "error",
                    message: res.data.msg,
                    showClose: true,
                })
            }
        })
        .catch((err)=> {
            this.$message({
                type: "error",
                message: err.data.msg,
                showClose: true,
            });
            console.log(err);
        })
    }
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
}
.text {
  font-family: "Helvetica Neue";
  font-size: xx-large;
}
.memberName {
  text-align: center;
}
</style>
