<template>
    <div>
        <el-empty
                description="暂无通知"
                v-show="notices_num===0"></el-empty>
        <el-card
                class="box-card"
                v-for="item in notices"
                :key="item.id"
                style="margin-bottom: 10px;width: 60%;margin-left: 20%">
            <div slot="header" class="clearfix">
                <span class="title">{{item.title}}</span>
            </div>
            <div class="text item">
                {{item.content}}
            </div>
        </el-card>
    </div>
</template>

<script>
    import { HttpManager } from '../api/index'
    export default {
        data() {
            return {
                notices:{},
                notices_num:0
            }
        },
        mounted() {
            this.getNotice();
        },
        methods:{
            getNotice(){
                HttpManager.getNotice().then(res =>{
                    // console.log(res)
                    this.notices=res;
                    this.notices_num=res.length
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
