<template>
    <div>
        <el-card class="box-card">
        <el-form
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="80px"
                :labelPosition="labelPosition"
                style="width: 60%;margin-left: 20%">
            <el-form-item label="学习进展" prop="study_progress">
                <el-input type="textarea" v-model="ruleForm.study_progress"  rows="6"></el-input>
            </el-form-item>
            <el-form-item label="科研开发及日常事务进展" prop="daily_progress">
                <el-input type="textarea" v-model="ruleForm.daily_progress" rows="6"></el-input>
            </el-form-item>
            <el-form-item label="科研开发投入时间（单位：小时）" prop="total_time">
                <el-select v-model="ruleForm.total_time" style="width: 30%;">
                    <el-option
                    v-for="num in 80"
                    :key="num"
                    :label="num" :value="num"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="时间分配情况" prop="time_allocation">
                <el-input type="textarea" v-model="ruleForm.time_allocation"  rows="6"></el-input>
            </el-form-item>
            <el-form-item label="心得体会" prop="experience">
                <el-input type="textarea" v-model="ruleForm.experience"  rows="6"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitWeekly('ruleForm')">提交</el-button>
            </el-form-item>

        </el-form>
            <el-upload
                    class="upload-demo"
                    :action="uploadUrl"
                    :show-file-list="true"
                    :limit="1"
                    :on-success="handleUploadSuccess"
                    :before-upload="beforeUpload"
                    style="width: 60%;margin-left: 20%"
                    >
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">文件大小不超过20MB</div>
            </el-upload>


 </el-card>
    </div>
</template>

<script>
    import {HttpManager} from "../api";
    import {mixin} from "../mixins";

    export default {
        mixins: [mixin],
    data() {
      return {
          uploadUrl:'',
            labelPosition:"top",
              ruleForm: {
                  study_progress: '',
                  daily_progress: '',
                  total_time: '',
                  time_allocation: '',
                  experience: ''
                },
              rules: {
                  study_progress: [
                      { required: true, message: '不许为空', trigger: 'blur' }
                  ],
                  daily_progress:[
                      { required: true, message: '不许为空', trigger: 'blur' }
                  ],
                  total_time: [
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

        mounted() {
            this.$message({
                message: '每周只可以写一次周报，如需修改，请去周报列表找对应的周报修改',
                type: 'warning',
                duration:5000
            });
            this.uploadUrl = this.$store.state.HOST+'/upload?id=' + this.$store.state.id + '&name=' + this.$store.state.name
        },
        methods: {
            handleUploadSuccess (res, file) {
                if (res.code === 1) {
                    this.notify('上传成功', 'success')
                } else {
                    this.notify('上传失败', 'error')
                }
            },
            beforeUpload (file) {
                const isLt20M = file.size / 1024 / 1024 < 20
                if (!isLt20M) {
                    this.$message.error('上传文件大小不能超过 20MB!')
                }
                return isLt20M
            },
        submitWeekly(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //通过验证
                    let params = new URLSearchParams()

                    params.append('owner_name',this.$store.state.name);
                    params.append('owner_id',this.$store.state.id);
                    params.append('study_progress',this.ruleForm.study_progress);
                    params.append('daily_progress',this.ruleForm.daily_progress);
                    params.append('total_time',this.ruleForm.total_time);
                    params.append('time_allocation',this.ruleForm.time_allocation);
                    params.append('experience',this.ruleForm.experience);

                    HttpManager.postWeekly(params).then(res => {
                        if(res.code === 1){
                            this.notify('提交成功', 'success')
                            this.$refs[formName].resetFields()
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
    }
  }
</script>

<style scoped>

</style>
