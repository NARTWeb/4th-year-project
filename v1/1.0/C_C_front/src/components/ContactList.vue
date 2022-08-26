<template>
  <div class="all">
    <el-dialog v-model="dialogVisible" title="Tips" width="30%">
      <span>This is a message {{ selectMember.name }}</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="del(selectMember.id)"
            >Confirm</el-button
          >
        </span>
      </template>
    </el-dialog>
    <div class="main-scroll">
      <el-scrollbar height="77.5vh" id="scroll-bar" wrap-style="min-height:400px;">
        <ul v-infinite-scroll="test" class="infinite-list">
          <li
            v-for="member in list"
            :key="member.id"
            class="infinite-list-item"
          >
            <div style="display: flex; align-items: center">
              <el-popover placement="right" :width="30" trigger="click">
                <template #reference>
                  <div class="colm">
                    <el-badge is-dot class="item" :type="checkType(member.state)">
                      <el-avatar
                        v-if="props.isFriend"
                        :icon="UserFilled"
                        @click="selectMember = member"
                      />
                      <el-avatar
                        v-else
                        size="large"
                        :icon="UserFilled"
                        @click="selectMember = member"
                      />
                    </el-badge>
                  </div>
                </template>
                <div>
                  <el-button class="popLabel" text @click="toChat(member.id)">{{
                    member.name
                  }}</el-button>
                  <el-button
                    type="danger"
                    :icon="Delete"
                    circle
                    class="button"
                    size="small"
                    id="b1"
                    @click="dialogVisible = true"
                  />
                  <div id="bt">
                    <el-button-group size="large">
                      <el-button
                        v-if="member.state == 1"
                        class="button"
                        round
                        type="primary"
                        :size="btnSize"
                        @click="unset(member.id)"
                        >&nbsp;{{ $t("contactList.unset") }}&nbsp;</el-button
                      >
                      <el-button
                        v-else
                        class="button"
                        round
                        type="success"
                        :size="btnSize"
                        @click="hide(member.id)"
                        >&nbsp;{{ $t("contactList.hide") }}&nbsp;</el-button
                      >
                      <el-button
                        v-if="member.state == 2"
                        class="button"
                        round
                        type="primary"
                        :size="btnSize"
                        @click="unset(member.id)"
                        >&nbsp;{{ $t("contactList.unset") }}&nbsp;</el-button
                      >
                      <el-button
                        v-else
                        class="button"
                        round
                        type="info"
                        :size="btnSize"
                        @click="mute(member.id)"
                        >&nbsp;{{ $t("contactList.mute") }}&nbsp;</el-button
                      >
                    </el-button-group>
                  </div>
                </div>
              </el-popover>
            </div>
          </li>
        </ul>
      </el-scrollbar>
    </div>
  </div>
</template>
<script setup>
import { reactive, ref, watch } from "vue";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import { Delete } from "@element-plus/icons-vue";
import { ElMessage } from 'element-plus'
import {
  showFriendList,
  hideFriend,
  muteFriend,
  delFriend,
  unsetFriend,
} from "../api/friend";
import {
  showGroupList,
  hideGroup,
  muteGroup,
  leaveGroup,
  unsetGroup,
} from "../api/group";
import { UserFilled } from "@element-plus/icons-vue";
import { useI18n } from "vue-i18n";
import { onMounted } from 'vue'
import { min } from "lodash";
import { useRouter } from "vue-router";

const router = useRouter();
const props = defineProps({
  isFriend: Boolean,
  showAll: Boolean,
  param: {
    page: {
      pageSize: Number,
      pageNum: Number,
    },
  },
});
const loading = ref(false);
const nodata = ref(false);
const pageN = ref(props.param.page.pageNum);
const store = useUserStore();
const { token} = storeToRefs(store);
const minW = ref('60px');
const btnSize = "default";
var list = reactive([]);
var selectMember = reactive({});
const dialogVisible = ref(false);
const bgColor = reactive({
  backgroundColor:'#fef0f0'
});
const { t } = useI18n();
function checkType(state) {
  if(state == 2) {
    return "info";
  } else if(state == 1) {
    return "success";
  } else if(state == 0) {
    return "danger";
  }
  return "success";
}
function test() {
  const testList = [
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 2,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 1,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
    {
      id: "3642178321",
      name: "mike",
      avatar: "hdfsjakdsa",
      state: 0,
    },
  ];
  if (props.param.page.pageNum == 10) {
    return;
  }
  if (!loading.value) {
    loading.value = true;
    console.log("load");
    list.push(...testList);
    console.log("list.length: " + list.length);
    props.param.page.pageNum += 1;
  }
  loading.value = false;
}
function load() {
  if (!loading.value && !nodata) {
    loading.value = true;
    let result;
    let page = {
      pageSize: props.param.page.pageSize,
      pageNum: pageN.value
    };
    if (props.isFriend) {
      result = showFriendList(token, page, props.showAll);
    } else {
      result = showGroupList(token, page, props.showAll);
    }
    result
      .then((res) => {
        if (res.data.success) {
          if (res.data.data.length <= 0) {
            nodata.value = true;
          } else {
            nodata.value = false;
            list.push(...res.data.data);
            pageN.value += 1;
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
        let msg
        if (props.isFriend) {
          msg = t("contactList.friend.ListError");
        } else {
          msg = t("contactList.group.ListError");
        }
        ElMessage({
          type: "error",
          message: msg,
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
function hide(id) {
  let result;
  if (props.isFriend) {
    result = hideFriend(token, id);
  } else {
    result = hideGroup(token, id);
  }
  result
    .then((res) => {
      if (res.data.success) {
        for (var i = 0; i < list.length; i++) {
          if (list[i].id == id) {
            list.splice(i, 1);
            break;
          }
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
      let msg
      if (props.isFriend) {
        msg = t("contactList.friend.hideError");
      } else {
        msg = t("contactList.group.hideError");
      }
      ElMessage({
        type: "error",
        message: msg,
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function mute(id) {
  let result;
  if (props.isFriend) {
    result = muteFriend(token, id);
  } else {
    result = muteGroup(token, id);
  }
  result
    .then((res) => {
      if (res.data.success) {
        for (var i = 0; i < list.length; i++) {
          if (list[i].id == id) {
            // --------------------------------------------待商讨
            list.splice(i, 1);
          }
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
      let msg
      if (props.isFriend) {
        msg = t("contactList.friend.muteError");
      } else {
        msg = t("contactList.group.muteError");
      }
      ElMessage({
        type: "error",
        message: msg,
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function unset(id) {
  let result;
  if (props.isFriend) {
    result = unsetFriend(token, id);
  } else {
    result = unsetGroup(token, id);
  }
  result
    .then((res) => {
      if (res.data.success) {
        //-------------------------------------------?
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
      let msg
      if (props.isFriend) {
        msg = t("contactList.friend.unsetError");
      } else {
        msg = t("contactList.group.unsetError");
      }
      ElMessage({
        type: "error",
        message: msg,
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function del(id) {
  dialogVisible.value = false;
  let result;
  if (props.isFriend) {
    result = delFriend(token, id);
  } else {
    result = leaveGroup(token, id);
  }
  result
    .then((res) => {
      if (res.data.success) {
        for (var i = 0; i < list.length; i++) {
          if (list[i].id == id) {
            list.splice(i, 1);
          }
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
      let msg
      if (props.isFriend) {
        msg = t("contactList.friend.delError");
      } else {
        msg = t("contactList.group.delError");
      }
      ElMessage({
        type: "error",
        message: msg,
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function toChat(id) {
  if (props.isFriend) {
    router.push({ name:'chatRoom', params: {id: 'f'+id} });
  } else {
    router.push({ name:'chatRoom', params: {id: 'g'+id} });
  }
}
onMounted(()=> {
  if(props.isFriend) {
    bgColor.backgroundColor = '#fef0f0';
  } else {
     bgColor.backgroundColor = '#faecd8';
     minW.value = '90px';
  }
})
watch (
  () => list.length,
  (length) => {
    if(length <= props.param.page.pageSize ) {
      load();
    }
  }
)
</script>
<style scoped>
.main-scroll {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  min-width: v-bind(minW);
  min-height: 400px;
  z-index: 5;
}
.colm {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  text-align: center;
  margin-left: -25px;
}
#scroll-bar {
  margin-top: 0%;
  min-height: 400px;
  overflow-y: visible;
}
.infinite-list {
  margin-left: -45%;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  z-index: 4;
  min-height: 400px;
}
.infinite-list-item {
  display: -webkit-flex; /* Safari */
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  min-height: v-bind(minW);
  min-width: v-bind(minW);
  background-color: v-bind("bgColor.backgroundColor");
  text-align: center;
  z-index: 4;
}
.all {
  overflow: clip;
  min-height: 400px;
}
.popLabel {
  display: inline-block;
  font-size: 20px;
  font-weight: bolder;
  padding-right: 10px;
}
.button {
  margin: 0;
  padding: 0;
}
#bt {
  display: block;
  text-align: center;
  margin-top: 5px;
}
#b1 {
  float: right;
  margin-top: 3px;
}
.el-button:not(#b1) {
  min-width: 40px;
  width: 60px;
}
</style>
