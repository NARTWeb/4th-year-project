<template>
  <div class="all flex">
      <div class="member flex">
        <el-scrollbar height="45vh">
            <div v-for="member in memberList" :key="member.id" class="scroll-item">
              <el-avatar
                  :size="60"
                  :src="member.avatar"
                  />
              <div class="memberName">{{ member.uname }}</div>
            </div>
            <div>
              <span>
                <button class="add">+</button>
                <button class="del">-</button>
              </span>                
            </div>
        </el-scrollbar>
      </div>

      <div class="item flex">
        <div class="change flex">
          <div><label>groupName</label></div>
          <div><el-button type="primary" @click="changeBtn">change</el-button></div>
        </div>
        <div>
          <span>{{groupInfo.groupName}}</span>
        </div> 
      </div>

      <div class="item flex">
        <div class="change flex">
          <div><label>importantNotice</label></div>
          <div><el-button type="primary" @click="changeBtn">change</el-button></div>
        </div>
        <div>
          <span>{{groupInfo.notice}}</span>
        </div> 
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
.flex {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
}
.member {
  justify-content: flex-start;
  height: 25%;
}
.change {
  flex-direction: row;
  justify-content: space-around;
  height: 25%;
}
</style>
