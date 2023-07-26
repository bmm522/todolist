import globalAxios from "src/common/GlobalAxios";
import async from "async";

const insertTodoApi = async (todoTitle, todoContent, todoAt) => {
  const result = await globalAxios.post("/todo", JSON.stringify({
    todoTitle : todoTitle,
    todoContent : todoContent,
    todoAt : todoAt,
  }))
}

