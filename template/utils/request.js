import axios from 'axios'
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost/api/', // api的base_url
  timeout: 15000 // 请求超时时间
})
export default service
