<template>
    <div>
        <el-empty
                description="暂未发布通知"
        v-show="notices_num===0"></el-empty>

        <el-card
                class="box-card"
                style="margin-bottom: 10px;width: 100%">
            <el-button type="primary" size="mini" @click="postVisible = !postVisible">添加新通知</el-button>

            <el-card
                    class="box-card"
                    v-for="item in notices"
                    :key="item.id"
                    style="margin-bottom: 20px;width: 60%;margin-left: 20%;  background-color:	#F5FFFA">
                <div slot="header" class="clearfix">
                    <span class="title">{{item.title}}</span>
                    <el-button
                            size="mini"
                            type="danger"
                            class="btn"
                            @click="deleteNoticeById(item.id)">删除</el-button>
                </div>
                <div class="text item">
                    {{item.content}}
                </div>
            </el-card>
        </el-card>

        <el-dialog title="发布通知" :visible.sync="postVisible" width="700px" center>
            <el-form
                    ref="ruleForm"
                    :model="ruleForm"
                    :rules="rules"
                    label-width="80px"
                    :labelPosition="labelPosition">
                <el-form-item label="标题" prop="title">
                    <el-input  v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="通知内容" prop="content">
                    <el-input type="textarea" v-model="ruleForm.content" rows="6"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitNotice('ruleForm')">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import { HttpManager } from '../api/index'
    import {mixin} from "../mixins";

    export default {
        mixins:[mixin],
        data() {
            return {
                notices:{},
                notices_num:0,
                labelPosition:"left",
                postVisible:false,
                ruleForm: {
                    title: '',
                    content: '',
                },
                rules: {
                    title: [
                        { required: true, message: '标题不许为空', trigger: 'blur' }
                    ],
                    content:[
                        { required: true, message: '内容不许为空', trigger: 'blur' }
                    ],
                },
            }
        },
        mounted() {
            this.getNotice();
        },
        methods:{
            getNotice(){
                HttpManager.getNotice().then(res =>{
                    this.notices=res;
                    this.notices_num=res.length
                })
            },
            submitNotice(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //通过验证
                        let params = new URLSearchParams()

                        params.append('title',this.ruleForm.title);
                        params.append('content',this.ruleForm.content);

                        HttpManager.postNotice(params).then(res => {
                            if(res.code === 1){
                                this.notify('提交成功', 'success')
                                this.getNotice()
                                this.$refs[formName].resetFields()
                                this.postVisible = false
                            }else {
                                this.notify('提交失败', 'error')
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            deleteNoticeById(id){
                HttpManager.deleteNoticeById(id).then(res =>{
                    if(res.code===1){
                        this.notify('删除成功', 'success')
                        this.notices_num--
                        this.getNotice()
                    }else {
                        this.notify('删除失败', 'error')
                    }
                }).catch(err => {
                    console.log(err)
                })
            }
        }
    }
</script>

<style scoped>
    .title{
        font-size: 20px;
        font-weight: bold;
    }
    .btn{
   float: right;
    }
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }


</style>
