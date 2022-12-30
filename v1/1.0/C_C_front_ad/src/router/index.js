import { createRouter, createWebHistory } from 'vue-router'

const ADMainPage = () => import('@/views/ADMainPage.vue')
const ADLogin = () => import('@/views/loginAndRegister/Login.vue')

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/loginad',
      name: 'loginad',
      component: ADLogin
    },
    {
      path: '/',
      name: 'mainad',
      component: ADMainPage
    },
  ]
});

router.beforeEach((to, from, next) => {
  console.log(localStorage.getItem('token'));
  if (!localStorage.getItem('token')) {
    if (to.name == "login" || to.name == "register") {
      next();
    } else {
      router.push('login');
    }
  } else {
    next();
  }
});


export default router