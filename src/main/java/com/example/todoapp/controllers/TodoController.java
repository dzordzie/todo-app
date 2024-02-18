package com.example.todoapp.controllers;

import com.example.todoapp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
  private final TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }


  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("todos", todoService.getTodos());
    return "index";
  }

  @PostMapping("/new-todo")
  public String newTodo(@RequestParam String title) {
    todoService.addNewTodo(title);
    return "redirect:/";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    todoService.deleteTodo(id);
    return "redirect:/";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable Long id) {
    model.addAttribute("todoEdit", todoService.getTodoById(id));
    return "edit";
  }

  @PostMapping("/{id}/edit")
  public String saveEdit(@PathVariable Long id, @RequestParam(required = false) String title, @RequestParam(required = false) boolean urgent, @RequestParam(required = false) boolean done) {
    todoService.editAndSaveTodo(id, title, urgent, done);
    return "redirect:/";
  }
}
