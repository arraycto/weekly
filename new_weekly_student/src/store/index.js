import { BASE_URL } from '../api/config'

import persistedState from 'vuex-persistedstate'
//Vuex中存储的数据是在内存中，页面刷新，重新加载vue实例，vuex中的数据就会被清除
// 所以需要对Vuex中的数据做持久化保存，使用persistedState做这个功能
const store = {
    state:{
        HOST: BASE_URL,
        id:'',
        name:'',
        password:''
    },
    getters:{
        id: state=> state.id,
        name:state => state.name,
        password: state => state.password
    },
    mutations:{
        setUrl: (state, url) => { state.url = url },
        setId: (state, id) => { state.id = id },
        setName: (state, name) => { state.name = name },
        setPassword:(state,password) => { state.password = password }
    },
    plugins:[persistedState({
        storage: window.sessionStorage
    })]
}

export default store
