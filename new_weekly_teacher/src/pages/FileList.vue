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
                        prop="old_name"
                        label="文件名"
                        width="280">
                </el-table-column>
                <el-table-column
                        prop="size"
                        label="文件大小（单位：MB）"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="owner_name"
                        label="上传人"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="post_time"
                        label="上传时间"
                        width="280">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <a class="download" :href="href(scope.row.id)">下载</a>
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
                        :total="file.length">
                </el-pagination>
            </div>
        </el-card>
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
                file: [],

            }
        },
        computed:{
            //计算当前表格中的数据
            tableData () {
                return this.file.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
            },
        },

        mounted() {
            this.getFile()
        },

        methods: {


            href(id){
                return this.$store.state.HOST +'/download?id=' + id
            },
            //获取文件列表
            getFile(){
                HttpManager.getFile().then(res =>{
                    for(let i in res) {
                        this.file.push(res[i])
                    }
                })
            },

        },
    }

</script>

<style scoped>
    .pagination {
        display: flex;
        justify-content: center;
    }
    .download{
        display: block;
        width: 70px;
        height: 30px;
        color: white;
        line-height: 30px;
        font-size: 8px;
        text-decoration: none;
        text-align: center;
        background-color: 	#1E90FF;
        border-radius:5px;
    }
</style>
