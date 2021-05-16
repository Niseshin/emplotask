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
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks(
            @DefaultValue("100") @QueryParam("lim") int limit,
            @DefaultValue("0") @QueryParam("off") int offset) {
        return taskService.getTasks(limit, offset);
    }

    @GET
    @Path("listex")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasksExtended(
            @DefaultValue("100") @QueryParam("lim") int limit,
            @DefaultValue("0") @QueryParam("off") int offset) {
        return taskService.getTasksExtended(limit, offset);
    }

    @GET
    @Path("taskpriority")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskPriority() {
        return taskService.getTaskPriority();
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
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTask(Task task) {
        return taskService.saveTask(task);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTask(Task task) {
        return taskService.deleteTask(task);
    }
}
