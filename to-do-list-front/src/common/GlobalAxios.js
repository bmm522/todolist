import axios from "axios";
import {useQuasar} from "quasar";
import {expiredTokenHandler} from "src/common/ExpiredTokenHandler";



const createInstance = () => {
  const instance = axios.create({baseURL: process.env.API_URI});
  return setInterceptor(instance);
}


const setInterceptor = (instance) => {
  instance.interceptors.request.use(function (config) {
    config.headers.AccessToken = localStorage.getItem("AccessToken");
    config.headers["Content-Type"] = "application/json";
    return config;
  })

  instance.interceptors.response.use(
    async (response) => {
      if (response.data.code === 419) {
        return await expiredTokenHandler(response);
      }
      return response;
    },
  )
  return instance;
}

const globalAxios = createInstance();



export default globalAxios;
