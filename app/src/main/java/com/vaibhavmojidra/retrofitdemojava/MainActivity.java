package com.vaibhavmojidra.retrofitdemojava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.vaibhavmojidra.retrofitdemojava.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TodoService todoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        todoService=RetrofitInstance.getRetrofitInstance().create(TodoService.class);
        getAllTodos();
        getAllTodosWithUserId(2);
        getTodoWithId(4);
        postTodoItem(2,4,"Vai",false);
    }

    private void getAllTodos() {
        Call<Todos> call=todoService.getAllTodos();
        call.enqueue(new Callback<Todos>() {
            @Override
            public void onResponse(Call<Todos> call, Response<Todos> response) {
                assert response.body() != null;
                for(TodoItem todoItem:response.body()){
                    binding.textView.append("ID: "+todoItem.id+"\nUser ID: "+todoItem.userId+"\nTitle: "+todoItem.title+"\nCompleted: "+todoItem.completed+"\n\n\n");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Todos> call, Throwable t) {

            }
        });
    }

    private void getAllTodosWithUserId(int userId){
        Call<Todos> call=todoService.getALlTodosWithUserId(userId);
        call.enqueue(new Callback<Todos>() {
            @Override
            public void onResponse(Call<Todos> call, Response<Todos> response) {
                for(TodoItem todoItem:response.body()){
                    binding.textView.append("ID: "+todoItem.id+"\nUser ID: "+todoItem.userId+"\nTitle: "+todoItem.title+"\nCompleted: "+todoItem.completed+"\n\n\n");
                }
            }

            @Override
            public void onFailure(Call<Todos> call, Throwable t) {

            }
        });
    }

    private void getTodoWithId(int id){
        Call<TodoItem> call=todoService.getTodoItemWithId(id);
        call.enqueue(new Callback<TodoItem>() {
            @Override
            public void onResponse(Call<TodoItem> call, Response<TodoItem> response) {
                TodoItem todoItem=response.body();
                binding.textView.append("ID: "+todoItem.id+"\nUser ID: "+todoItem.userId+"\nTitle: "+todoItem.title+"\nCompleted: "+todoItem.completed+"\n\n\n");
            }

            @Override
            public void onFailure(Call<TodoItem> call, Throwable t) {

            }
        });
    }

    private void postTodoItem(int id,int userId,String title,boolean completed){
        Call<TodoItem> todoItem=todoService.postTodoItem(new TodoItem(userId,id,title,completed));
        todoItem.enqueue(new Callback<TodoItem>() {
            @Override
            public void onResponse(Call<TodoItem> call, Response<TodoItem> response) {
                TodoItem todoItem=response.body();
                binding.textView.append("ID: "+todoItem.id+"\nUser ID: "+todoItem.userId+"\nTitle: "+todoItem.title+"\nCompleted: "+todoItem.completed+"\n\n\n");
            }

            @Override
            public void onFailure(Call<TodoItem> call, Throwable t) {

            }
        });
    }
}