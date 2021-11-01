import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)



const constantRoutes = [
  {
    path: '/',
    component: resolve => require(['../pages/Login.vue'], resolve)
  },
  {
    path: '/Home',
    component: resolve => require(['../components/Home.vue'], resolve),
    meta: { title: '自述文件',requireAuth: true, },
    children: [
      {
        path: '/NoticeList',
        component: resolve => require(['../pages/NoticeList.vue'], resolve),
        meta: { title: '通知管理',requireAuth: true, }
      },
      {
        path: '/TwoWeekWeekly',
        component: resolve => require(['../pages/TwoWeekWeekly.vue'], resolve),
        meta: { title: '近两周周报',requireAuth: true, }
      },
      {
        path: '/WeeklyList',
        component: resolve => require(['../pages/WeeklyList.vue'], resolve),
        meta: { title: '周报管理',requireAuth: true, }
      },
      {
        path: '/FileList',
        component: resolve => require(['../pages/FileList.vue'], resolve),
        meta: { title: '文件管理',requireAuth: true, }
      },
      {
        path: '/StudentList',
        component: resolve => require(['../pages/StudentList.vue'], resolve),
        meta: { title: '学生管理',requireAuth: true, }
      },
      {
        path: '/BigNews',
        component: resolve => require(['../pages/BigNews.vue'], resolve),
        meta: { title: '重要信息汇总',requireAuth: true, }
      },
      {
        path: '/ModifyPassword',
        component: resolve => require(['../pages/ModifyPassword.vue'], resolve),
        meta: { title: '修改密码',requireAuth: true, }
      },
    ]
  }

]

const lawfulRoute = ['/','/Home','/NoticeList','/WeeklyList','/TwoWeekWeekly','/FileList','/StudentList','/BigNews','/ModifyPassword']


const router = new Router({
  mode: 'history', // 去掉url中的#
  routes: constantRoutes
})
router.beforeEach((to, from, next) => {
  if(to.meta.title){
    document.title=to.meta.title
  }
  if (to.meta.requireAuth) {

    //获取会话对象中的用户信息
    let name = JSON.parse(sessionStorage.vuex).name
    if (name !== '' && name !== undefined) {
      console.log("可以进入")
      next()
    } else {
      console.log("不可以进入")
      next("/");
    }
  }
  //用于防止第一次登录前的随意访问
  else if(lawfulRoute.findIndex((item,index) =>{
    return item === to.fullPath
  })){
    console.log("不可以进入")
    next("/");
  }
  else {
    next()
  }
})




export default router;
