import Cookies from 'js-cookie'

const TokenKey = 'userToken'
const IdKey = 'userId';
const NameKey = 'userNickname'
const AvatarKey = 'userAvatar'


export default {
  removeUser() {
    Cookies.remove(TokenKey)
    Cookies.remove(IdKey)
    Cookies.remove(NameKey)
    Cookies.remove(AvatarKey)
  },
  getUserInfo() {
    return {
      token: Cookies.get(TokenKey),
      id: Cookies.get(IdKey),
      nickname: Cookies.get(NameKey),
      avatar: Cookies.get(AvatarKey)
    }
  },
  setToken(token) {
    Cookies.set(TokenKey, token)
  },
  setUserInfo(id,nickname,avatar) {
    Cookies.set(IdKey, id)
    Cookies.set(NameKey, nickname)
    Cookies.set(AvatarKey, avatar)
  }

}



// export function setTokon(token) {
//   Cookies.set(TokenKey, token)
// }
//
// export function setUserInfo(id,nickname,avatar) {
//   Cookies.set(IdKey, id)
//   Cookies.set(NameKey, nickname)
//   Cookies.set(AvatarKey, avatar)
// }
//
// export function getUser() {
//   return {
//     token:Cookies.get(TokenKey),
//     id:Cookies.get(IdKey),
//     name:Cookies.get(NameKey),
//     avatar:Cookies.get(AvatarKey)
//   }
// }
//
// export function removeUser() {
//   Cookies.remove(TokenKey)
//   Cookies.remove(IdKey)
//   Cookies.remove(NameKey)
//   Cookies.remove(AvatarKey)
// }


