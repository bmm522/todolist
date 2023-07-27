import globalAxios from "src/common/GlobalAxios";


const checkDuplicateUsernameApi = async (username) => {

 const result = await globalAxios.get("/user/check-duplicate", {
    params: {
      username: username
    }
  })
  return result.data;
}

const getNickname = async () => {
  const result = await globalAxios.get("/user/nickname"
  )

  console.log(result);
  return  result.data;
}

const registerApi = async (userId, password, nickname) => {
  const result = await globalAxios.post("/user/register", JSON.stringify({
    username: userId,
    password: password,
    nickname: nickname,
  }))
  return result.data;
}

const loginApi = async (userId, password) => {
  const result = await globalAxios.post("/login", JSON.stringify({
    username: userId,
    password: password,
  }))

  return result.data;
}

export default {
  checkDuplicateUsernameApi,
  registerApi,
  loginApi,
  getNickname
};
