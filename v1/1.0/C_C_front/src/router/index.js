import { createRouter, createWebHistory } from 'vue-router'

const MainPage = () => import('@/views/MainPage.vue')
const FindSthNew = () => import('@/views/FindSthNew.vue')
const CreateGroup = () => import ('@/views/createGroup.vue')
const ReqList = () => import('@/views/RequestList.vue')
const InviteList = () => import('@/views/InviteList.vue')
const StatusList = () => import('@/views/StatusList.vue')

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
        },
        {
          path: '/friendRequests',
          name: 'reqList',
          component: ReqList
        },
        {
          path: '/groupInvites',
          name: 'inviteList',
          component: InviteList
        },
        {
          path: '/status',
          name: 'statusList',
          component: StatusList
        }
      ]
    }
  ]
})

export default router