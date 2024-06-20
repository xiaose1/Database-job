import axios from 'axios'
import { ElMessage } from 'element-plus'
import global from '../global'
import router from '../router'

const instance = axios.create({
    baseURL: global.baseUrl,
    timeout: 5000 // 请求超时时间
})

//添加请求拦截器
instance.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
	if (response.data.code === 999) {
		//处理session过期重复弹窗问题
		let msgFlag = sessionStorage.getItem('msgFlag')
		if (!msgFlag) {
			ElMessage.error(response.data.msg)
			sessionStorage.setItem('msgFlag', 1)
			setTimeout(() => {
				sessionStorage.removeItem('msgFlag')
			}, 1000)
		}
		router.push('login')
		return false
	}
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
	if (response.data.code !== 0) {
		console.log('接口请求失败')
		console.log(response)
		ElMessage.error(response.data.msg)
		return false
	}
	if (response.data.data) {
		return response.data.data
	}
    return true;
  }, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  });

export default instance