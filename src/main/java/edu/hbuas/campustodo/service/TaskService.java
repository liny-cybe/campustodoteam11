package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务服务。
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    /**
     * 添加一个任务。
     *
     * @param title 任务标题，不能为空或空白
     * @return 新创建的任务对象
     * @throws IllegalArgumentException 如果标题为空或空白
     */
    public Task addTask(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    /**
     * 列出所有任务。
     *
     * @return 所有任务的列表副本
     */
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * 完成指定编号的任务。
     *
     * @param id 任务编号
     * @throws IllegalArgumentException 如果任务不存在
     * @throws IllegalStateException 如果任务已经完成
     */
    public void completeTask(long id) {
        Task task = findById(id);
        if (task == null) {
            throw new IllegalArgumentException("任务不存在: " + id);
        }
        if (task.isCompleted()) {
            throw new IllegalStateException("任务已完成，不能重复完成: " + id);
        }
        task.complete();
    }

    private Task findById(long id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
