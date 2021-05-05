package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.records.TaskRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.Result;

@ApplicationScoped
public class TaskService {

    @Inject
    private ConnectionBean conBean;

    public Response getTasks() {
        Result result = conBean.getDSLContext()
                .select(TASK.ID, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID, EMPLOYEE.NAME)
                .from(TASK.join(EMPLOYEE).on(EMPLOYEE.ID.eq(TASK.PERFORMER_ID)))
                .fetch();
        Response response = Response
                .status(Response.Status.OK)
                .entity(result.formatJSON())
                .build();

        return response;
    }

    public Response addTask(TaskRecord task) {
        if (task.getPriority() != null
                && task.getDescription() != null
                && task.getPerformerId() != null) {
            conBean.getDSLContext()
                    .insertInto(TASK, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID)
                    .values(task.getPriority(), task.getDescription(), task.getPerformerId())
                    .returning()
                    .fetch();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response updateTask(TaskRecord task) {
        if (task.getId() != null
                && task.getPriority() != null
                && task.getDescription() != null
                && task.getPerformerId() != null) {
            conBean.getDSLContext()
                    .update(TASK)
                    .set(TASK.PRIORITY, task.getPriority())
                    .set(TASK.DESCRIPTION, task.getDescription())
                    .set(TASK.PERFORMER_ID, task.getPerformerId())
                    .where(TASK.ID.eq(task.getId()))
                    .execute();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteTask(TaskRecord task) {
        if (task.getId() != null) {
            conBean.getDSLContext()
                    .delete(TASK)
                    .where(TASK.ID.eq(task.getId()))
                    .execute();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
