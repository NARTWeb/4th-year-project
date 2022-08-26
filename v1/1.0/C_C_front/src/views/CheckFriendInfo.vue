<template>
  <div class="all">
    <div class="top">
      <div class="avatar">
        <el-avatar class="img" :src="tt.avatar" />
      </div>
      <div class="info">
        <div class="uname">
          <info-item :label="tt.label1" :value="tt.value1"></info-item>
        </div>
        <div class="uemail">
          <info-item :label="tt.label2" :value="tt.value2"></info-item>
        </div>
      </div>
    </div>
      <el-divider/>
    <div class="bottom">
      <el-scrollbar id="all" wrap-style="height:50vh;">
        <ul v-infinite-scroll="tList" class="infinite-list">
        <el-timeline>
          <el-timeline-item
            v-for="status in myStatusList"
            :key="status.statusId"
            :timestamp="format(status.createDate)"
            size="large"
            color="#D9F2E3"
            hollow
            center
          >
          <li>
            <my-status-item
              :isMine="status.isMine"
              :message="status.msg"
              :pictures="status.pics"
              :id="status.statusId"
            ></my-status-item>
          </li>
          </el-timeline-item>
        </el-timeline>
          <li v-show="nodata" id="end">{{ $t("myStatusList.toEnd") }}</li>
        </ul>
      </el-scrollbar>
    </div>
  </div>
</template>
<script setup>
import { onMounted } from "vue";
import { reactive, ref, watch } from "vue";
import { useI18n } from "vue-i18n";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import MyStatusItem from "../components/MyStatusItem.vue";
import InfoItem from "../components/InfoItem.vue";
import { showMyStatusList } from "../api/status";
import { format } from "@/utils/time.js";

const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const myStatusList = reactive([]);
const counter = ref(0);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 5,
  pageNum: 0,
});
const tt = {
  label1: "Username",
  label2: "Email",
  value1: "testName",
  value2: "testEmail@gmail.com",
  avatar:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
};

function tList() {
  if (counter.value > 10) {
    nodata.value = true;
    return;
  }
  const test = [
    {
      statusId: counter.value.toString,
      msg: "this is a new Status",
      isMine: false,
      createDate: {
        year: 2022,
        month: 8,
        day: 23,
        hour: 23,
        min: 8
      },
      pics: [
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      ],
    },
  ];
  for (let i = 0; i < 5; i++) {
    myStatusList.push(...test);
    counter.value += 1;
  }
}
function load() {
  if (!loading.value && !nodata.value) {
    showMyStatusList(token, page)
      .then((res) => {
        if (res.data.success) {
          if (res.data.data.length > 0) {
            myStatusList.push(...res.data.data);
            page.pageNum += 1;
          } else {
            nodata.value = true;
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
        this.$message({
          type: "error",
          message: t("myStatusList.loadError"),
          showClose: true,
        });
        console.log(err);
      })
      .finally(() => {
        this.loading.value = false;
      });
  }
}
</script>
<style scoped>
.all {
  width: 100%;
  height: 100%;
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: column nowrap;
  justify-content: flex-start;
}
.top {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
   -webkit-flex: none;
          flex: none;
}
.info {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-around;
  width: 100%;
}
.bottom {
  -webkit-flex: auto;
          flex: auto;
}
.infinite-list {
  list-style: none;
}
#larger-all {
  padding: 0;
  margin: -20px 0;
  overflow: hidden;
}
#end {
  text-align: center;
  font-size: xx-large;
  font-weight: 600;
}
</style>
