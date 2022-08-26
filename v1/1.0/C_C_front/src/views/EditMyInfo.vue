<template>
  <div class="all">
      <div class="avatar">
        <el-avatar class="img" :src="tt.avatar" />
      </div>

      <div class="info text">
        <div class="first">
            <div class="uname">
                <info-item :label="tt.label1" :value="tt.value1" can-change></info-item>
            </div>
            <div class="birth">
                <info-item :label="tt.label2" :value="tt.value2" can-change></info-item>
            </div>
        </div>
        <div class="second">
            <div class="pwd">
                <info-item :label="tt.label3" :value="tt.value3" can-change is-pwd></info-item>
            </div>
            <div class="phone">
                <info-item :label="tt.label4" :value="tt.value4" can-change></info-item>
            </div>
        </div>
        <div class="third">
            <div class="email">
                <info-item :label="tt.label5" :value="tt.value5" can-change></info-item>
            </div>
            <div class="address">
                <info-item :label="tt.label6" :value="tt.value6" can-change></info-item>
            </div>
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

const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const counter = ref(0);

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
