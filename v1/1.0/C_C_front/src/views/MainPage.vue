<template>
  <div>
    <el-container>
      <el-header>
        <div class="header-row">
          <div class="col1">
            <el-image
              src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
              fit="fill"
              style="width: 220px; height: 50px; margin-left: -6%"
            />
          </div>
          <div class="col2">
            <el-input
              v-model="searchInput"
              size="large"
              class="col2-input"
              placeholder="search"
              @change="toSearch"
              :suffix-icon="Search"
            ></el-input>
            <el-button :icon="Search" class="col2-btn" @click="toSearch" circle />
          </div>
          <div class="col3">
            <el-dropdown trigger="click">
              <span class="el-dropdown-link">
                <el-avatar
                  :src="avatar"
                  fit="contain"
                  style="width: 50px; height: 50px"
                />
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="postStatus">{{
                    $t("main.menu.item1")
                  }}</el-dropdown-item>
                  <el-dropdown-item @click="checkStatus">{{
                    $t("main.menu.item2")
                  }}</el-dropdown-item>
                  <el-dropdown-item v-if="friendShowAll" @click="showAllFriends">{{
                    $t("main.menu.item3_hide")
                  }}</el-dropdown-item>
                  <el-dropdown-item v-else @click="showAllFriends">{{
                    $t("main.menu.item3")
                  }}</el-dropdown-item>
                  <el-dropdown-item v-if="groupShowAll" @click="showAllGroups">{{
                    $t("main.menu.item4_hide")
                  }}</el-dropdown-item>
                  <el-dropdown-item v-else @click="showAllGroups">{{
                    $t("main.menu.item4")
                  }}</el-dropdown-item>
                  <el-dropdown-item @click="editInfo">{{
                    $t("main.menu.item5")
                  }}</el-dropdown-item>
                  <el-dropdown-item @click="logout">{{
                    $t("main.menu.item6")
                  }}</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <el-main>
        <el-container>
          <el-header class="header">
            <div class="header-text t1">{{ $t("main.bar.item1") }}</div>
            <div class="header-text t2">{{ $t("main.bar.item2") }}</div>
            <div>
              <el-menu
                class="el-menu-demo"
                mode="horizontal"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
                :ellipsis="false"
                @select="menuClick"
              >
                <el-menu-item index="findNew" class="menuItem">{{
                  $t("main.bar.item3")
                }}</el-menu-item>
                <el-menu-item index="reqList" class="menuItem">{{
                  $t("main.bar.item4")
                }}</el-menu-item>
                <el-menu-item index="inviteList" class="menuItem">{{
                  $t("main.bar.item5")
                }}</el-menu-item>
                <el-menu-item index="statusList" class="menuItem">{{
                  $t("main.bar.item6")
                }}</el-menu-item>
              </el-menu>
            </div>
          </el-header>
          <el-main class="main-main">
            <el-container>
              <el-aside width="10vw" id="mainAside">
                <div class="aside-main">
                  <div class="main-bar" id="m1">
                    <contact-list
                      isFriend
                      :showAll="friendShowAll"
                      :param="friendParam"
                    ></contact-list>
                  </div>
                  <div class="main-bar" id="m2">
                    <contact-list
                      :showAll="groupShowAll"
                      :param="groupParam"
                    ></contact-list>
                  </div>
                </div>
              </el-aside>
              <el-main width="90vw" class="mainPart">
                <router-view></router-view>
              </el-main>
            </el-container>
          </el-main>
        </el-container>
      </el-main>
    </el-container>
  </div>
</template>
<script setup>
import { reactive, ref } from "vue";
import { useUserStore } from "../stores/userStore";
import { useFriendStore } from "../stores/friendStore.js";
import { storeToRefs } from "pinia";
import { Search } from "@element-plus/icons-vue";
import { searchFriend } from "../api/friend";
import ContactList from "../components/ContactList.vue";
import { RouterLink, RouterView } from "vue-router";
import { useI18n } from "vue-i18n";
import AcceptableItem from "../components/AcceptableItem.vue";
import ResultItem from "../components/ResultItem.vue";
import StatusItem from "../components/StatusItem.vue";
import ChatMessage from "../components/ChatMessage.vue";
import MyStatusItem from "../components/MyStatusItem.vue";
import InfoItem from "../components/InfoItem.vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const pics = reactive([
  "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
  "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
  "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
  "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
  "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
]);

const store = useUserStore();
const Fstore = useFriendStore();
const { name, avatar, token } = storeToRefs(store);
const router = useRouter();
var searchInput = ref("");
var friendParam = reactive({
  page: {
    pageSize: 10,
    pageNum: 1,
  },
});
var groupParam = reactive({
  page: {
    pageSize: 10,
    pageNum: 1,
  },
});
var friendShowAll = ref(false);
var groupShowAll = ref(false);
const { t } = useI18n();

function searchF() {
  searchFriend(token, searchInput.value, friendParam.page)
    .then((res) => {
      if (res.data.success) {
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
        message: t("contactList.friend.ListError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {});
}
function showAllFriends() {
  friendShowAll.value = friendShowAll.value == true ? false : true;
}
function showAllGroups() {
  groupShowAll.value = groupShowAll.value == true ? false : true;
}
function logout() {
  store.logout();
}
function postStatus() {
  router.push({ name: "postStatus", params: {} });
}
function checkStatus() {
  router.push({ name: "myStatus"});
}
function editInfo() {
  router.push({ name: "editMyInfo", params: {} });
}
function toSearch() {
  let temp = searchInput.value;

  if (temp == "") {
    temp = "_[all]_";
  }
  if (Fstore.searchHistory != temp) {
    Fstore.searchHistory = temp;
    Fstore.loadFirstList();
  }
  router.push({
    name: "searchFriend"
  });
}
function btn() {
  alert("djhskadsa");
}
function menuClick(index) {
  router.push({
    name: index,
  });
}
</script>
<style scoped>
.el-main {
  overflow: visible;
}
.header-row {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 0px;
  padding-left: 10px;
}
.col2-input {
  width: 60vw;
  min-width: 200px;
}
.aside {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column nowrap;
  justify-content: flex-start;
  min-width: 70px;
  height: 100%;
  align-content: flex-start;
}
.aside-main {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
}
#mainAside {
  min-width: 150px;
  min-height: 400px;
}
.main-bar {
  min-height: 400px;
}
#m1 {
  width: 50%;
  min-width: 70px;
  flex: 1 1 auto;
}
#m2 {
  width: 50%;
  min-width: 80px;
  flex: 2 2 auto;
}
.header-text {
  color: #fff;
  background-color: #545c64;
  width: 5vw;
  height: 7vh;
  min-height: 40px;
  text-align: center;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 0;
  padding: 0;
}
.t1 {
  min-width: 60px;
}
.t2 {
  min-width: 90px;
}
.header {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  padding: 0;
  margin-bottom: -1vh;
  min-width: 810px;
}
.el-menu-demo {
  height: 7vh;
  min-height: 40px;
  width: 80vw;
  min-width: 660px;
}
.menuItem {
  width: 25%;
  min-width: 120px;
}
.mainPart {
  flex: auto;
  min-width: 660px;
  min-height: 400px;
  height: 78vh;
  overflow: visible;
  margin-top: 0;
  margin-left: 1px;
  background-color: #fafafa;
}
.common-layout {
  overflow: clip;
}
.main-main {
  margin-top: 1vh;
  margin-left: 0;
  padding: 0;
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  align-content: flex-start;
  min-width: 660px;
  min-height: 400px;
}
.el-dropdown-link {
  margin-left: -5vw;
}
.col2 {
  flex: auto;
  display: -webkit-flex; /* Safari */
  display: flex;
  min-width: 300px;
}
.col2-btn {
  margin-left: -40px;
  margin-top: 5px;
}
.col3 {
  padding-left: 3.5em;
}
</style>
