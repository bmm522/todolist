import axios from "axios";
import {useQuasar} from "quasar";




const globalAxios = axios.create({baseURL: process.env.API_URI});



globalAxios.interceptors.response.use(

  (res) => {
   return res;
  },

  (error) => {
    return Promise.reject();
  },
)

export default globalAxios;
