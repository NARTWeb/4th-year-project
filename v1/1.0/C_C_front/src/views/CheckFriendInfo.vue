<template>
  <div id="all">
    <div class="top">
        <div>avatar</div>
        <div>
            <info-item ></info-item>
        </div>
    </div>
    <div class="bottom">
        <el-scrollbar height="75vh" id="all">
            <ul v-infinite-scroll="tList" class="infinite-list">
                <li v-for="status in myStatusList" :key="status.statusId">
                    <my-status-item
                        :isMine="status.isMine"
                        :message="status.msg"
                        :pictures="status.pics"
                        :id="status.id"
                    ></my-status-item>
                </li>
                <li v-show="nodata" id="end">{{$t('myStatusList.toEnd')}}</li>
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
import { showMyStatusList } from "../api/status";

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
      isMine: false,
      pics: [
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      ],
      id: "hdsjakdhsa",
    },
    {
      statusId: counter.value.toString,
      avatar:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      uname: "testName",
      msg: "nono it shouldn't be here",
      isMine: true,
      pics: [
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      ],
      id: "hdsjakdhsa",
    },
  ];
  for (let i = 0; i < 5; i++) {
    myStatusList.push(...test);
    counter.value += 1;
  }
}
function load() {
  if (!loading.value && !nodata.value) {
    showMyStatusList(token, page).then((res) => {
      if (res.data.success) {
        if(res.data.data.length > 0) {
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
    }).catch((err) => {
        this.$message({
          type: "error",
          message: t('myStatusList.loadError'),
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
