<template>
    <div class="aside">
        <el-menu
          :default-active="onRoutes"
          class="el-menu-vertical-demo"
          background-color="#1A1B1C"
          text-color="#fff"
          active-text-color="#ffd04b">

          <el-menu-item
          v-for="item in items"
          :index="item.index"
          :key="item.index"
          @click="jump(item.index)">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
    </el-menu>
    </div>
</template>

<script>
    export default {
        data() {
          return {
            items:[
              {
                index:"noticelist",
                icon:"el-icon-reading",
                title:"通知管理"
              },
              {
                index:"twoweekweekly",
                icon:"el-icon-document-copy",
                title:"近两周周报"
              },
                {
                    index:"filelist",
                    icon:"el-icon-document-copy",
                    title:"文件管理"
                },
                {
                    index:"studentlist",
                    icon:"el-icon-document-copy",
                    title:"学生管理"
                },
              {
                index:"bignews",
                icon:"el-icon-bell",
                title:"重要信息汇总"
              },
            ]
          }
        },
        methods: {
          jump(address){
            this.$router.push(address)
          }
        },
        computed: {
          onRoutes () {
            return this.$route.path.replace('/', '')
          }
        },
    }
</script>

<style scoped>
.aside{
    display: block;
    position: absolute;
    background-color: #1A1B1C;
    left: 0;
    top: 10%;
    bottom: 0;
}
</style>
