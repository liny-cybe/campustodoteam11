package edu.hbuas.campustodo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();

        var task = service.addTask("完成需求评审");

        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
    }

    @Test
    void shouldRejectBlankTitle() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("   "));
    }
    // ==== 任务完成相关测试 ====

    @Test
    void completeTask_existingTask_shouldMarkCompleted() {
        TaskService service = new TaskService();
        Task task = service.addTask("待完成任务");
        assertFalse(task.isCompleted());
        service.completeTask(task.getId());
        assertTrue(task.isCompleted());
    }

    @Test
    void completeTask_notExist_shouldThrowException() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class,
            () -> service.completeTask(999));
    }

    @Test
    void completeTask_alreadyCompleted_shouldThrowException() {
        TaskService service = new TaskService();
        Task task = service.addTask("已完成任务");
        service.completeTask(task.getId());
        assertThrows(IllegalStateException.class,
            () -> service.completeTask(task.getId()));
    }
}
