import {defineStore} from 'pinia'
import {ref} from "vue";

export const useUserInfoDataStore = defineStore('userInfoDataId', () => {
  const userInfoDataId = ref(0);

  const data = ref({
    "userId": "",
    "username": "",
    "nickname": "",
  });

  const init = () => {
      data.value.userId = "",
      data.value.username = "",
      data.value.nickname = ""
  }

  return {data, init}
})
