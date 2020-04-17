export function login(form) {
  return axios.get(`/user/info`,form).then(res1=>{
    this.userInfo=res1.data.data;
  })
}
