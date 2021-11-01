<template>
    <div>
        <el-card class="box-card">
            <el-collapse  @change="handleChange">
                <el-collapse-item name="1">
                    <template slot="title">
                        <h2>北京林业大学信息</h2>
                    </template>
                    <div v-for="item in  bfuMessage">{{item}}</div>
                </el-collapse-item>
                <el-collapse-item name="2">
                    <template slot="title">
                        <h2>研究生周报系统说明</h2>
                    </template>
                    <div v-for="item in weeklySystemNotice">{{item}}</div>
                </el-collapse-item>
                <el-collapse-item name="3">
                    <template slot="title">
                        <h2>软件著作权申请说明</h2>
                    </template>
                    <div v-for="item in softWareCopyRight">{{item}}</div>
                </el-collapse-item>
                <el-collapse-item name="4">
                    <template slot="title">
                        <h2>毕设理念</h2>
                    </template>
                    <div v-for="item in graduationProject">{{item}}</div>
                </el-collapse-item>
            </el-collapse>
        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                bfuMessage:[
                    '单位名称：北京林业大学',
                    '法定代表人：安黎哲',
                    '统一社会信用代码/税号：12100000400006719W',
                    '地址：北京市海淀区清华东路35号',
                    '电话：010-62338301',
                    '开户行及账号：工商银行北京市东升路支行：0200006209026400903'
                ],
                weeklySystemNotice:[
                    '（一）概述：',
                    '研究生周报是研究生和导师之间沟通的重要手段，需要真正重视，严肃对待。学习、科研、开发、日常事务进展、以及偶尔的信息收集都通过周报实现。',
                    '（二）时间和时段：',
                    '从研究生入学第一周开始到毕业，周报都不间断。每周周一至周天作为一个周报周期，从周二到下周周一是周报填报时间，可以持续记录，多次提交，但每次提交都会覆盖前一次的提交。在可提交的时段内，临时信息对导师不可见。导师从下周周二至下下周的周一对上周周报进行评阅和反馈。',
                    '（三）提交和查看周报：',
                    '提交周报的后，周报将被长时间保存，可以随时查看历史周报，直至毕业。',
                    '（四）周报的重要性：',
                    '周报与技术学习、科学研究、毕业设计、研究生津贴发放等都息息相关，每一位研究生要务必写好每周的周报。'
                ],
                softWareCopyRight:[
                    '2019-09-27 因系统调整，中国版权保护中心（软著申请）学校的登录密码已经更改，请需要申请软著的同学登录后，勿随意更改密码。',
                    '网址：http://apply.ccopyright.com.cn/user/loginsucc.do',
                    '用户：北京林业大学',
                    '密码：68cgeQ'
                ],
                graduationProject:[
                    '关于毕设，我强调一个理念：工科的论文不是写出来的，而是做出来的。',
                    '有的研究生平时不写程序，不做实验，到了最后半年狂写论文，还是过不了，觉得很冤：我明明尽了最大努力写论文，为什么外审不给过？',
                    '其实，这些同学的努力只是在最后半年，而且基本上是无用功。从一入学开始就要积极地写程序，用标准的方式写各种实验程序，处理各种数据，写文档记录结果，最后在论文里才有的可写，才敢写，评审时才不会被一眼看出没做系统而干些论文。'
                ]
            }
        },
        methods:{
            handleChange(val) {

            }
        }
    }
</script>

<style scoped>
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
