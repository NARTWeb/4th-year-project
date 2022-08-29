import { createRouter, createWebHistory } from 'vue-router'

const MainPage = () => import('@/views/MainPage.vue')
const FindSthNew = () => import('@/views/FindSthNew.vue')
const CreateGroup = () => import ('@/views/searchAndCreate/createGroup.vue')
const AddNewFriend = () => import('@/views/searchAndCreate/AddNewFriend.vue')
const SearchFriend = () => import('@/views/searchAndCreate/SearchFriendIHave.vue')
const PopWinFriendList = () => import('@/views/searchAndCreate/PopWinFriendList.vue')
const PostStatus = () => import('@/views/searchAndCreate/PostStatus.vue')
const ReqList = () => import('@/views/lists/RequestList.vue')
const InviteList = () => import('@/views/lists/InviteList.vue')
const StatusList = () => import('@/views/lists/StatusList.vue')
const CheckAllMyStatus = () => import('@/views/lists/CheckAllMyStatus.vue')
const ChatRoom = () => import('@/views/chat/ChatRoom.vue')
const CheckFriendInfo = () => import('@/views/infos/CheckFriendInfo.vue')
const EditMyInfo = () => import('@/views/infos/EditMyInfo.vue')
const GroupChatSetting = () => import('@/views/infos/GroupChatSetting.vue')
const Login = () => import('@/views/loginAndRegister/Login.vue')
const Welcome = () => import('@/views/loginAndRegister/Welcome.vue')

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/lORr',
      name: 'lorr',
      component: Welcome,
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Login
    },
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
        },
        {
          path: '/friendInfo/:id',
          name: 'friendInfo',
          component: CheckFriendInfo
        },
        {
          path: '/editMyInfo',
          name: 'editMyInfo',
          component: EditMyInfo
        },
        {
          path: '/groupChatInfo',
          name: 'groupChatInfo',
          component: GroupChatSetting
        },
        {
          path: '/popWin',
          name: 'popWin',
          component: PopWinFriendList
        }
      ]
    }
  ]
});

router.beforeEach((to, from, next)=>{
  console.log(to);
  console.log(from);
  next();//通行证
})


export default router