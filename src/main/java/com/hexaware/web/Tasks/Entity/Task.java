package com.hexaware.web.Tasks.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 characters")
    @Column(nullable = false, length = 50) 
    private String title;

    @NotNull(message = "Description cannot be null")
    @Size(min = 2, max = 100, message = "Description should be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String description;

    @NotNull(message = "Due date cannot be null")
    @Column(nullable = false)
    private LocalDate dueDate; 

    @NotNull(message = "Priority cannot be null")
    @Size(min = 2, max = 20, message = "Priority should be between 2 and 20 characters")
    @Column(nullable = false, length = 20)
    private String priority;

    @NotNull(message = "Status cannot be null")
    @Size(min = 2, max = 20, message = "Status should be between 2 and 20 characters")
    @Column(nullable = false, length = 20)
    private String status;

    public Task() {
    }

    public Task(int taskId, String title, String description, LocalDate dueDate, String priority, String status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
                + ", priority=" + priority + ", status=" + status + "]";
    }
}
