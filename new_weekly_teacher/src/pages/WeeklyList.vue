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
                        prop="study_progress"
                        label="学习进度"
                        width="450">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="primary"
                                :disabled="!isDisable(scope.row.comment)"
                                @click="handleJudge(scope.row)">进行审阅</el-button>
                        <el-button
                                size="mini"
                                type="warning"
                                :disabled="isDisable(scope.row.comment)"
                                @click="modifyJudge(scope.row)">修改审阅</el-button>
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


        <!--关于进行审阅的弹出框-->
        <el-dialog title="审阅周报" :visible.sync="judgeVisible" width="700px">
            <el-form
                    ref="judgeForm"
                    :model="judgeForm"
                    :rules="rules"
                    label-width="80px"
                    :labelPosition="labelPosition">
                <el-form-item label="学习进度" size="mini" prop="study_progress">
                    <el-input type="textarea" v-model="judgeForm.study_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="日常进度" size="mini" prop="daily_progress">
                    <el-input type="textarea" v-model="judgeForm.daily_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="科研开发投入时间">
                    <el-select v-model="judgeForm.total_time" style="width: 10%;">
                        <el-option
                                v-for="num in 80"
                                :key="num"
                                :label=num
                                :value=num></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="分配情况" size="mini" prop="time_allocation">
                    <el-input type="textarea" v-model="judgeForm.time_allocation" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="心得体会" size="mini" prop="experience">
                    <el-input type="textarea" v-model="judgeForm.experience" rows="4"></el-input>
                </el-form-item>

                <el-form-item label="审阅意见" size="mini" prop="comment">
                    <el-input type="textarea" v-model="judgeForm.comment" rows="4"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="judgeVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="saveJudge('judgeForm')">确定</el-button>
            </span>
        </el-dialog>

        <!--关于修改审阅的弹出框-->
        <el-dialog title="修改审阅" :visible.sync="modifyJudgeVisible" width="700px">
            <el-form
                    ref="modifyJudgeForm"
                    :model="modifyJudgeForm"
                    :rules="rules"
                    label-width="80px"
                    :labelPosition="labelPosition">
                <el-form-item label="学习进度" size="mini" prop="study_progress">
                    <el-input type="textarea" v-model="modifyJudgeForm.study_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="日常进度" size="mini" prop="daily_progress">
                    <el-input type="textarea" v-model="modifyJudgeForm.daily_progress" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="科研开发投入时间">
                    <el-select v-model="modifyJudgeForm.total_time" style="width: 10%;">
                        <el-option
                                v-for="num in 80"
                                :key="num"
                                :label=num
                                :value=num></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="分配情况" size="mini" prop="time_allocation">
                    <el-input type="textarea" v-model="modifyJudgeForm.time_allocation" rows="4"></el-input>
                </el-form-item>
                <el-form-item label="心得体会" size="mini" prop="experience">
                    <el-input type="textarea" v-model="modifyJudgeForm.experience" rows="4"></el-input>
                </el-form-item>

                <el-form-item label="审阅意见" size="mini" prop="comment">
                    <el-input type="textarea" v-model="modifyJudgeForm.comment" rows="4"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="modifyJudgeVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="saveModifyJudge('modifyJudgeForm')">确定</el-button>
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
                judgeVisible:false,    //显示审阅框
                modifyJudgeVisible:false,//显示修改审阅框
                judgeForm: {
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
                modifyJudgeForm: {
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
                    comment: [
                        { required: true, message: '审阅意见不许为空', trigger: 'blur' }
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
            this.getWeeklyById(this.$store.state.stu_id)
        },

        methods: {

            isDisable(comment){
                if(comment === '' || comment==null){
                    return true
                }
                else{
                    return false
                }
            },
            getWeeklyById(id){
                HttpManager.getWeeklyById(id).then(res =>{
                    for(let i in res) {
                        this.weekly.push(res[i])
                    }
                })
            },
            //打开第一次审阅周报的显示框
            handleJudge(row){
                this.judgeForm = {
                    id: row.id,
                    owner_id: row.owner_id,
                    study_progress: row.study_progress,
                    daily_progress: row.daily_progress,
                    total_time: row.total_time,
                    time_allocation: row.time_allocation,
                    experience: row.experience,
                    post_time: row.post_time
                }
                this.judgeVisible = true
            },
            //保存第一次审阅周报的结果
            saveJudge(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //通过验证
                        let params = new URLSearchParams()

                        params.append('id',this.judgeForm.id);
                        params.append('comment',this.judgeForm.comment);

                        HttpManager.updateJudgementById(params).then(res => {
                            if(res.code === 1){
                                this.notify('审阅成功', 'success')
                                this.weekly=[]
                                this.getWeeklyById(this.$store.state.stu_id)
                            }else {
                                this.notify('审阅失败', 'error')
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                        this.judgeVisible = false
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            //打开修改审阅的显示框
            modifyJudge(row){
                this.modifyJudgeForm = {
                    id: row.id,
                    owner_id: row.owner_id,
                    study_progress: row.study_progress,
                    daily_progress: row.daily_progress,
                    total_time: row.total_time,
                    time_allocation: row.time_allocation,
                    experience: row.experience,
                    post_time: row.post_time,
                    comment: row.comment
                }
                this.modifyJudgeVisible = true
            },
            saveModifyJudge: function (formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //通过验证
                        let params = new URLSearchParams()

                        params.append('id', this.modifyJudgeForm.id);
                        params.append('comment', this.modifyJudgeForm.comment);

                        HttpManager.updateJudgementById(params).then(res => {
                            if (res.code === 1) {
                                this.notify('修改审阅成功', 'success')
                                this.weekly = []
                                this.getWeeklyById(this.$store.state.stu_id)
                            } else {
                                this.notify('修改审阅失败', 'error')
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                        this.modifyJudgeVisible = false
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
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
