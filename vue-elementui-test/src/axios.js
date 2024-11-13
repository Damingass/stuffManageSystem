import axios from "axios";
import router from "./router";
import ElementUI from "element-ui";

axios.defaults.baseURL = "http://localhost:8888";

//不进行前置拦截
axios.interceptors.request.use(config=>{
    return config;
})

axios.interceptors.response.use(response=>{
    let res=response.data;
    if(res.code==200) return response;
    else if(res.code==401){
        ElementUI.Message.error(res.msg,{duration:3000});
        router.push('/login');
        return Promise.reject(res.msg);
    } else{
        ElementUI.Message.error(res.msg,{duration:3000});
        return Promise.reject(res.msg);
    }
})