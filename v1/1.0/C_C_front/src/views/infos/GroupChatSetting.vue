<template>
  <div class="all flex">
    <div class="avatar-and-members flex">
      <div class="avatar flex">
        <div class="this-font">{{ $t("groupSetting.groupAvatar") }}</div>
        <div class="g-avatar">
          <el-image
            class="g-avatar"
            :src="gAvatar"
            :preview-src-list="[gAvatar]"
            fit="cover"
          />
        </div>
        <div class="add-btn">
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
      <div class="member flex">
        <div class="this-font">{{ $t("groupSetting.groupMember") }}</div>
        <el-scrollbar wrap-class="member-scroll-bar" class="scroll">
          <div class="scroll-bar flex">
            <div
              v-for="member in memberList"
              :key="member.id"
              class="scroll-item"
            >
              <div :class="imgParent" @click="onDel(member.id)">
                <img :src="member.avatar" class="imgClass" />
              </div>
              <div class="memberName">{{ member.uname }}</div>
            </div>
            <div>
              <el-avatar
                :size="60"
                :icon="Plus"
                @click="toPopWin"
                class="scroll-item oper"
              />
            </div>
            <div>
              <el-avatar
                :size="60"
                :icon="Minus"
                @click="delMember"
                class="scroll-item oper"
                style="display: none"
              />
            </div>
          </div>
        </el-scrollbar>
      </div>
    </div>
    <div class="changeable">
      <div class="item flex i1">
        <div class="change flex">
          <div class="this-font">{{ $t("groupSetting.groupName") }}</div>
          <el-input
            v-model="gName"
            class="text name-bar"
            :maxlength="20"
            :minlength="1"
            show-word-limit
            size="large"
            @change="changeGName"
          ></el-input>
        </div>
      </div>

      <div class="item flex i2">
        <div class="change flex">
          <div class="this-font">{{ $t("groupSetting.importantNotice") }}</div>
          <el-input
            v-model="gNotice"
            :rows="4"
            resize="none"
            type="textarea"
            :maxlength="200"
            :minlength="1"
            show-word-limit
            class="text notice-bar"
            @change="changeGNotice"
          ></el-input>
        </div>
      </div>
    </div>
    <PopWinFriendList :dialog-visible="dialogFormVisible" :list="inviteList"
      @closeWin="closePop"
      @addFun="addToList"></PopWinFriendList>
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
import { showMemberList } from "@/api/group.js";
import { ElMessage } from "element-plus";
import { Plus, Minus } from "@element-plus/icons-vue";
import { changeGroupInfo } from "@/api/group";
import { uploadPic } from "@/api/upload";
import PopWinFriendList from "@/views/searchAndCreate/PopWinFriendList.vue";
import { sendGroupInvite } from "@/api/group";

const store = useUserStore();
const { token } = storeToRefs(store);
const gId = ref(store.getGroupId);
const gName = ref(store.getGroupName);
const gNotice = ref(store.getNotice);
const gAvatar = ref(store.getGroupAvatar);
const { t } = useI18n();
const counter = ref(0);
const memberList = reactive([]);
const inviteList = reactive([]);
var imgParent = ref("circle");
const uploadRef = ref("");
const dialogFormVisible = ref(false);

function toPopWin() {
  dialogFormVisible.value = true;
}
function closePop() {
  dialogFormVisible.value = false;
}
function test() {
  const testList = [
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
      state: 2,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
      state: 1,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
      state: 1,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
      state: 1,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
    {
      id: "3642178321",
      uname: "mike",
      avatar:
        "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
      state: 0,
      notice: "dhsajkdshajdklhsjlafk",
    },
  ];
  for (let i = 0; i < 5; i++) {
    memberList.push(...testList);
  }
}
function uploadFun() {
  uploadPic(uploadRef)
    .then((res) => {
      if (res.data.success) {
        gAvatar.value = res.data.data;
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
function getMember() {
  showMemberList(token.value, gId.value)
    .then((res) => {
      if (res.data.success) {
        memberList.push(...res.data.data);
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
        message: t("groupSetting.getMemberError"),
        showClose: true,
        grouping: true,
      });
    });
}
function changeGName() {
  changeInfo(t("groupSetting.changeName"), t("groupSetting.changeNameError"));
}
function changeGNotice() {
  changeInfo(
    t("groupSetting.changeNotice"),
    t("groupSetting.changeNoticeError")
  );
}
function changeGAvatar() {
  changeInfo(
    t("groupSetting.changeAvatar"),
    t("groupSetting.changeAvatarError")
  );
}
function changeInfo(successMsg, ErrorMsg) {
  const gInfo = {
    id: gId.value,
    name: gName.value,
    avatar: gAvatar.value,
    notice: gNotice.value,
  };
  changeGroupInfo(token.value, gInfo)
    .then((res) => {
      if (res.data.success) {
        ElMessage({
          type: "success",
          message: successMsg,
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
        message: ErrorMsg,
        showClose: true,
        grouping: true,
      });
      console.log(err);
    });
}
function delMember() {
  imgParent.value =
    imgParent.value == "circle" ? "del-circle circle" : "circle";
}
function onDel() {
  if (imgParent.value == "circle") {
    return;
  }
}
function addToList(obj) {
  inviteList.push(obj);
  let inviteInfo = {
      groupId: gId.value,
      receiverId: obj.id,
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
function showHover() {
  alert("hover");
}
onMounted(() => {
  getMember();
});
</script>
<style scoped>
.this-font {
  font-size: xx-large;
}
.flex {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.avatar {
  justify-content: flex-start;
  height: 100%;
}
.all {
  height: 100%;
}
.member {
  justify-content: flex-start;
  margin-left: 5vw;
  margin-right: 20px;
}
@media screen and (min-width: 1100px) {
  .member {
    width: 60vw;
  }
}
@media screen and (min-height: 800px) {
  .member {
    height: 400px;
  }
}
@media screen and (max-height: 799px) and (min-height: 700px) {
  .member {
    height: 300px;
  }
}
@media screen and (max-height: 699px) and (min-height: 600px){
  .member {
   height: 200px;
  }
}
@media screen and (max-height: 599px) {
  .member {
    height: 200px;
    margin-top: -50px;
  }
}
.item {
  width: 100%;
}
.change {
  flex-direction: column;
  justify-content: space-around;
  align-items: flex-start;
  height: 10%;
  width: 100%;
}
.avatar-and-members {
  flex-flow: row nowrap;
}
@media screen and (max-height:540px) {
  .avatar-and-members {
    margin-top: -20px;
  }
}
.memberName {
  text-align: center;
}
.scroll-bar {
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
}
.scroll {
  background-color: bisque;
  padding: 10px;
  border-radius: 20px;
  min-width: 300px;
  width: 100%;
}
.member-scroll-bar {
  width: 50vw;
}
.scroll-item {
  margin-left: 10px;
}
.oper:hover {
  background-color: antiquewhite;
}
img {
  height: 100%;
  width: 100%;
}
.circle {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
  text-align: center;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.del-circle:hover .imgClass {
  content: url(https://cdn-icons-png.flaticon.com/512/56/56889.png);
  height: 50%;
  width: 70%;
}
.del-circle {
  background-color: #dedfe0;
}
.g-avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.add-btn {
  text-align: center;
}
.name-bar {
  width: 33%;
}
.notice-bar {
  width: 50%;
}
@media screen and (min-height: 910px) {
  .changeable {
    width: 100%;
    padding: 0;
    margin: 0 0;
    display: block;
  }
  .name-bar {
    width: 33%;
  }
  .notice-bar {
    width: 50%;
  }
  .i1 {
    top: -40px;
  }
}
@media screen and (max-height: 909px){
  .changeable {
    width: 100%;
    padding: 0;
    margin: 0;
    display: -webkit-flex; /* Safari */
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
    align-items: center;
  }
  .name-bar {
    width: 66%;
  }
  .notice-bar {
    width: 100%;
  }
  @media screen and (max-height: 599px) {
    .changeable {
    width: 100%;
    padding: 0;
    margin-top: -50px;
    }
  }
}
</style>
