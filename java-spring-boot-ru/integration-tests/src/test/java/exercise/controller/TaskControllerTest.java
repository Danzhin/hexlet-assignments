package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    // BEGIN
    @Test
    public void testShow() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().sentence(3);
        var task = createTask(title, description);
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                json -> json.node("title").isEqualTo(title),
                json -> json.node("description").isEqualTo(description)
        );
    }

    @Test
    public void testCreate() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().sentence(3);
        var taskData = createTaskData(title, description);

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskData));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                json -> json.node("title").isEqualTo(title),
                json -> json.node("description").isEqualTo(description)
        );
    }

    @Test
    public void testUpdate() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().sentence(3);
        var task = createTask(title, description);
        taskRepository.save(task);

        var newTitle = faker.lorem().word();
        var newDescription = faker.lorem().sentence(3);
        var taskData = createTaskData(newTitle, newDescription);

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskData));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                json -> json.node("title").isEqualTo(newTitle),
                json -> json.node("description").isEqualTo(newDescription)
        );
    }

    @Test
    public void testDelete() throws Exception {
        var title = faker.lorem().word();
        var description = faker.lorem().sentence(3);
        var task = createTask(title, description);
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END

    public Task createTask(String title, String description) {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> description)
                .create();
    }

    public HashMap<String, String> createTaskData(String title, String description) {
        var taskData = new HashMap<String, String>();
        taskData.put("title", title);
        taskData.put("description", description);
        return taskData;
    }
}
