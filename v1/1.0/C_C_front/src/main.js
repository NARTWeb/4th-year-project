import { createApp } from "vue";
import { createPinia } from "pinia";
import ElementPlus from "element-plus";
import axios from "axios";
import VueAxios from "vue-axios";
import i18n from "./locals";
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import InfiniteLoading from './util/infinite-loading.js';
import App from "./App.vue";
import router from "./router";

import "./assets/css/main.css";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(ElementPlus);
app.use(VueAxios, axios);
app.use(InfiniteLoading);
app.use(i18n);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.mount("#app");