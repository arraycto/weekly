<template>
    <div>
        <el-card class="box-card">
            <div class="form">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="旧密码" prop="oldPass">
                        <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newPass">
                        <el-input type="password" v-model="ruleForm.newPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" prop="checkNewPass">
                        <el-input type="password" v-model="ruleForm.checkNewPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script>
    import {HttpManager} from "../api";
    import {mixin} from "../mixins";

    export default {
        mixins: [mixin],
        data(){
            let validateOldPass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入旧密码！'));
                } else if (value !== this.$store.state.password) {
                    callback(new Error('输入的旧密码不对!'));
                } else {
                    callback();
                }
            };
            let validateNewPass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入新密码！'));
                } else if (value === this.$store.state.password) {
                    callback(new Error('新密码不能与旧密码相同！'));
                } else {
                    callback();
                }
            };
            let validateCheckNewPass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码！'));
                } else if (value !== this.ruleForm.newPass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    oldPass: '',
                    newPass: '',
                    checkNewPass: ''
                },
                rules: {
                    oldPass: [
                        { validator: validateOldPass,required: true, trigger: 'blur' }
                    ],
                    newPass: [
                        { validator: validateNewPass,required: true, trigger: 'blur' }
                    ],
                    checkNewPass: [
                        { validator: validateCheckNewPass, required: true, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //通过验证

                        let params = new URLSearchParams()
                        params.append('id',this.$store.state.id)
                        params.append('newPass',this.ruleForm.newPass)

                        HttpManager.modifyPass(params).then(res => {
                            if(res.code === 1){
                                this.notify('修改密码成功，请重新登录', 'success')
                                //Vuex存储的数据清空
                                this.$store.commit("setId", '');
                                this.$store.commit("setName", '');
                                this.$store.commit("setPassword", '');

                                console.log(this.$store.state.id)
                                this.$router.push('/')

                            }else {
                                this.notify('修改密码失败', 'error')
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
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
.form{
    width: 40%;
    margin-left: 30%;
}
</style>
