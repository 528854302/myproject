import axios from 'axios'
import cookie from 'js-cookie'


const myRequest = axios.create({
  baseURL: 'http://localhost:8081/api/'
})

myRequest.interceptors.request.use(config => {
    var token = cookie.get('userToken');
    console.log(token);
    if (token){
      config.headers['token'] = token;
    }

  return config;
  },
  err=>{
    return Promise.reject(err);
  });


export default myRequest
