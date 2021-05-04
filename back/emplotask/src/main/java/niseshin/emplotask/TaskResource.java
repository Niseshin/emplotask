package niseshin.emplotask;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tasks")
public class TaskResource {

    private final TaskService taskService = new TaskService();

    @GET
    public Response getTasks() {
        return taskService.getTasks();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTask(Task task) {
        taskService.addTask(task);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask(Task task) {
        taskService.updateTask(task);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteTask(Task task) {
        taskService.deleteTask(task);
    }
}
