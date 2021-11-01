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
                index:"notice",
                icon:"el-icon-reading",
                title:"通知公告"
              },
              {
                index:"weeklylist",
                icon:"el-icon-document-copy",
                title:"周报列表"
              },
              {
                index:"writeweekly",
                icon:"el-icon-edit",
                title:"撰写周报"
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
