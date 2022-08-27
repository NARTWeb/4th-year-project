<template>
  <div class="all">
      <div class="member">
        <el-scrollbar height="45vh">
            <div v-for="member in memberList" :key="member.id" class="scroll-item">
            <el-avatar
                :size="60"
                :src="member.avatar"
                />
            <div class="memberName">{{ member.uname }}</div>
            </div>
            <div>
                <button class="add">+</button>
                <button class="del">-</button>
            </div>
        </el-scrollbar>
      </div>

      <div>
        <label>groupName</label>
        <span>{{groupInfo.groupName}}</span>
        <el-button type="primary" @click="changeBtn">change</el-button>
      </div>
      <div>

        <label>importantNotice</label>
        <span>{{groupInfo.notice}}</span>
        <el-button type="primary" @click="changeBtn">change</el-button>
      </div>
  </div>
</template>
<script setup>

import { onMounted } from "vue";
import { reactive, ref, watch } from "vue";
import { useI18n } from "vue-i18n";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import InfoItem from "../components/InfoItem.vue";
import { format } from "@/utils/time.js";
import { showMemberList } from "@/api/group.js";
import { ElMessage } from "element-plus";
import { group } from "console";

const store = useUserStore();
const { token, groupInfo } = storeToRefs(store);
const { t } = useI18n();
const counter = ref(0);
const memberList = reactive([]);


const tt = {
  label1: "Username",
  label2: "Birthday",
  label3: "Password",
  label4: "Phone",
  label5: "Email",
  label6: "address",

  value1: "testName",
  value2: "000607",
  value3: "",
  value4: "34567890",
  value5: "testEmail@gmail.com",
  value6: "testAddress",
  avatar:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
};

function changeBtn() {
  changeValue.value = true;
}
function getMember() {
    showMemberList(token, groupId, page)
    .then((res) => {
        if(res.data.success){
            memberList.push(...res.data.data);
        }else{
            ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
            });
        }
    })
    .catch((err) =>{
        ElMessage({
                type: "error",
                message: t("groupSetting.getMemberError"),
                showClose: true,
                grouping: true,
            });
    })
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
.top {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: 25%;
}
.info {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 75%;
}
.first {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 33.3%;
}
.second {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 33.3%;
}
.third {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 33.3%;
}
</style>
