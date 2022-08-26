<template>
  <div class="all flex">
    <el-dialog
      v-model="dialogVisible"
      :title="t('infoItem.title')"
      width="40%"
      :before-close="clearBtn"
    >
      <div style="width: 40%">
        <el-input :placeholder="t('infoItem.oldPwd')" v-model="oldPwd" />
        <el-input :placeholder="t('infoItem.newPwd')" v-model="newPwd" />
        <el-input
          :placeholder="t('infoItem.newPwd2')"
          v-model="newPwd2"
          @input="checkPwd"
        />
        <div class="error3">New Password not match!</div>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clearBtn">Cancel</el-button>
          <el-button type="primary" @click="changeBtn">Confirm</el-button>
        </span>
      </template>
    </el-dialog>
    <div class="top flex fullW">
      <span class="text title">{{ props.label }}</span>
      <hr class="fullW"/>
    </div>
    <div class="bottom fullW">
      <div v-if="props.isPwd" class="pwde flex fullW">
        <div>
          <el-button round type="primary" @click="dialogVisible = true"
            >change</el-button
          >
        </div>
      </div>
      <div v-else class="elements flex fullW">
        <div>
          <el-input v-if="changeValue" v-model="v" @blur="changeValue = false"></el-input>
          <span v-else class="text cb">{{ v }}</span>
        </div>
        <div class="el-right">
          <el-button round type="primary" v-show="canChange" @click="changeBtn">change</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, reactive } from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();
const input = ref("");
const oldPwd = ref("");
const newPwd = ref("");
const newPwd2 = ref("");
const dialogVisible = ref(false);
const error3Display = ref("none");
const changeValue = ref(false);

const props = defineProps({
  isPwd: Boolean,
  label: String,
  value: String,
  canChange: Boolean,
  // hideBtn: Boolean,
  // matchFormat: String,
});
const v = ref(props.value);
function clearBtn() {
  dialogVisible.value = false;
  oldPwd.value = "";
  newPwd.value = "";
  newPwd2.value = "";
}
function checkPwd() {
  if (newPwd.value == newPwd2.value) {
    error3Display.value = "none";
  } else {
    error3Display.value = "";
  }
}
function changeBtn() {
  if(props.isPwd) {
    dialogVisible.value = true;
  } else {
    changeValue.value = true;
  }
}
</script>
<style scoped>
.error3 {
  display: v-bind(error3Display);
  color: red;
}
.flex {
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content: space-between;
  flex-flow: column nowrap;
  align-items: center;
}
.fullW {
  width: 100%;
}
.top {
  align-items: flex-start;
  width: 250px;
}
.pwde {
  justify-content: flex-end;
  flex-flow: row nowrap;
}
.elements {
  flex-flow: row nowrap;
}
.cb {
  font-size: large;
}
</style>
