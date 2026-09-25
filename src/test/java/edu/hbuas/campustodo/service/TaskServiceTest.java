package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();

        Task task = service.addTask("完成需求评审");

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

    // ==== 优先级筛选相关测试 ====

    @Test
    void addTask_defaultPriority_shouldBeMedium() {
        TaskService service = new TaskService();
        Task task = service.addTask("默认优先级任务");
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void filterByPriority_highPriority_onlyHighTasks() {
        TaskService service = new TaskService();
        service.addTask("高优任务", Priority.HIGH);
        service.addTask("中优任务", Priority.MEDIUM);
        service.addTask("低优任务", Priority.LOW);
        List<Task> result = service.filterByPriority(Priority.HIGH);
        assertEquals(1, result.size());
        assertEquals("高优任务", result.get(0).getTitle());
    }

    @Test
    void filterByPriority_noMatch_shouldReturnEmptyList() {
        TaskService service = new TaskService();
        service.addTask("任务1", Priority.HIGH);
        List<Task> result = service.filterByPriority(Priority.LOW);
        assertTrue(result.isEmpty());
    }

    @Test
    void filterByPriority_emptyService_shouldReturnEmptyList() {
        TaskService service = new TaskService();
        List<Task> result = service.filterByPriority(Priority.HIGH);
        assertTrue(result.isEmpty());
    }

    @Test
    void filterByPriority_nullPriority_shouldThrowException() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class,
            () -> service.filterByPriority(null));
    }
}
