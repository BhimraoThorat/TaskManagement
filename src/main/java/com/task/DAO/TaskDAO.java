package com.task.DAO;

import java.util.List;

import com.task.Entity.Task;

public interface TaskDAO {
    void addTask(Task task);
    Task getTaskById(int taskId);
    void updateTask(Task task);
    void deleteTask(int taskId);
	List<Task> getAllTasks();
}
