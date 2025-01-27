package solyu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Finish homework");
        taskList.addTask(task);
        assertEquals(1, taskList.size(), "Task list should contain Finish homework task.");
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Go jogging");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.size(), "Task list should be empty.");
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Read a book");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0), "Retrieved task should be the same as added.");
    }
}
