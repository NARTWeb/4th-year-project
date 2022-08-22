import { createRouter, createWebHistory } from 'vue-router'

const MainPage = () => import('@/views/MainPage.vue')
const FindSthNew = () => import('@/views/FindSthNew.vue')
const CreateGroup = () => import ('@/views/createGroup.vue')

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainPage,
      children: [
        {
          path: '/findSthNew',
          name: 'findNew',
          component: FindSthNew,
          children: [
            {
              path: '/findSthNew/createGroup',
              name: 'createGroup',
              component: CreateGroup
            }
          ]
        }
      ]
    }
  ]
})

export default router