<template>
    <div>
        <div class="bg-img">
            <img src="../assets/img/bg3.jpg" width="100%" height="100%" />
        </div>
        <h1 class="header">研究生周报系统</h1>


        <div class="ms-login">
            <el-form
                ref="ruleForm"
                class="demo-ruleForm"
                :model="ruleForm"
                :rules="rules">
                <el-form-item prop="study_number">
                    <el-input v-model="ruleForm.study_number" placeholder="学号"></el-input>
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
          data: function () {
            return {
                ruleForm: {
                    study_number: '',
                    password: ''
                },
                rules: {
                    study_number: [
                    { required: true,
                    message: '请输入学号',
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
            params.append('study_number', this.ruleForm.study_number)
            params.append('password', this.ruleForm.password)
            HttpManager.getLoginStatus(params)
                .then(res => {
                    if (res.code === 1) {
                        //在vuex中增添登录人的信息
                        this.$store.commit("setId", res.id);
                        this.$store.commit("setName", res.name);
                        this.$store.commit("setPassword", this.ruleForm.password);
                        this.notify("登录成功","success")
                        this.$router.push('notice')

                    } else {
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
