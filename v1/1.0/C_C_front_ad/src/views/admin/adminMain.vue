<!--
* Copyright (c) 2008-2024: Shizhong Shang
* Project: 4th-year-project
* @fileName: adminMain
* @Description: TODO 
*
* @author: SHIZHONG SHANG
* @date: 2022-12-30 2:42 p.m.	
-->
<template>
    <div id="wholePage">
        <el-container >
            <el-header id="header">
                <div class="header-row">
                    <div class="col1">
                        <el-image
                                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                                fit="fill"
                                style="width: 220px; height: 50px; margin-left: -6%"
                        />
                    </div>
                    <div class="admin">
                        <el-dropdown trigger="click">
                          <span class="el-dropdown-link">
                            <el-avatar
                                    :src="avatar"
                                    fit="contain"
                                    style="width: 50px; height: 50px"
                            />
                          </span>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click="changeLang()">{{
                                            $t("changeLang")
                                        }}</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </div>
            </el-header>

            <el-container id="info1" height="30vh">
                <el-main width="33vw" id="statusNum">
                    <span>fghjkl</span>
                </el-main>

                <el-main width="33vw" id="commentNum">
                    <span>fhbjkl</span>
                </el-main>
                <el-main width="33vw" id="msgNum">
                    <span>ghjkl</span>
                </el-main>
            </el-container>

            <el-container id="info2" height="60vh">
                <el-container height="60vh">
                    <el-main width="33vw" height="30vh" id="userNum">
                        <span>ghjkl</span>
                    </el-main>
                    <el-main width="33vw" height="30vh" id="onlineUserNum">
                        <span>ghjk</span>
                    </el-main>
                </el-container>

                <el-container width="66vw" id="searchBar">
                    <el-scrollbar height="60vh" id="all">
                        <ul v-infinite-scroll="load" class="infinite-list">
                            <li v-for="f in fList" :key="f.id">
                                <result-item
                                        :avatar="f.avatar"
                                        :id="f.id"
                                        :username="f.uname"
                                        :button-label="t('friendIHave.chat')"
                                        @delItem="close"
                                        @btnFunc="chatBtn"
                                ></result-item>
                            </li>
                        </ul>
                    </el-scrollbar>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import { ref, reactive, watch, onBeforeUnmount, onMounted,  } from "vue";
import { useI18n } from "vue-i18n";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import "@/api/admin";
import { showUserNum } from "@/api/admin";
import {showMessageNum} from "../../api/admin";

const router = useRouter();
const { token } = storeToRefs(userStore);
const { t } = useI18n();

function getAdminMainPageInfo() {
    showStatusNum(token.value)
        .then((res) => {
            if (res.data.success) {
                statusNum.value = res.data.data;
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
                message: t("admin.statusNumErr"),
                showClose: true,
            });
            console.log(err);
        });
    showCommentNum(token.value)
        .then((res) => {
            if (res.data.success) {
                commentNum.value = res.data.data;
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
                message: t("admin.commentNumErr"),
                showClose: true,
            });
            console.log(err);
        });
    showMessageNum(token.value)
        .then((res) => {
            if (res.data.success) {
                msgNum.value = res.data.data;
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
                message: t("admin.msgNumErr"),
                showClose: true,
            });
            console.log(err);
        });
    showUserNum(token.value)
        .then((res) => {
            if (res.data.success) {
                userNum.value = res.data.data;
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
                message: t("admin.userNumErr"),
                showClose: true,
            });
            console.log(err);
        });
    showOnlineUserNum(token.value)
        .then((res) => {
            if (res.data.success) {
                onlineUserNum.value = res.data.data;
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
                message: t("admin.onlineUserNumErr"),
                showClose: true,
            });
            console.log(err);
        });
}

onMounted(() => {
    getAdminMainPageInfo();
})
</script>

<style scoped>


    #all {
        padding: 0;
        margin: -10px 0;
    }

    #wholePage {
        min-height: 500px;
    }
    @media screen and (min-height: 635px) {
        #wholePage {
            padding-top: 20px;
            padding-right: 20px;
        }
    }
    @media screen and (max-height: 634px) and (min-height: 590px) {
        #wholePage {
            padding-top: 10px;
            padding-right: 10px;
        }
    }
    @media screen and (max-height: 589px) {
        #wholePage {
            padding-top: 0px;
            padding-right: 0px;
        }
    }
</style>