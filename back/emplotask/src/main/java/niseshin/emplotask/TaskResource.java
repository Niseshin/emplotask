package niseshin.emplotask;

import generated.tables.pojos.Task;
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

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasksThroughDao() {
        return taskService.getTasksThroughDao();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(Task task) {
        return taskService.addTask(task);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(Task task) {
        return taskService.updateTask(task);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTask(Task task) {
        return taskService.deleteTask(task);
    }
}
