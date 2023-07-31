import globalAxios from "src/common/GlobalAxios";


const insertTodoApi = async (todoTitle, todoContent, todoAt) => {
  const result = await globalAxios.post("/todo", JSON.stringify({
    todoTitle : todoTitle,
    todoContent : todoContent,
    todoAt : todoAt,
  }))

  return result.data;
}


const getTodoListApi = async (page, isUpdate) => {
  const result = await globalAxios.get("/todo/list",  {
    params : {
      page : page,
      isUpdate : isUpdate,
    }
  })

  return result.data;
}

const updateTodoApi = async (todoId, todoTitle, todoContent, todoAt, todoDone) => {
  console.log(todoDone);
}


export default {
  insertTodoApi,
  getTodoListApi,
  updateTodoApi
};
