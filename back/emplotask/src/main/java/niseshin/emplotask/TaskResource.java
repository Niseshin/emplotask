package niseshin.emplotask;

import generated.tables.records.TaskRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tasks")
@ApplicationScoped
public class TaskResource {

    @Inject
    private TaskService taskService;

    @GET
    public Response getTasks() {
        return taskService.getTasks();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(TaskRecord task) {
        return taskService.addTask(task);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(TaskRecord task) {
        return taskService.updateTask(task);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTask(TaskRecord task) {
        return taskService.deleteTask(task);
    }
}
