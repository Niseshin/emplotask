package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.records.TaskRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

@ApplicationScoped
public class TaskService {

    @Inject
    private DSLContext context;

    public Response getTasks() {
        Result result = context
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
        if (task.getId() == null
                && task.getPriority() != null
                && checkPriority(task)
                && task.getDescription() != null
                && !task.getDescription().isEmpty()
                && task.getPerformerId() != null
                && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(task.getPerformerId())).fetchOne() != null) {
//            context.insertInto(TASK, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID)
//                    .values(task.getPriority(), task.getDescription(), task.getPerformerId())
//                    .returning()
//                    .fetch();

            task.reset("id");
            task.attach(context.configuration());
            task.insert();

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
                && context.select().from(TASK).where(TASK.ID.eq(task.getId())).fetchOne() != null
                && task.getPriority() != null
                && checkPriority(task)
                && task.getDescription() != null
                && !task.getDescription().isEmpty()
                && task.getPerformerId() != null
                && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(task.getPerformerId())).fetchOne() != null) {
//            context.update(TASK)
//                    .set(TASK.PRIORITY, task.getPriority())
//                    .set(TASK.DESCRIPTION, task.getDescription())
//                    .set(TASK.PERFORMER_ID, task.getPerformerId())
//                    .where(TASK.ID.eq(task.getId()))
//                    .execute();

            task.attach(context.configuration());
            task.update();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteTask(TaskRecord task) {
        if (task.getId() != null
                && context.select().from(TASK).where(TASK.ID.eq(task.getId())).fetchOne() != null) {
//            context.delete(TASK)
//                    .where(TASK.ID.eq(task.getId()))
//                    .execute();

            task.attach(context.configuration());
            task.delete();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    // слижком сложный способ проверки приоритета
    private boolean checkPriority(TaskRecord task) {
        Result result = context
                .select(TASK.ID, TASK.PRIORITY)
                .from(TASK)
                .orderBy(TASK.PRIORITY.asc())
                .fetch();

        int minPriority = (int) result.getValue(0, "priority");
        if (task.getId() == null
                || (result.size() > 1
                && ((int) result.getValue(0, "id") != task.getId()
                || (int) result.getValue(1, "priority") == minPriority))) {
            System.out.println("--");
            --minPriority;
        }

        int maxPriority = (int) result.getValue(result.size() - 1, "priority");
        if (task.getId() == null
                || (result.size() > 1
                && ((int) result.getValue(result.size() - 1, "id") != task.getId()
                || (int) result.getValue(result.size() - 2, "priority") == maxPriority))) {
            System.out.println("++");
            ++maxPriority;
        }

        return task.getPriority() >= minPriority && task.getPriority() <= maxPriority;
    }
}
