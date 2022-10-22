<template>
  <el-dialog v-model="dialogVisible" :title="t('popWin.AddFriend')" width="60%" 
  @open="openWin"
  @close="closeWin">
    <div class="all">
      <div class="search">
        <el-input id="searchF" v-model="input" :placeholder="t('friendIHave.searchFriend')">
          <template #append>
            <el-button @click="searchFr" :icon="Search" />
          </template>
        </el-input>
      </div>
      <div class="list">
        <el-scrollbar height="65vh" id="all">
          <ul v-infinite-scroll="tLoad" class="infinite-list">
            <li v-for="friend in gList" :key="friend.id">
              <result-item
                :avatar="friend.avatar"
                :id="friend.id"
                :username="friend.uname"
                :button-label="t('memberList.add')"
                @delItem="close"
                @btnFunc="addBtn"
              ></result-item>
            </li>
          </ul>
        </el-scrollbar>
      </div>
    </div>
  </el-dialog>
</template>
<script setup>
import { computed, reactive, ref } from "vue";
import useUserStore from "@/stores/userStore";
import { useFriendStore } from "@/stores/friendStore.js";
import { storeToRefs } from "pinia";
import { Search } from "@element-plus/icons-vue";
import { searchFriend } from "@/api/friend.js";
import ContactList from "@/components/ContactList.vue";
import { useI18n } from "vue-i18n";
import ResultItem from "@/components/ResultItem.vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { useNewStore } from "@/stores/newStore";
import { showFriendList } from "@/api/friend";

const { t } = useI18n();

const store = useUserStore();
const Fstore = useFriendStore();
const { name, avatar, token } = storeToRefs(store);
const { gList } = storeToRefs(Fstore);
const router = useRouter();
const friendList = reactive([]);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 5,
  pageNum: 0,
});
var input = ref("");
const props = defineProps({
  dialogVisible: Boolean,
  list: Array,  
});
const emit = defineEmits(["closeWin", "addFun"]);

function close(id) {
  let obj = Fstore.delGItem(id);
}
function addBtn(id) {
  let obj = Fstore.delGItem(id);
  emit("addFun", obj);
}
function pop() {
  dialogVisible.value = true;
}
function searchFr() {
  let temp = input.value;
  console.log("temp " + temp);
  if(temp == undefined || temp == "") {
    temp = "-1";
  }
  searchFriend(token.value, temp, page)
    .then((res) => {
      if (res.data.success) {
        friendList.splice(0, friendList.length);
        friendList.push(...res.data.data);
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
        message: t("friendIHave.searchError"),
        showClose: true,
        grouping: true,
      });
    });
}
function tLoad() {
  console.log("tLoad");
  return Fstore.loadNewGFriends();
}
function closeWin() {
  emit("closeWin");
}
function openWin() {
  console.log(props.list);
  Fstore.loadFirstGList(props.list);
}
</script>
<style scoped>
.all {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.list {
    width: 100%;
}
</style>
