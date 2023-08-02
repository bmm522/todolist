import globalAxios from "src/common/GlobalAxios";


const checkDuplicateUsernameApi = async (username) => {
  username.trim();

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

  return  result.data;
}

const registerApi = async (userId, password, nickname) => {
  userId.trim();
  password.trim();
  nickname.trim();
  const result = await globalAxios.post("/user/register", JSON.stringify({
    username: userId,
    password: password,
    nickname: nickname,
  }))
  return result.data;
}

const loginApi = async (userId, password) => {
  userId.trim();
  password.trim();
  const result = await globalAxios.post("/login", JSON.stringify({
    username: userId,
    password: password,
  }))

  return result.data;
}


const editUserNickname = async (nickname) => {
  nickname.trim();
  const result = await globalAxios.post("/user/nickname/update", JSON.stringify({
    nickname : nickname
  }))
  return result.data;
}





export default {
  checkDuplicateUsernameApi,
  registerApi,
  loginApi,
  getNickname,
  editUserNickname
};
