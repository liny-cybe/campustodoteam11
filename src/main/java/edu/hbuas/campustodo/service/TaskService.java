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

    /**
     * 添加一个任务，默认优先级为 MEDIUM。
     *
     * @param title 任务标题，不能为空或空白
     * @return 新创建的任务对象
     * @throws IllegalArgumentException 如果标题为空或空白
     */
    public Task addTask(String title) {
        return addTask(title, Priority.MEDIUM);
    }

    /**
     * 添加一个指定优先级的任务。
     *
     * @param title    任务标题，不能为空或空白
     * @param priority 任务优先级，不能为 null
     * @return 新创建的任务对象
     * @throws IllegalArgumentException 如果标题为空或 priority 为 null
     */
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

    /**
     * 列出所有任务。
     *
     * @return 所有任务的列表副本
     */
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * 按优先级筛选任务。
     *
     * @param priority 任务优先级，不能为 null
     * @return 符合指定优先级的任务列表，无匹配时返回空列表
     * @throws IllegalArgumentException 如果 priority 为 null
     */
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
