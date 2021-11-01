export const mixin = {
    methods: {
        // 提示信息
        notify(message,type){
            this.$notify({
                title: message,
                type:type,
                duration:1000
            })
        },
        // 获取当前页
        handleCurrentChange (val) {
            this.currentPage = val
        },
    }
}
