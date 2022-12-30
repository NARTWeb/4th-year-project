import { ElMessage } from "element-plus";
import { defineStore } from "pinia";
import  useUserStore  from "@/stores/userStore";
import { storeToRefs } from "pinia";
import { searchFriend } from "@/api/friend";
import i18n from '../locals/index.js'

const store = useUserStore();
const { token } = storeToRefs(store);

export const useFriendStore = defineStore("friends", {
  state: () => {
    return {
      fSearchHistory: "",
      gSearchHistory: "",
      fLoading: false,
      fNodata: false,
      gLoading: false,
      gNodata: false,
      fList: [],
      gList: [],
      counter: 0,
      fPage: {
        pageSize: 8,
        pageNum: 1,
      },
      gPage: {
        pageSize: 8,
        pageNum: 1,
      },
    };
  },
  actions: {
    loadNewFriends() {
      if (!this.fLoading && !this.fNodata) {
        this.fLoading = true;
        let temp = this.fSearchHistory;
        if(temp == undefined || temp == "") {
          temp = "-1";
        }
        searchFriend(token.value, temp, this.fPage)
          .then((res) => {
            if (res.data.success) {
              if (res.data.data.length > 0) {
                if (this.fList == undefined) {
                    this.fList = res.data.data;
                } else {
                    this.fList.push(...res.data.data);
                }
                this.fPage.pageNum += 1;
              } else {
                this.fNodata = true;
              }
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
                message: i18n.global.t("newFriendList.loadError"),
                showClose: true,
                grouping: true,
              });
              console.log(err);
            })
          .finally(() => {
            this.fLoading = false;
          });
      }
    },
    loadNewGFriends() {
      if (!this.gLoading && !this.gNodata) {
        this.gLoading = true;
        let temp = this.gSearchHistory;
        if(temp == undefined || temp == "") {
          temp = "-1";
        }
        searchFriend(token.value, temp, this.gPage)
          .then((res) => {
            if (res.data.success) {
              if (res.data.data.length > 0) {
                if (this.gList == undefined) {
                    this.gList = res.data.data;
                } else {
                    this.gList.push(...res.data.data);
                }
                this.gPage.pageNum += 1;
              } else {
                this.gNodata = true;
              }
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
                message: i18n.global.t("newFriendList.loadError"),
                showClose: true,
                grouping: true,
              });
              console.log(err);
            })
          .finally(() => {
            this.gLoading = false;
          });
      }
    },
    loadFirstList() {
      this.fLoading = false;
      this.fNodata = false;
      this.fPage.pageNum = 1;
      this.counter = 0;
      if (this.fList != undefined) {
        this.fList.splice(0, this.fList.length);
      }
      this.loadNewFriends();
    },
    tList() {
      let tempName = this.fSearchHistory;
      for (let i = 0; i < this.fPage.pageSize; i++) {
        let test = [
          {
            id: this.counter.toString(),
            uname: tempName + this.counter.toString(),
            avatar:
              "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
          },
        ];
        if (this.fList == undefined) {
          this.fList = test;
        } else {
          this.fList.push(...test);
        }
        this.counter += 1;
      }
    },
    loadFirstGList(array) {
      this.gLoading = false;
      this.gNodata = false;
      this.gPage.pageNum = 1;
      this.counter = 0;
      if (this.gList != undefined) {
        this.gList.splice(0, this.gList.length);
      }
      this.loadNewGFriends();
      if(this.gList.length <= 7) {
        return;
      }
      this.gList = this.getGList(array);
      while(this.gList.length <= 7) {
        this.loadNewGFriends();
        this.gList = this.getGList(array);
      }
    },
    tGList() {
      let tempName = this.gSearchHistory;
      for (let i = 0; i < this.gPage.pageSize; i++) {
        let test = [
          {
            id: this.counter.toString(),
            uname: tempName + this.counter.toString(),
            avatar:
              "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
          },
        ];
        if (this.gList == undefined) {
          this.gList = test;
        } else {
          this.gList.push(...test);
        }
        this.counter += 1;
      }
    },
    getGList(array) {
      let difference = this.gList.filter(function(obj) {
        return !array.some(function(obj2) {
          return obj.id == obj2.id;
        })
      });
      return difference;
    },
    delGItem(id) {
      for (let i = 0; i < this.gList.length; i++) {
        if (id == this.gList[i].id) {
            let temp = this.gList[i];
            this.gList.splice(i, 1);
            if(this.gList.length <= 7) {
                this.loadNewGFriends();
            }
            return temp;
        }
      }
    },
    delItem(id) {
      for (let i = 0; i < this.fList.length; i++) {
        if (id == this.fList[i].id) {
            this.fList.splice(i, 1);
            if(this.fList.length <= 7) {
                this.loadNewFriends();
            }
            return;
        }
      }
    },
  },
});
