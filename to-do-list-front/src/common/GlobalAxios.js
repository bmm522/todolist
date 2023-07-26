import axios from "axios";
import {useQuasar} from "quasar";


const createInstance = () => {
  const instance = axios.create({baseURL: process.env.API_URI});
  return setInterceptor(instance);
}

const setInterceptor = (instance) => {
  instance.interceptors.request.use(function (config ) {
    config.headers.AccessToken =  sessionStorage.getItem("AccessToken");
    config.headers.RefreshToken =  sessionStorage.getItem("RefreshToken");
    config.headers["Content-Type"] = "application/json";
    return config;
  })



  instance.interceptors.response.use(

    (res) => {
      return res;
    },

    (error) => {
      return Promise.reject();
    },
  )

  return instance;
}



const globalAxios= createInstance();


export default globalAxios;
