import { get, post} from './request'

const HttpManager = {
  // =======================> 教师 API
  // 判断是否登录成功
  getLoginStatus: (params) => post(`teacher/login/status`, params),

  //获取通知公告
  getNotice: () => get('notice'),

  getStudent:()=> get('student'),

  //获取过去两周的周报
  getWeekly: () => get('gettwoweeksweekly'),

  //获取文件
  getFile: () => get('getfile'),


  //获取周报列表
  getWeeklyById: (id) => get(`getweekly?id=${id}`),

  //保存审批结果
  updateJudgementById:(params) => post('updatejudgement', params),

  //发布tongzhi
  postNotice:(params) => post('postnotice',params),

  //添加学生
  addStudent:(params) => post('addstudent',params) ,

  //删除学生
  deleteStudent:(id) => get('deletestudent',id),

  //修改密码
  modifyPass:(params) => post('modifyteacherpass',params),

  //删除通知
  deleteNoticeById:(id) => get(`deletenotice?id=${id}`)

}
export { HttpManager }
