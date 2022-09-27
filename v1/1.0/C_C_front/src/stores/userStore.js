import { defineStore } from "pinia";
import { getToken, setToken, removeToken } from "@/request/token";
import { signin, signout, register, showUserInfo, showAvatarUname, changeUserInfo} from "@/api/user.js";
import { ElMessage } from 'element-plus'
import i18n from '../locals/index.js'

const useUserStore = defineStore("user", {
  state: () => {
    return {
      token: "jkdflsf;jkadfs",
      name: "qiao",
      avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
      email: "mikeqiao@gmail.com",
      tel: "123456789",
      address: "21 Glenhaven",
      birthday: "04/13/2020",
      groupId: "",
      notice: "",
      groupName: "",
      groupAvatar: "",
    };
  },
  getters :{
    getGroupId: (state) => {
      return state.groupId;
    },
    getNotice: (state) => {
      return state.notice;
    },
    getGroupName: (state) => {
      return state.groupName;
    },
    getGroupAvatar: (state) => {
      return state.groupAvatar;
    },
  },
  actions: {
    updategroupInfo(gInfo){
      this.groupId = gInfo.gid;
      this.notice = gInfo.note;
      this.groupName = gInfo.gName;
      this.groupAvatar = gInfo.gAvatar;
    },
    login(user) {
      return new Promise((resolve, reject) => {
        signin(user.username, user.password)
          .then((res) => {
            if (res.data.success) {
              setToken(res.data.data);
              this.token = getToken();
              this.getAvatarUname();
              resolve();
            } else {
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
              reject(res.data.msg);
            }
          })
          .catch((error) => {
            ElMessage({
              type: "error",
              message: i18n.global.t('user.loginError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
    async getAvatarUname() {
      return new Promise((resolve, reject) => {
        showAvatarUname(this.token)
          .then((res) => {
            if (res.data.success) {
              this.name = res.data.data.uname;
              this.avatar = res.data.data.avatar;
              resolve();
            } else {
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
              reject(res.data.msg);
            }
          })
          .catch((error) => {
            ElMessage({
              type: "error",
              message: i18n.global.t('user.mainError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
    async getUserInfo() {
      return new Promise((resolve, reject) => {
        showUserInfo(this.token, "")
          .then((res) => {
            if (res.data.success) {
              this.name = res.data.data.uname;
              this.avatar = res.data.data.avatar;
              this.email = res.data.data.email;
              this.birthday = res.data.data.birthday;
              this.tel = res.data.data.phone;
              this.address = res.data.data.address;
              resolve(res.data);
            } else {
              // this.name = "";
              // this.avatar = "";
              // this.email = "";
              // this.birthday = "";
              // this.tel = "";
              // this.address = "";
              // removeToken();
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
              resolve(res.data);
            }
          })
          .catch((error) => {
            // this.name = "";
            // this.avatar = "";
            // this.email = "";
            // this.birthday = "";
            // this.tel = "";
            // this.address = "";
            // removeToken();
            ElMessage({
              type: "error",
              message: i18n.global.t('user.infoError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
    async changeUserInfo(userInfo) {
      return new Promise((resolve, reject) => {
        changeUserInfo(this.token, userInfo)
          .then((res) => {
            if (res.data.success) {
              this.name = userInfo.uname;
              this.avatar = userInfo.avatar;
              this.email = userInfo.email;
              this.birthday = userInfo.birthday;
              this.tel = userInfo.phone;
              this.address = userInfo.address;
              resolve(res.data);
            } else {
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
              resolve(res.data);
            }
          })
          .catch((error) => {
            ElMessage({
              type: "error",
              message: i18n.global.t('user.changeInfoError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
    logout() {
      return new Promise((resolve, reject) => {
        signout(this.token)
          .then((res) => {
            if (res.data.success) {
              this.fedLogOut();
              resolve();
            } else {
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
            }
          })
          .catch((error) => {
            ElMessage({
              type: "error",
              message: i18n.global.t('user.logoutError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
    // 前端 登出
    fedLogOut() {
      return new Promise((resolve) => {
        this.name = "";
        this.avatar = "";
        this.email = "";
        this.birthday = "";
        this.tel = "";
        this.address = "";
        this.token = "";
        removeToken();
        resolve();
      }).catch((error) => {
        reject(error);
      });
    },
    register(user) {
      return new Promise((resolve, reject) => {
        register(user.uname, user.pwd, user.email)
          .then((res) => {
            if (res.data.success) {
              setToken(res.data.data);
              this.token = getToken();
              this.getAvatarUname();
              resolve();
            } else {
              ElMessage({
                type: "error",
                message: res.data.msg,
                showClose: true,
                grouping: true,
              });
              reject(res.data.msg);
            }
          })
          .catch((error) => {
            ElMessage({
              type: "error",
              message: i18n.global.t('user.registerError'),
              showClose: true,
              grouping: true,
            });
            reject(error);
          });
      });
    },
  },
  persist: {
    enabled: true
  }
});

export default useUserStore;
