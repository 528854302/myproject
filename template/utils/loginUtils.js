import UserUtils from '~/utils/user'

export default {
  checkLogin(){
    if (!UserUtils.getUserInfo().id){
      this.$route.push('/login');
    }
  }
}
