<template>
    <div>
        <el-card
                class="box-card"
                style="margin-bottom: 10px">
            <el-button type="primary" size="mini" @click="addStuVisible = ! addStuVisible">添加新学生</el-button>
            <el-table
                    :data="tableData"
                    stripe
                    border
                    style="width: 60%;margin-left: 20%">
                <el-table-column
                        prop="study_number"
                        label="学号"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                </el-table-column>


                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="primary"
                                @click="handleBrowse(scope.row.id)"
                                >查看周报</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="deleteStudent(scope.row.id)"
                        >删除学生</el-button>
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
                        :total="tableData.length">
                </el-pagination>
            </div>

        </el-card>

        <el-dialog title="添加学生" :visible.sync="addStuVisible" width="700px">
            <el-form
                    ref="ruleForm"
                    :model="ruleForm"
                    :rules="rules"
                    label-width="80px"
                    :labelPosition="labelPosition">
                <el-form-item label="学号" size="mini" prop="study_number">
                    <el-input v-model="ruleForm.study_number"></el-input>
                </el-form-item>
                <el-form-item label="姓名" size="mini" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="密码" size="mini" prop="password">
                    <el-input v-model="ruleForm.password"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="addStuVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="saveAdd('ruleForm')">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import { HttpManager } from '../api/index'
    import {mixin} from "../mixins";
    import store from "../store";

    export default {
        mixins: [mixin],
        data() {
            return {
                labelPosition:"left",
                currentPage: 1, // 当前页
                pageSize:9,//显示内容个数
                tableData: [],
                addStuVisible:false,
                ruleForm: { // 记录编辑的信息
                    study_number:'',
                    name:'',
                    password:''
                },
                rules: {
                    study_number: [
                        { required: true, message: '学号不许为空', trigger: 'blur' }
                    ],
                    name:[
                        { required: true, message: '姓名不许为空', trigger: 'blur' }
                    ],
                    password:[
                        { required: true, message: '密码不许为空', trigger: 'blur' }
                    ]
                },
            }
        },
        mounted() {
            this.getStudent()
        },
        methods: {
            getStudent(){
                HttpManager.getStudent().then(res => {
                    console.log(res)
                    for(let i in res) {
                        this.tableData.push(res[i])
                    }
                })
            },
            handleBrowse(id){
                this.$store.commit("setStu_id", id);
                console.log()
                this.$router.push('weeklylist')
            },
            saveAdd(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //通过验证
                        let params = new URLSearchParams()

                        params.append('study_number',this.ruleForm.study_number);
                        params.append('name',this.ruleForm.name);
                        params.append('password',this.ruleForm.password);

                        HttpManager.addStudent(params).then(res => {
                            if(res.code === 1){
                                this.notify('添加成功', 'success')
                                this.tableData=[]
                                this.getStudent()
                            }else {
                                this.notify('添加失败', 'error')
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                        this.addStuVisible = false
                    }
                    else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            deleteStudent(id){
                let params = new URLSearchParams()

                params.append('id',id);

                HttpManager.deleteStudent(params).then(res => {
                    if(res.code === 1){
                        this.notify('删除成功', 'success')
                        this.tableData=[]
                        this.getStudent()
                    }else {
                        this.notify('删除失败', 'error')
                    }
                }).catch(err => {
                    console.log(err)
                })
            }
        },

    }
</script>

<style scoped>
    .pagination {
        display: flex;
        justify-content: center;
    }
</style>
