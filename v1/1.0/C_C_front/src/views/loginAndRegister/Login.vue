<template>
  <div class="h-full flex flex-col flex-nowrap justify-center items-center">
    <div
      id="shadow"
      class="border-dotted border-0 border-gray-600 rounded-3xl py-12 px-12 backdrop-blur-md bg-white/30 flex flex-col flex-nowrap justify-center items-center"
    >
      <header>
        <div
          v-if="this.$route.query.lor === 'login'"
          class="text-white my-12 text-4xl mt-5"
        >
          登录
        </div>
        <div v-else class="text-white my-12 text-4xl mt-5">注册</div>
      </header>
      <div>
        <form ref="form" @submit.prevent="submit">
          <div class="flex justify-center">
            <div>
              <div class="form-floating mb-8 xl:w-80">
                <input
                  type="text"
                  class="form-control block w-full px-2 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                  id="username"
                  name="username"
                  ref="username"
                  v-model="username"
                  placeholder="Username"
                />
              </div>
              <div class="form-floating mb-3 xl:w-80">
                <input
                  type="password"
                  class="form-control block w-full px-2 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                  id="password"
                  name="password"
                  ref="password"
                  v-model="password"
                  placeholder="Password"
                />
              </div>
            </div>
          </div>
        </form>
      </div>
      <div>
        <button
          @click="regOrLogin"
          class="flex2 bg-rose-800 hover:bg-yellow-300 text-white font-bold py-2 px-16 rounded-2xl my-8 text-2xl"
        >
          <div v-if="this.$route.query.lor === 'login'">登录</div>
          <div v-else>注册</div>
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import tool from "@/utils/tool.js";

export default {
  data() {
    return {
      uname: "",
      pwd: "",
    };
  },
  methods: {
    regOrLogin: function () {
      let uname = this.uname;
      let pwd = this.pwd;
      if (!tool.isNotNull(uname)) {
        // 获得焦点
        this.$refs.username.focus();
      } else if (!tool.isNotNull(pwd)) {
        this.$refs.password.focus();
      } else {
        // 判断用户输入是否合法
        if (uname.length > 12) {
          alert("用户名不能超过12位");
          return false;
        } else if (pwd.length > 6) {
          alert("密码不能超过6位");
          return false;
        }
        axios
          .post(tool.serverUrl + "user/registerOrLogin", {
            params: {
              uname: this.uname,
              pwd: this.pwd,
            },
            timeout: 10000, // 设置超时间为10s
          })
          .then(
            (res) => {
              console.log(JSON.stringify(res.data));
            },
            (err) => {
              console.log(err);
            }
          );
      }
      console.log(uname);
      console.log(pwd);
    },
  },
  computed: {
    username: {
      get() {
        return this.uname;
      },
      set(val) {
        this.uname = val.trim();
      },
    },
    password: {
      get() {
        return this.pwd;
      },
      set(val) {
        this.pwd = val.trim();
      },
    },
  },
};
</script>
<style>
@font-face {
  font-family: "GMC";
  src: url("../../GenkaiMinCho.ttf");
}
#shadow:hover {
  box-shadow: -5px 15px 50px 10px white;
  /* transform: translateY(-11px); */
  /* transition: opacity 2s ease-in-out; */
  transition-duration: 0.7s;
}
</style>
