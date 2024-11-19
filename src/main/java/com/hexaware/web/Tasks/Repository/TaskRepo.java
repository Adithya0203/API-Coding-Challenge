package com.hexaware.web.Tasks.Repository;

import com.hexaware.web.Tasks.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {
}