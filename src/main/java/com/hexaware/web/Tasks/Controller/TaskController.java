package com.hexaware.web.Tasks.Controller;

import com.hexaware.web.Tasks.Entity.Task;
import com.hexaware.web.Tasks.Exception.NotFoundException;
import com.hexaware.web.Tasks.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Save a new task
    @PostMapping("/add")
    public ResponseEntity<Task> saveNewTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // Get all tasks
    @GetMapping("/getall")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get a task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id) throws NotFoundException {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
//            return new ResponseEntity<>("No such task Found", HttpStatus.NOT_FOUND);
        	throw new NotFoundException("Task not found");
        }
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task updatedTask) throws NotFoundException {
        Optional<Task> existingTask = taskService.getTaskById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setPriority(updatedTask.getPriority());
            task.setStatus(updatedTask.getStatus());
            Task updatedObj = taskService.saveTask(task);
            return new ResponseEntity<>(updatedObj, HttpStatus.OK);
        } else {
        	throw new NotFoundException("Task not found");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
}
