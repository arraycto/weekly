<template>
    <div class="header">
        <div class="header-icon">
            <i class="el-icon-edit"></i>
        </div>
        <el-popover
          placement="top-start"
          width="200"
          trigger="hover"
          :open-delay="1000"
          content="开发者：滕如坤">
      <div class="header-text" slot="reference">研究生周报系统(教师端)</div>
        </el-popover>

        <div class="header-right">
            <div class="header-user">
        <!-- 用户名下拉菜单 -->
        <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              {{getName}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="modifyPassword">修改密码</el-dropdown-item>
                <el-dropdown-item command="loginOut" style="color:red">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>


  </div>
</template>

<script>
export default {
    computed:{
          getName(){
              return this.$store.state.name
          }
    },
    methods: {
        handleCommand (command) {
          if (command === 'loginOut') {
              //Vuex存储的数据清空
              this.$store.commit("setId", '');
              this.$store.commit("setName", '');
              this.$store.commit("setPassword", '');
              this.$router.push('/')
          }
           if (command === 'modifyPassword') {

               this.$router.push('/modifyPassword')
          }
        },


  }
}
</script>
<style scoped>
.header {
  position: absolute;
  z-index: 100;
  box-sizing: border-box;
  width: 100%;
  height: 10%;
  font-size: 22px;
  color: #B0B3B2;
  background: #1A1B1C;
  box-shadow: 0px 0px 8px 2px rgba(0, 0, 0, 0.3);
}

.header-icon {
  float: left;
  padding: 0 21px;
  line-height: 70px;
}

 .header-text {
  float: left;
  width: 250px;
  font-family: 26px;
  line-height: 70px;
}

.header-right {
  float: right;
  padding-right: 50px;
}

.header-user {
  display: flex;
  height: 70px;
  align-items: center;
}

.el-dropdown-link {
  color: #B0B3B2;
  cursor: pointer;
}
</style>
