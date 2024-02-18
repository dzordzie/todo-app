package com.example.todoapp.services;

import com.example.todoapp.models.Todo;
import com.example.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
  private final TodoRepository todoRepository;

  @Autowired
  public TodoServiceImpl(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public List<Todo> getTodos() {
    return todoRepository.findAll();
  }

  @Override
  public void addNewTodo(String title) {
    Todo newTodo = new Todo();
    newTodo.setTitle(title);
    todoRepository.save(newTodo);
  }

  @Override
  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }

  @Override
  public Todo getTodoById(Long id) {
    Optional<Todo> foundTodo = todoRepository.findById(id);
    Todo t = new Todo();
    if (foundTodo.isPresent()) {
      t = foundTodo.get();
    }
    return t;
  }

  @Override
  public void editAndSaveTodo(Long id, String title, boolean urgent, boolean done) {
    Optional<Todo> foundTodo = todoRepository.findById(id);
    if (foundTodo.isPresent()) {
      Todo t = foundTodo.get();
      t.setTitle(title);
      t.setUrgent(urgent);
      t.setDone(done);
      todoRepository.save(t);
    }
  }
}
