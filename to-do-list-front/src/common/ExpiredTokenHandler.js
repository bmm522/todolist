import axios from "axios";


export const expiredTokenHandler = async (response) => {
  const result = await axios.post(process.env.API_URI + "/user/re-issue", {
    "refreshToken": localStorage.getItem("RefreshToken"),
  })
  localStorage.setItem("AccessToken", result.data.body.accessToken);
  localStorage.setItem("RefreshToken", result.data.body.refreshToken);
  response.config.headers.AccessToken = result.data.body.accessToken;
  return axios(response.config);
}


