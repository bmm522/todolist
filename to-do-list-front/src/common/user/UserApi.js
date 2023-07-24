import globalAxios from "src/common/GlobalAxios";


const checkDuplicateUserIdApi = async (userId) => {

 const result = await globalAxios.get("/user/check-duplicate", {
    params: {
      username: userId
    }
  })
  return result.data;
}

const registerApi = async (userId, password) => {
  const result = await globalAxios.post("/user/register", JSON.stringify({
    username: userId,
    password:password
  }), {
    headers: {
      "Content-type" : "application/json",
    }
  })
  return result.data;
}

export default {
  checkDuplicateUserIdApi,
  registerApi
};
