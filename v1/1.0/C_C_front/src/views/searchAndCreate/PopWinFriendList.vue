<template>
    <el-dialog
      v-model="dialogVisible"
      :title="t('popWin.AddFriend')"
      width="40%"
    >
      <div style="width: 40%">
        <div class="all">
            <div class="search">
                <el-input id="searchF" :placeholder="t('FriendIHave.searchFriend')">
                    <template #append>
                        <el-button @click="searchFr" :icon="Search" />
                    </template>
                </el-input>
            </div>
            <div class="list">
                <el-scrollbar height="65vh" id="all">
                    <ul v-infinite-scroll="load" class="infinite-list">
                        <li v-for="friend in friendList" :key="friend.id">
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
      </div>
    </el-dialog>
</template>
<script setup>
import { reactive, ref } from "vue";
import  useUserStore from "@/stores/userStore";
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
const router = useRouter();
const newStore = useNewStore();
const friendList = reactive([]);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 5,
  pageNum: 0,
});
const input = ref<String>('');

function close(id) {
  newStore.delItem(id);
}
function addBtn(id) {

}
function searchFr() {
    searchFriend(token, input, page)
    .then((res) =>{
        if(res.data.success){
            friendList.splice(0,friendList.length)
            friendList.push(...res.data.data);
        }else{
            ElMessage({
                type: "error",
                message: t('FriendIHave.searchError'),
                showClose: true,
                grouping: true,
            });
        }
    })
    .catch((err) => {
        ElMessage({
            type: "error",
            message: t('FriendIHave.searchError'),
            showClose: true,
            grouping: true,
        });
    })
}
function load(){
    if(!loading.value && !nodata.value){
        loading.value = true;
        searchFriend(token, input, page)
        .then((res) => {
            if(res.data.success){
                if(res.data.data.length > 0){
                    friendList.push(...res.data.data);
                    page.pageNum += 1;
                }else{
                    nodata.value = true;
                }
            }else{
                ElMessage({
                    type: "error",
                    message: t("friendIHave.loadError"),
                    showClose: true,
                    grouping: true,
                });
            }
        })
        .catch((err) => {
            ElMessage({
            type: "error",
            message: t("friendIHave.loadError"),
            showClose: true,
            grouping: true,
            });
        })
        .finally(() => {
            loading.value = false;
        });
    }
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
}
</style>
