import { get, post } from './request'

const HttpManager = {
  // =======================> 学生 API
  // 判断是否登录成功
  getLoginStatus: (params) => post(`student/login/status`, params),

  //获取通知公告
  getNotice: () => get('notice'),

  //获取周报列表
  getWeeklyById: (id) => get(`getweekly?id=${id}`),

  //更新周报
  updateWeekly: (params) => post('updateweekly', params),

  //发布周报
  postWeekly:(params) => post('postweekly',params),

  //修改密码
  modifyPass:(params) => post('modifypass',params)


}
export { HttpManager }
