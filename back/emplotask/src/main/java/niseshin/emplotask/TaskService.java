package niseshin.emplotask;

import static generated.Tables.*;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

public class TaskService {

    private static final DSLContext context = ConnectionBean.getDSLContext();

    public Response getTasks() {
        Result result = context.select(TASK.ID, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID, EMPLOYEE.NAME)
                .from(TASK.join(EMPLOYEE).on(EMPLOYEE.ID.eq(TASK.PERFORMER_ID)))
                .fetch();
        Response response = Response
                .status(Response.Status.OK)
                .entity(result.formatJSON())
                .build();

        return response;
    }

    public void addTask(Task task) {
        context.insertInto(TASK, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID)
                .values(task.priority, task.description, task.performer)
                .returning()
                .fetch();
    }

    public void updateTask(Task task) {
        context.update(TASK)
                .set(TASK.PRIORITY, task.priority)
                .set(TASK.DESCRIPTION, task.description)
                .set(TASK.PERFORMER_ID, task.performer)
                .where(TASK.ID.eq(task.id))
                .execute();
    }

    public void deleteTask(Task task) {
        context.delete(TASK)
                .where(TASK.ID.eq(task.id))
                .execute();
    }
}
