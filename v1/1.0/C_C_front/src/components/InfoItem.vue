<template>
    <div class="all">
            <el-dialog
                v-model="dialogVisible"
                :title="t('infoItem.title')"
                width="40%"
                :before-close="clearBtn"
            >
            <div style="width:40%">
                <el-input :placeholder="t('infoItem.oldPwd')" v-model="oldPwd"/>
                <el-input :placeholder="t('infoItem.newPwd')" v-model="newPwd"/>
                <el-input :placeholder="t('infoItem.newPwd2')" v-model="newPwd2" @input="checkPwd"/>
                <div class="error3">New Password not match!</div>
            </div>
                <template #footer>
                <span class="dialog-footer">
                    <el-button @click="clearBtn">Cancel</el-button>
                    <el-button type="primary" @click="changeBtn"
                    >Confirm</el-button
                    >
                </span>
                </template>
            </el-dialog>
            <div class="top">
                <span>{{props.label}}</span>
                <hr>
            </div>
            <div class="bottom">
                <div v-if="props.isPwd" class="pwde">
                    <div>
                        <el-button round type="primary" @click="dialogVisible = true">change</el-button>
                    </div>
                </div>
                <div v-else class="elements">
                    <div>
                        <span>{{props.value}}</span>
                    </div>
                    <div>
                        <el-button round type="primary" @click="changeBtn">change</el-button>
                    </div>
                </div>   
            </div>
    </div>
</template>
<script setup>
import {ref, reactive} from "vue";
import { useI18n } from "vue-i18n";

const { t } = useI18n();
const input = ref('');
const oldPwd = ref('');
const newPwd = ref('');
const newPwd2 = ref('');
const dialogVisible = ref(false);
const error3Display = ref('none');

    const props = defineProps({
        isPwd: Boolean,
        label: String,
        value: String,
        // hideValue: Boolean,
        // hideBtn: Boolean,
        // matchFormat: String,
    })
    function clearBtn() {
        dialogVisible.value = false;
        oldPwd.value = "";
        newPwd.value = "";
        newPwd2.value = "";
    }
    function checkPwd() {
        if(newPwd.value == newPwd2.value) {
            error3Display.value = 'none';
        } else {
            error3Display.value = '';
        }
    }
    function changeBtn(){
        alert("oldPWD: " + oldPwd.value + " newPWD: " + newPwd.value + " newPWD2: " + newPwd2.value);
        clearBtn();
    }

</script>
<style scoped>
    .error3 {
        display: v-bind(error3Display);
        color: red;
    }

    .all {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: column nowrap;
        align-items: center;
        width: 100%;
    }
    .top {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: column nowrap;
        width: 100%;
    }
    .bottom {
        width: 100%;
    }
    .pwde {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: flex-end;
        flex-flow: row nowrap;
        align-items: center;
        width: 100%;
    }
    .elements {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: row nowrap;
        align-items: center;
        width: 100%;
    }
</style>