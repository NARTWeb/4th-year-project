<template>
  <div id="larger-all">
    <el-scrollbar height="75vh" id="all">
      <ul v-infinite-scroll="tList" class="infinite-list">
        <li v-for="status in statusList" :key="status.statusId">
          <status-item
            :statusId="status.statusId"
            :avatar="status.avatar"
            :uname="status.uname"
            :message="status.msg"
            :pictures="status.pics"
            :comments="status.comments"
            :heart="status.liked"
            :heartNum="status.likes"
            :date="status.createDate"
            :uid="status.uid"
          ></status-item>
        </li>
        <li v-show="nodata" id="end">{{$t('statusList.toEnd')}}</li>
      </ul>
    </el-scrollbar>
  </div>
</template>
<script setup>
import { onMounted } from "vue";
import { reactive, ref, watch } from "vue";
import { useI18n } from "vue-i18n";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";
import StatusItem from "../components/StatusItem.vue";
import { showAllStatusList } from "../api/status";

const store = useUserStore();
const { token } = storeToRefs(store);
const { t } = useI18n();
const statusList = reactive([]);
const counter = ref(0);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 5,
  pageNum: 0,
});

function tList() {
  if (counter.value > 10) {
    nodata.value = true;
    return;
  }
  const test = [
    {
      statusId: counter.value.toString,
      avatar:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      uname: "testName",
      msg: "this is a new Status",
      pics: [
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      ],
      comments: [
        {
          id: (1 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
        {
          id: (2 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
        {
          id: (3 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
        {
          id: (4 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
        {
          id: (5 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
        {
          id: (6 + counter + 10).toString,
          uname: "respName",
          msg: "this is a response",
          date: "2002/09/18",
        },
      ],
      heart: false,
      heartNum: 100,
      createDate: "2022/09/10",
      uid: "hdsjakdhsa",
    },
  ];
  for (let i = 0; i < 5; i++) {
    statusList.push(...test);
    counter.value += 1;
  }
}
function load() {
  if (!loading.value && !nodata.value) {
    showAllStatusList(token, page).then((res) => {
      if (res.data.success) {
        if(res.data.data.length > 0) {
            statusList.push(...res.data.data);
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
    }).catch((err) => {
        this.$message({
          type: "error",
          message: t('statusList.loadError'),
          showClose: true,
        });
        console.log(err);
      })
      .finally(() => {
        this.loading.value = false;
      });
    ;
  }
}
</script>
<style scoped>
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
