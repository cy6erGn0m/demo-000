package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class TestController {
    @Autowired
    private TaskRepository repository;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model) {
        model.addAttribute("user", id);
        return "profile";
    }

    @GetMapping("/user/{id}/tasks")
    public String tasks(@PathVariable int id, Model model) {
        ArrayList<Task> tasks = new ArrayList<>();
        repository.findAll().forEach(tasks::add);

        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task/create")
    public String createTasks(Model model) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            tasks.add(new Task("Task " + i));
        }

        repository.saveAll(tasks);

        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
