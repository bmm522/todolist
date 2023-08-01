import globalAxios from "src/common/GlobalAxios";


const insertTodoApi = async (todoTitle, todoContent, todoAt) => {
  const result = await globalAxios.post("/todo", JSON.stringify({
    todoTitle: todoTitle,
    todoContent: todoContent,
    todoAt: todoAt,
  }))

  return result.data;
}


const getTodoListApi = async (page, isUpdate,  isGetBeforeDataStatus, searchCondition) => {

  const result = await globalAxios.get("/todo/list", {
    params: {
      page: page,
      isUpdate: isUpdate,
      isGetBeforeDataStatus : isGetBeforeDataStatus,
      todoTitle: searchCondition.todoTitle,
      todoAt:searchCondition.todoAt,
    }
  })

  return result.data;
}

const updateTodoApi = async (todoId, todoTitle, todoContent, todoAt, todoDone) => {
  todoTitle.trim();
  const result = await globalAxios.post("/todo/update", JSON.stringify({
    todoId: todoId,
    todoTitle: todoTitle,
    todoContent: todoContent,
    todoAt: todoAt,
    todoDone: todoDone
  }))


  return result.data;


}

const batchDeleteTodoListApi = async (batchDeleteTodoIdList) => {


  const result = await globalAxios.post("/todo/list/batch-delete", JSON.stringify({
    deletedTodoIdList : batchDeleteTodoIdList
  }))

  return result.data;
}

const batchUpdateTodoDoneApi = async (batchUpdateTodoDoneTodoIdList, isDone) => {

  const result = await globalAxios.post("/todo/list/done/batch-update/"+isDone, JSON.stringify({
    updatedTodoIdList : batchUpdateTodoDoneTodoIdList
  }))

  return result.data;
}


export default {
  insertTodoApi,
  getTodoListApi,
  updateTodoApi,
  batchDeleteTodoListApi,
  batchUpdateTodoDoneApi
};
