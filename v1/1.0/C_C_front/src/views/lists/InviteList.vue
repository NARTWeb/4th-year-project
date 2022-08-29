<template>
  <div>
    <el-scrollbar height="75vh" id="all">
      <ul v-infinite-scroll="testList" class="infinite-list">
        <li v-for="req in inviteList" :key="req.id">
          <acceptable-item
            :avatar="req.groupAvatar"
            :groupName="req.groupName"
            :senderName="req.senderName"
            :message="req.msg"
            :button-label1="t('inviteList.accept')"
            :button-label2="t('inviteList.reject')"
            :id="req.id"
            @accept="acceptf"
            @reject="rejectf"
          ></acceptable-item>
        </li>
      </ul>
    </el-scrollbar>
  </div>
</template>
<script setup>
import { reactive, ref, watch } from "vue";
import AcceptableItem from "@/components/AcceptableItem.vue";
import  useUserStore from "@/stores/userStore";
import { storeToRefs } from "pinia";
import { useI18n } from "vue-i18n";
import { showGroupInvitions, responseGroupInvite } from "@/api/group";
import { ElMessage } from "element-plus";

const store = useUserStore();
const { token } = storeToRefs(store);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 5,
  pageNum: 0,
});
const { t } = useI18n();
const inviteList = reactive([]);
const counter = ref(0);

function testList() {
  const test = [
    {
      groupAvatar:
        "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      senderName: "tony",
      groupName: "groupName",
      msg: "respond me!!",
      id: (1 + counter.value).toString(),
    },
    {
      groupAvatar:
        "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      senderName: "tony",
      groupName: "groupName",
      msg: "respond me!!",
      id: (2 + counter.value).toString(),
    },
    {
      groupAvatar:
        "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      senderName: "tony",
      groupName: "groupName",
      msg: "respond me!!",
      id: (3 + counter.value).toString(),
    },
    {
      groupAvatar:
        "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      senderName: "tony",
      groupName: "groupName",
      msg: "respond me!!",
      id: (4 + counter.value).toString(),
    },
    {
      groupAvatar:
        "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      senderName: "tony",
      groupName: "groupName",
      msg: "respond me!!",
      id: (5 + counter.value).toString(),
    },
  ];
  if (counter.value < 30) {
    inviteList.push(...test);
    counter.value += 5;
  }
}
function acceptf(id) {
  for (let i = 0; i < inviteList.length; i++) {
    if (id == inviteList[i].id) {
      inviteList.splice(i, 1);
      return;
    }
  }
}
function rejectf(id) {
  for (let i = 0; i < inviteList.length; i++) {
    if (id == inviteList[i].id) {
      inviteList.splice(i, 1);
      return;
    }
  }
}
function searchReqList() {
  if (!nodata.value && !loading.value) {
    loading.value = true;
    showFriendRequests(token, page)
      .then((res) => {
        if (res.data.success) {
          if (res.data.data > 0) {
            inviteList.push(...res.data.data);
            page.pageNum += 1;
          } else {
            nodata.value = true;
          }
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
          message: t("inviteList.loadError"),
          showClose: true,
          grouping: true,
        });
        console.log(err);
      })
      .finally(() => {
        this.loading.value = false;
      });
  }
}
function acceptFun(id) {
  for (let i = 0; i < inviteList.length; i++) {
    if (id == inviteList[i].id) {
      responseFriendReq(token, id, true)
        .then((res) => {
          if (res.data.success) {
            inviteList.splice(i, 1);
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
            message: t("inviteList.acceptError"),
            showClose: true,
            grouping: true,
          });
          console.log(err);
        })
        .finally(() => {
          return;
        });
    }
  }
}
function rejectFun(id) {
  for (let i = 0; i < inviteList.length; i++) {
    if (id == inviteList[i].id) {
      responseFriendReq(token, id, false)
        .then((res) => {
          if (res.data.success) {
            inviteList.splice(i, 1);
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
            message: t("inviteList.rejectError"),
            showClose: true,
            grouping: true,
          });
          console.log(err);
        })
        .finally(() => {
          return;
        });
    }
  }
}
watch(
  () => inviteList.length,
  (length) => {
    if (length <= 5) {
      testList();
    }
  }
);
</script>
<style scoped>
#all {
  padding: 0;
  margin: -10px 0;
}
</style>