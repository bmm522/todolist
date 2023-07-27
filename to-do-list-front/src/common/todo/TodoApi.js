import globalAxios from "src/common/GlobalAxios";


const insertTodoApi = async (todoTitle, todoContent, todoAt) => {
  const result = await globalAxios.post("/todo", JSON.stringify({
    todoTitle : todoTitle,
    todoContent : todoContent,
    todoAt : todoAt,
  }))

  return result.data;
}

export default {
  insertTodoApi
};
