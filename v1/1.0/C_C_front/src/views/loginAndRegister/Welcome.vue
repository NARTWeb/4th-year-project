<template>
  <div
    class="flex flex-col w-full h-full flex-nowrap justify-center items-center"
  >
    <div class="font-semibold text-4xl">
      <button
        class="flex1 bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded-3xl mx-10"
        @click="register"
      >
        注册
      </button>

      <button
        class="flex2 bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded-3xl mx-10"
        @click="login"
      >
        登录
      </button>
    </div>
  </div>
</template>
<script>
import router from "@/router";

export default {
  data() {
    return {};
  },
  methods: {
    register: function () {
      router.push({
        path: "/register",
        query: {
          lor: "register",
        },
      });
    },
    login: function () {
      router.push({
        path: "/login",
        query: {
          lor: "login",
        },
      });
    },
  },
  // 创建前设置
  beforeCreate() {
    const urlPah = "../../src/assets/img/bg1.png";
    document
      .querySelector("body")
      .setAttribute("style", "background-image:url('" + urlPah + "');");
    //.setAttribute('style','background-color: cyan');
    document
      .querySelector("body")
      .setAttribute("class", "bg-cover bg-no-repeat w-full h-full");
  },
  // 销毁前清除
  Updated() {
    document.querySelector("body").removeAttribute("style");
  },
};
</script>
<style></style>
