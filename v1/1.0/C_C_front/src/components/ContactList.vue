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
      <el-scrollbar height="77.5vh" class="scroll-bar">
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
                    <el-badge is-dot class="item" type="success">
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
                  <el-button class="popLabel" text @click="toChat(member)">{{
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
                    <el-button-group>
                      <el-button
                        v-if="member.state == 1"
                        class="button"
                        id="b2"
                        round
                        type="primary"
                        :size="btnSize"
                        @click="unset(member.id)"
                        >{{ $t("contactList.unset") }}</el-button
                      >
                      <el-button
                        v-else
                        class="button"
                        id="b2"
                        round
                        type="success"
                        :size="btnSize"
                        @click="hide(member.id)"
                        >{{ $t("contactList.hide") }}</el-button
                      >
                      <el-button
                        v-if="member.state == 2"
                        class="button"
                        id="b3"
                        round
                        type="primary"
                        :size="btnSize"
                        @click="unset(member.id)"
                        >{{ $t("contactList.unset") }}</el-button
                      >
                      <el-button
                        v-else
                        class="button"
                        id="b3"
                        round
                        type="info"
                        :size="btnSize"
                        @click="mute(member.id)"
                        >{{ $t("contactList.mute") }}</el-button
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
import { reactive, ref } from "vue";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import { Delete } from "@element-plus/icons-vue";
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
const props = defineProps({
  isFriend: Boolean,
  showAll: Boolean,
  param: {
    loading: Boolean,
    nodata: Boolean,
    page: {
      pageSize: Number,
      pageNum: Number,
    },
  },
});
const store = useUserStore();
const { token } = storeToRefs(store);
const btnSize = "default";
var list = reactive([]);
var selectMember = reactive({});
const dialogVisible = ref(false);
const bgColor = reactive({
  backgroundColor:'#fef0f0'
});
const { t } = useI18n();
function test() {
  const testList = [
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
  if (!props.param.loading) {
    props.param.loading = true;
    console.log("load");
    list.push(...testList);
    console.log("list.length: " + list.length);
    props.param.page.pageNum += 1;
  }
  props.param.loading = false;
}
function load() {
  if (!props.param.loading) {
    props.param.loading = true;
    let result;
    if (props.isFriend) {
      result = showFriendList(token, id, props.showAll);
    } else {
      result = showGroupList(token, id, props.showAll);
    }
    result
      .then((res) => {
        if (res.data.success) {
          if (res.data.data.length <= 0) {
            props.param.nodata = true;
          } else {
            props.param.nodata = false;
            list = list.concat(res.data.data);
            props.param.page.pageNum += 1;
          }
        } else {
          this.$message({
            type: "error",
            message: res.data.msg,
            showClose: true,
          });
        }
      })
      .catch((err) => {
        if (props.isFriend) {
          let msg = t("contactList.friend.ListError");
        } else {
          let msg = t("contactList.group.ListError");
        }
        this.$message({
          type: "error",
          message: msg,
          showClose: true,
        });
        console.log(err);
      })
      .finally(() => {
        this.loading = false;
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
          }
        }
      } else {
        this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
      }
    })
    .catch((err) => {
      if (props.isFriend) {
        let msg = t("contactList.friend.hideError");
      } else {
        let msg = t("contactList.group.hideError");
      }
      this.$message({
        type: "error",
        message: msg,
        showClose: true,
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
        this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
      }
    })
    .catch((err) => {
      if (props.isFriend) {
        let msg = t("contactList.friend.muteError");
      } else {
        let msg = t("contactList.group.muteError");
      }
      this.$message({
        type: "error",
        message: msg,
        showClose: true,
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
        this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
      }
    })
    .catch((err) => {
      if (props.isFriend) {
        let msg = t("contactList.friend.unsetError");
      } else {
        let msg = t("contactList.group.unsetError");
      }
      this.$message({
        type: "error",
        message: msg,
        showClose: true,
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
        this.$message({
          type: "error",
          message: res.data.msg,
          showClose: true,
        });
      }
    })
    .catch((err) => {
      if (props.isFriend) {
        let msg = t("contactList.friend.delError");
      } else {
        let msg = t("contactList.group.delError");
      }
      this.$message({
        type: "error",
        message: msg,
        showClose: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function toChat(member) {
  if (props.isFriend) {
    router.push({ name, params: { member } });
  } else {
    router.push({ name, params: { member } });
  }
}
onMounted(()=> {
  if(props.isFriend) {
    bgColor.backgroundColor = '#fef0f0';
  } else {
     bgColor.backgroundColor = '#faecd8';
  }
})
</script>
<style>
.main-scroll {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
}
.colm {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  text-align: center;
}
.scroll-bar {
  margin-top: 0%;
}
.infinite-list {
  margin-left: -45%;
}
.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  min-height: 70px;
  min-width: 70px;
  background-color: v-bind('bgColor.backgroundColor');
  text-align: center;
}
.all {
  overflow: clip;
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
</style>
