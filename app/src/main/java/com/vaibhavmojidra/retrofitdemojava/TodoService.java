package com.vaibhavmojidra.retrofitdemojava;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoService {
    @GET("/todos")
    Call<Todos> getAllTodos();

    @GET("/todos")
    Call<Todos> getALlTodosWithUserId(@Query("userId") int userId);

    @GET("/todos/{id}")
    Call<TodoItem> getTodoItemWithId(@Path("id")int id);

    @POST("/todos")
    Call<TodoItem> postTodoItem(@Body TodoItem todoItem);
}
