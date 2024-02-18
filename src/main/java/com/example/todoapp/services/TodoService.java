package com.example.todoapp.services;

import com.example.todoapp.models.Todo;

import java.util.List;

public interface TodoService {
  List<Todo> getTodos();

  void addNewTodo(String title);

  void deleteTodo(Long id);

  Todo getTodoById(Long id);

  void editAndSaveTodo(Long id, String title, boolean urgent, boolean done);
}
