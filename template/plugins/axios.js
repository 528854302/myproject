import axios from 'axios'

// 设置baseURL
axios.defaults.baseURL = 'http://localhost/api/'

// 创建axios对象，暴露
export default axios.create()
