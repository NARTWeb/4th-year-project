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
      searchHistory: "",
      loading: false,
      nodata: false,
      fList: [],
      counter: 0,
      page: {
        pageSize: 8,
        pageNum: 0,
      },
    };
  },
  actions: {
    tList() {
      if (!this.loading && !this.nodata) {
        this.loading = true;
        searchFriend(token, this.searchHistory, this.page)
          .then((res) => {
            if (res.data.success) {
              if (res.data.data.length > 0) {
                if (this.fList == undefined) {
                    this.fList = res.data.data;
                } else {
                    this.fList.push(...res.data.data);
                }
                this.page.pageNum += 1;
              } else {
                this.nodata = true;
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
            this.loading = false;
          });
      }
    },
    loadFirstList() {
      this.loading = false;
      this.nodata = false;
      this.page.pageNum = 0;
      this.counter = 0;
      if (this.fList != undefined) {
        this.fList.splice(0, this.fList.length);
      }
      this.loadNewFriends();
    },
    loadNewFriends() {
      let tempName = this.searchHistory;
      for (let i = 0; i < this.page.pageSize; i++) {
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
