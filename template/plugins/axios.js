import axios from 'axios'


const myRequest = axios.create({
  baseURL: 'http://localhost/api/'
})

myRequest.interceptors.request.use(
  config => {
    let token = sessionStorage.getItem('user_info1');
    alert(token);
  if (token){
    alert(token);
    config.headers['token'] = sessionStorage.getItem('user_info1');
  }
  return config;
  },
  err=>{
    return Promise.reject(err);
  })


export default myRequest
