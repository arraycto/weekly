<template>
    <div>
        <el-card
                class="box-card"
                style="margin-bottom: 10px">

            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
            <el-table-column
                    prop="post_time"
                    label="提交时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="total_time"
                    label="科研开发投入时间（单位：小时）"
                    width="280">
            </el-table-column>
            <el-table-column
                    prop="comment"
                    label="导师评价"
                    width="450">
            </el-table-column>
                <el-table-column
                        prop="judge_time"
                        label="审批时间"
                        width="200">
                </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="success"
                            @click="handleBrowse(scope.row)">详情</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            :disabled="!isEditable(scope.row.comment)"
                            @click="handleEdit(scope.row)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
            <div class="pagination">
                <el-pagination
                        @current-change="handleCurrentChange"
                        background
                        layout="total, prev, pager, next"
                        :current-page="currentPage"
                        :page-size="pageSize"
                        :total="weekly.length">
                </el-pagination>
            </div>
        </el-card>


        <!-- 关于查看的弹出框 -->
        <el-dialog title="周报详情" :visible.sync="browseVisible" width="700px">
            <el-form :model="ruleForm" label-width="80px" :labelPosition="labelPosition">
                <el-form-item label="学习进度" size="mini">
                    <el-input type="textarea" v-model="ruleForm.study_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="日常进度" size="mini">
                    <el-input type="textarea" v-model="ruleForm.daily_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="科研开发投入时间">
                    <el-select v-model="ruleForm.total_time" style="width: 10%;" disabled>
                        <el-option
                                v-for="num in 80"
                                :key="num"
                                :label=num
                                :value=num></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="分配情况" size="mini">
                    <el-input type="textarea" v-model="ruleForm.time_allocation" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="心得体会" size="mini">
                    <el-input type="textarea" v-model="ruleForm.experience" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="提交时间" size="mini">
                    <el-input v-model="ruleForm.post_time" disabled></el-input>
                </el-form-item>
                <el-form-item label="导师评价" size="mini">
                    <el-input type="textarea" v-model="ruleForm.comment" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="审批时间" size="mini">
                    <el-input v-model="ruleForm.judge_time" disabled></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" size="mini" @click="browseVisible = false">确定</el-button>
            </span>
        </el-dialog>

        <!--关于编辑的弹出框-->
        <el-dialog title="编辑周报" :visible.sync="editVisible" width="700px">
            <el-form
                    ref="ruleForm"
                    :model="ruleForm"
                    :rules="rules"
                    label-width="80px"
                    :labelPosition="labelPosition">
                <el-form-item label="学习进度" size="mini" prop="study_progress">
                    <el-input type="textarea" v-model="ruleForm.study_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="日常进度" size="mini" prop="daily_progress">
                    <el-input type="textarea" v-model="ruleForm.daily_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="科研开发投入时间">
                    <el-select v-model="ruleForm.total_time" style="width: 10%;">
                        <el-option
                                v-for="num in 80"
                                :key="num"
                                :label=num
                                :value=num></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="分配情况" size="mini" prop="time_allocation">
                    <el-input type="textarea" v-model="ruleForm.time_allocation" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="心得体会" size="mini" prop="experience">
                    <el-input type="textarea" v-model="ruleForm.experience" rows="4"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="editVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="saveEdit('ruleForm')">确定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    import { HttpManager } from '../api/index'
    import {mixin} from "../mixins";

    export default {
        mixins: [mixin],
      data() {

        return {
            currentPage: 1, // 当前页
            pageSize:9,//显示内容个数

            labelPosition:"top",//标题位置
            weekly: [],
            browseVisible: false, // 显示查看框
            editVisible:false,    //显示编辑框
            ruleForm: { // 记录编辑的信息
                id: '',
                owner_id:'',
                study_progress: '',
                daily_progress: '',
                total_time: '',
                time_allocation: '',
                experience: '',
                post_time:'',
                comment:'',
                judge_time:''
            },
            rules: {
                study_progress: [
                    { required: true, message: '不许为空', trigger: 'blur' }
                ],
                daily_progress:[
                    { required: true, message: '不许为空', trigger: 'blur' }
                ],
                time_allocation:[
                    { required: true, message: '不许为空', trigger: 'blur' }
                ],
                experience:[
                    { required: true, message: '不许为空', trigger: 'blur' }
                ],
            },
        }
      },
      computed:{
          // 计算当前表格中的数据
          tableData () {
              return this.weekly.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
          },


      },

      mounted() {
        this.getWeeklyById(this.$store.state.id)
      },

      methods: {

          // 获取当前页
          handleCurrentChange (val) {
              this.currentPage = val
          },

          isEditable(comment){
              if(comment!==null){
                  return false
              }
              return true
          },
          getWeeklyById(id){
              HttpManager.getWeeklyById(id).then(res =>{
                  // console.log(typeof res)
                  // console.log(res)
                  // for(let i in res) {
                  //     console.log(i,":",res[i]);
                  // }
                  // this.weekly=res;
                  //这里传过来的是对象,但是表格需要数组类型，所以遍历对象，取出json对象添加到数组中
                  for(let i in res) {
                      this.weekly.push(res[i])
                  }
              })
          },
          handleEdit(row){
              this.ruleForm = {
                  id: row.id,
                  owner_id: row.owner_id,
                  study_progress: row.study_progress,
                  daily_progress: row.daily_progress,
                  total_time: row.total_time,
                  time_allocation: row.time_allocation,
                  experience: row.experience,
                  post_time: row.post_time
              }
              this.editVisible = true
          },
          saveEdit(formName){
              this.$refs[formName].validate((valid) => {
                  if (valid) {
                      //通过验证
                      let params = new URLSearchParams()

                      params.append('id',this.ruleForm.id);
                      params.append('study_progress',this.ruleForm.study_progress);
                      params.append('daily_progress',this.ruleForm.daily_progress);
                      params.append('total_time',this.ruleForm.total_time);
                      params.append('time_allocation',this.ruleForm.time_allocation);
                      params.append('experience',this.ruleForm.experience);

                      HttpManager.updateWeekly(params).then(res => {
                          if(res.code === 1){
                              this.weekly=[]//清空缓存，防止脏数据

                              this.getWeeklyById(this.$store.state.id)//重新获取数据
                              this.notify('修改成功', 'success')
                          }else {
                              this.notify('修改失败', 'error')
                          }
                      }).catch(err => {
                          console.log(err)
                      })
                      this.editVisible = false
                  } else {
                      console.log('error submit!!');
                      return false;
                  }
              });


          },
          handleBrowse(row){
              this.ruleForm = {
                  id: row.id,
                  owner_id: row.owner_id,
                  study_progress: row.study_progress,
                  daily_progress: row.daily_progress,
                  total_time: row.total_time,
                  time_allocation: row.time_allocation,
                  experience: row.experience,
                  post_time: row.post_time,
                  comment: row.comment,
                  judge_time: row.judge_time
              }
              this.browseVisible = true
          },
      },
}

</script>

<style scoped>
    .pagination {
        display: flex;
        justify-content: center;
    }
</style>
