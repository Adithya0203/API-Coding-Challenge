package com.hexaware.web.Tasks.Service;

import com.hexaware.web.Tasks.Entity.Task;
import com.hexaware.web.Tasks.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService  {

    @Autowired
    private TaskRepo repo;

    public Task saveTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return repo.findById(id);
    }
    
    public void deleteTaskById(int id) {
        repo.deleteById(id);
    }
}
