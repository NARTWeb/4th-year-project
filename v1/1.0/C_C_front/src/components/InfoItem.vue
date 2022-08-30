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
        <div class="error3">{{ $t("infoItem.notMatch") }}</div>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="clearBtn">Cancel</el-button>
          <el-button type="primary" @click="changeBtn" :disabled="confirm">{{ $t("infoItem.confirm") }}</el-button>
        </span>
      </template>
    </el-dialog>
    <div class="top flex fullW">
      <span class="text title">{{ props.label }}</span>
      <hr class="fullW" />
    </div>
    <div class="bottom fullW">
      <div v-if="props.isPwd" class="pwde flex fullW">
        <div>
          <el-button round type="primary" @click="dialogVisible = true"
            >{{ $t("infoItem.change") }}</el-button
          >
        </div>
      </div>
      <div v-else class="elements flex fullW">
        <div>
          <el-input
            v-if="changeValue"
            v-model="input"
            @change="checkInput"
            @blur="checkInput"
          ></el-input>
          <span v-else class="text cb">{{ v }}</span>
        </div>
        <div class="el-right">
          <el-button round type="primary" v-show="canChange" @click="changeBtn">{{ $t("infoItem.change") }}</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from "vue";
import { useI18n } from "vue-i18n";
import { ElMessage } from "element-plus";

const { t } = useI18n();
const dialogVisible = ref(false);
const error3Display = ref("none");
const changeValue = ref(false);
const confirm = ref(false);

const emit = defineEmits(["changeFun", "changePwd"]);
const props = defineProps({
  isPwd: Boolean,
  label: String,
  value: String,
  reg: RegExp,
  canChange: Boolean,
  // matchFormat: String,
});
var v = props.value;
const input = computed({
  get() {
    return v;
  },
  set(newValue) {
    v = newValue.trim();
  }
});
var oldP = "";
const oldPwd = computed({
  get() {
    return oldP;
  },
  set(newValue) {
    oldP = newValue.trim();
  }
});
var newP = "";
const newPwd = computed({
  get() {
    return newP;
  },
  set(newValue) {
    newP = newValue.trim();
  }
});;
var newP2 = "";
const newPwd2 = computed({
  get() {
    return newP2;
  },
  set(newValue) {
    newP2 = newValue.trim();
  }
});;

function clearBtn() {
  dialogVisible.value = false;
  oldP = "";
  newP = "";
  newP2 = "";
}
function checkInput() {
  changeValue.value = false;
  let errorClass = '';
  if(props.label == 'Username') {
    errorClass = "uname";
  } else {
    errorClass = props.label.toLowerCase();
  }
  let regexp = new RegExp(props.reg);
  if(!regexp.test(v)) {
    ElMessage({
          type: "error",
          message: t('welcome.' + errorClass + 'Error'),
          showClose: true,
          grouping: true,
        });
    v = props.value;
  } else {
    change();
  }
}
function checkPwd() {
  if (newP == newP2) {
    error3Display.value = "none";
  } else {
    error3Display.value = "";
  }
  let regexp = new RegExp(/^[\w-]{6,18}$/);
  if (!regexp.test(newP)) {
    ElMessage({
      type: "warning",
      message: t("welcome.pwdError"),
      showClose: true,
      grouping: true,
    });
    confirm.value = true;
  } else {
    confirm.value = false;
  }
}
function changeBtn() {
  if (props.isPwd) {
    dialogVisible.value = dialogVisible.value == true ? false:true;
  } else {
    changeValue.value = changeValue.value == true ? false:true;
  }
}
function change() {
  if (!props.isPwd) {
    emit("changeFun", props.label, input);
  } else {
    emit("changePwd", oldP, newP);
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
  width: 250px;
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
