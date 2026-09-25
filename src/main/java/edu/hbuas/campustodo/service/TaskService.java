package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务服务。
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public Task addTask(String title) {
        return addTask(title, Priority.MEDIUM);
    }

    public Task addTask(String title, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        if (priority == null) {
            throw new IllegalArgumentException("优先级不能为空");
        }
        Task task = new Task(nextId++, title, priority);
        tasks.add(task);
        return task;
    }

    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }

    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("优先级不能为空");
        }
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }
}
