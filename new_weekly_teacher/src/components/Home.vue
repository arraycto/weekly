<template>
    <div>
        <Header></Header>
        <Aside></Aside>
        <div class="content-box">
            <div class="content">
                <router-view></router-view>
            </div>
        </div> 
    </div>
</template>

<script>
    import Header from "./Header.vue"
    import Aside from "./Aside.vue"
    export default {
        components: {
            Header,
            Aside
        }
    }
</script>

<style scoped>

</style>