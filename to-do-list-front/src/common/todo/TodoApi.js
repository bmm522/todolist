import globalAxios from "src/common/GlobalAxios";


const insertTodoApi = async (todoTitle, todoContent, todoAt) => {
  const result = await globalAxios.post("/todo", JSON.stringify({
    todoTitle : todoTitle,
    todoContent : todoContent,
    todoAt : todoAt,
  }))

  return result.data;
}


const getTodoListApi = async (page) => {
  const result = await globalAxios.get("/todo",  {
    params : {
      page : page
    }
  })

  return result.data;
}

export default {
  insertTodoApi,
  getTodoListApi
};
