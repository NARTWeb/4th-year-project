<template>
  <div>
    <acceptable-item></acceptable-item>
  </div>
</template>
<script setup>
import AcceptableItem from "../components/AcceptableItem.vue";
import { showFriendRequests } from "../api/friend";
import { useUserStore } from "../stores/userStore";
import { storeToRefs } from "pinia";

const store = useUserStore();
const { token } = storeToRefs(store);
const loading = ref(false);
const nodata = ref(false);
const page = reactive({
  pageSize: 8,
  pageNum: 0,
});
const reqList = reactive([]);

function searchReqList() {
  if (!nodata.value && !loading.value) {
    loading.value = true;
    showFriendRequests(token, page).then((res) => {
      if (res.data.success) {
        if (res.data.data > 0) {
          reqList.push(...res.data.data);
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
          message: msg,
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
<style scoped></style>
