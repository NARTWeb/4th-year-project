<template>
  <div id="larger-all">
    <el-scrollbar height="75vh" id="all">
      <ul v-infinite-scroll="load" class="infinite-list">
        <el-timeline>
          <el-timeline-item
            v-for="status in myStatusList"
            :key="status.statusId"
            :timestamp="status.createDate"
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
                @del="del"
              ></my-status-item>
            </li>
          </el-timeline-item>
        </el-timeline>

        <li v-show="nodata" id="end">{{ $t("myStatusList.toEnd") }}</li>
      </ul>
    </el-scrollbar>
  </div>
</template>
<script setup>
import { onMounted } from "vue";
import { reactive, ref, watch } from "vue";
import { useI18n } from "vue-i18n";
import  useUserStore  from "@/stores/userStore";
import { storeToRefs } from "pinia";
import MyStatusItem from "@/components/MyStatusItem.vue";
import { showMyStatusList, deleteStatus } from "@/api/status";
import { ElMessage } from "element-plus";

const props = defineProps({
  uid:{
    type:String,
    default:"-1"
  }
});

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

  for (let i = 0; i < 5; i++) {
    const test = [
      {
        statusId: counter.value.toString(),
        msg: "this is a new Status",
        isMine: true,
        createDate: "2018-04-13",
        pics: [
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
        ],
      },
      {
        statusId: (counter.value + 1).toString(),
        msg: "nono it shouldn't be here",
        isMine: true,
        createDate: "2018-04-13",
        pics: [
          "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
          "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
          "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
        ],
      },
    ];
    myStatusList.push(...test);
    counter.value += 2;
  }
}
function load() {
  if (!loading.value && !nodata.value) {
    showMyStatusList(token.value, props.uid, page)
      .then((res) => {
        if (res.data.success) {
          if (res.data.data.length > 0) {
            myStatusList.push(...res.data.data);
            page.pageNum += 1;
          } else {
            nodata.value = true;
          }
        } else {
          ElMessage({
            type: "error",
            message: res.data.msg,
            showClose: true,
          });
        }
      })
      .catch((err) => {
        ElMessage({
          type: "error",
          message: t("myStatusList.loadError"),
          showClose: true,
        });
        console.log(err);
      })
      .finally(() => {
        loading.value = false;
      });
  }
}
function del(id) {
  for (let i = 0; i < myStatusList.length; i++) {
    if (myStatusList[i].statusId == id) {
      delBack(id, i);
      return;
    }
  }
}
function delBack(id, i) {
  deleteStatus(token.value, id)
    .then((res) => {
      if (res.data.success) {
        myStatusList.splice(i, 1);
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
        message: t("myStatusList.delError"),
        showClose: true,
        grouping: true,
      });
      console.log(err);
    })
    .finally(() => {
      return;
    });
}
</script>
<style scoped>
.infinite-list {
  list-style: none;
}
#larger-all {
  padding: 0;
  margin: -10px 0;
  overflow: hidden;
}
#end {
  text-align: center;
  font-size: xx-large;
  font-weight: 600;
}
</style>
