import { defineStore } from "pinia";
import { getToken, setToken, removeToken } from "@/request/token";
import { signin, signout, register, showUserInfo, showAvatarUname, changeUserInfo} from "@/api/user.js";
import { ElMessage } from 'element-plus'

const useUserStore = defineStore("user", {
  state: () => {
    return {
      token: "jkdflsf;jkadfs",
      name: "qiao",
      avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
      email: "",
      tel: "",
      address: "",
      birthday: "",
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
        signin(user.account, user.password)
          .then((res) => {
            if (res.data.success) {
              this.token = res.data.data;
              setToken(res.data.data);
              resolve();
            } else {
              reject(res.data.msg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    getAvatarUname() {
      return new Promise((resolve, reject) => {
        showAvatarUname(this.token)
          .then((res) => {
            if (res.data.success) {
              this.name = res.data.data.uname;
              this.avatar = res.data.data.avatar;
              resolve();
            } else {
              reject(res.data.msg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    getUserInfo() {
      return new Promise((resolve, reject) => {
        showUserInfo(this.token)
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
              this.name = "";
              this.avatar = "";
              this.email = "";
              this.birthday = "";
              this.tel = "";
              this.address = "";
              removeToken();
              resolve(res.data);
            }
          })
          .catch((error) => {
            this.name = "";
            this.avatar = "";
            this.email = "";
            this.birthday = "";
            this.tel = "";
            this.address = "";
            removeToken();
            reject(error);
          });
      });
    },
    changeUserInfo(userInfo) {
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
              resolve(res.data);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    logout() {
      return new Promise((resolve, reject) => {
        signout(this.token)
          .then((res) => {
            if (res.data.success) {
              this.name = "";
              this.avatar = "";
              this.email = "";
              this.birthday = "";
              this.tel = "";
              this.address = "";
              this.token = "";
              removeToken();
              resolve();
            }
          })
          .catch((error) => {
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
              this.token = res.data.data;
              setToken(res.data.data);
              resolve();
            } else {
              reject(res.data.msg);
            }
          })
          .catch((error) => {
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
