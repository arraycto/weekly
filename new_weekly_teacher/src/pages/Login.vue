<template>
    <div>
        <div class="bg-img">
            <img src="../assets/img/bg3.jpg" width="100%" height="100%" />
        </div>
        <h1 class="header">研究生周报系统（教师登录）</h1>


        <div class="ms-login">
            <el-form
                ref="ruleForm"
                class="demo-ruleForm"
                :model="ruleForm"
                :rules="rules">
                <el-form-item prop="study_number">
                    <el-input v-model="ruleForm.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        type="password"
                        v-model="ruleForm.password"
                        placeholder="密码"
                        @keyup.enter.native="submitForm('ruleForm')"
                    ></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm">登录</el-button>
                </div>
                <p style="font-size:12px;line-height:30px;color:#999;">Tips : 用户名和密码要写数据库里的。</p>
            </el-form>
        </div>
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
                ruleForm: {
                    name: '',
                    password: ''
                },
                rules: {
                    name: [
                    { required: true,
                    message: '请输入姓名',
                    trigger: 'blur'
                    }
                    ],
                    password: [
                        { required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                        }
                    ]
                }
            }
        },

        methods: {

        submitForm () {
            let params = new URLSearchParams()
            params.append('name', this.ruleForm.name)
            params.append('password', this.ruleForm.password)
            HttpManager.getLoginStatus(params)
                .then(res => {
                    if (res.code === 1) {
                        //在vuex中增添登录人的信息
                        this.$store.commit("setName", this.ruleForm.name);
                        this.$store.commit("setPassword", this.ruleForm.password);
                        console.log(res)
                        this.notify("登录成功","success")
                        this.$router.push('noticelist')

                    } else {
                        console.log(res)
                        this.notify("账号或密码错误","error")
                    }
                })
                .catch(err => {
                    console.log(err)
                })
        },
    },
    }
</script>

<style scoped>

.bg-img{
    width:100%;
    height:100%;
    position:fixed;
    z-index:-2;
}

.header{
    position: absolute;
    top:16%;
    left: 0;
    right: 0;
    color: 	white;
    text-align: center;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  height: 160px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
