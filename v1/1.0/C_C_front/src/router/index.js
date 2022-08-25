import { createRouter, createWebHistory } from 'vue-router'

const MainPage = () => import('@/views/MainPage.vue')
const FindSthNew = () => import('@/views/FindSthNew.vue')
const CreateGroup = () => import ('@/views/createGroup.vue')
const ReqList = () => import('@/views/RequestList.vue')
const InviteList = () => import('@/views/InviteList.vue')
const StatusList = () => import('@/views/StatusList.vue')
const AddNewFriend = () => import('@/views/AddNewFriend.vue')
const SearchFriend = () => import('@/views/SearchFriendIHave.vue')
const CheckAllMyStatus = () => import('@/views/CheckAllMyStatus.vue')
const PostStatus = () => import('@/views/PostStatus.vue')
const ChatRoom = () => import('@/views/ChatRoom.vue')

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
            },
            {
              path: '/findSthNew/searchNew',
              name: 'addNewFriend',
              component: AddNewFriend
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
          path: '/searchMyFriend',
          name: 'searchFriend',
          component: SearchFriend
        },
        {
          path: '/status',
          name: 'statusList',
          component: StatusList
        },
        {
          path: '/myStatus',
          name: 'myStatus',
          component: CheckAllMyStatus
        },
        {
          path: '/postStatus',
          name: 'postStatus',
          component: PostStatus
        },
        {
          path: '/chatRoom/:id',
          name: 'chatRoom',
          component: ChatRoom
        }
      ]
    }
  ]
})

export default router