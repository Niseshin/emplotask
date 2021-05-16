package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.daos.EmployeeDao;
import generated.tables.daos.TaskDao;
import generated.tables.pojos.Task;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

@ApplicationScoped
public class TaskService {

    @Inject
    private DSLContext context;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private TaskDao taskDao;

    public Response getTasks(int limit, int offset) {
        List<Task> result = context
                .select()
                .from(TASK)
                .orderBy(TASK.PRIORITY.desc(), TASK.ID.asc())
                .limit(limit)
                .offset(offset)
                .fetchInto(Task.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
                .build();

        return response;
    }

    public Response getTasksExtended(int limit, int offset) {
        List<TaskExtended> result = context
                .select(TASK.ID, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID, EMPLOYEE.NAME)
                .from(TASK)
                .leftJoin(EMPLOYEE)
                .on(EMPLOYEE.ID.eq(TASK.PERFORMER_ID))
                .orderBy(TASK.PRIORITY.desc(), TASK.ID.asc())
                .limit(limit)
                .offset(offset)
                .fetchInto(TaskExtended.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
                .build();

        return response;
    }

    public Response addTask(Task task) {
        if (task.getId() == null
                && task.getPriority() != null
                && checkPriority(task)
                && task.getDescription() != null
                && !task.getDescription().isEmpty()
                && task.getPerformerId() != null
                && employeeDao.existsById(task.getPerformerId())) {

            taskDao.insert(task);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response updateTask(Task task) {
        if (task.getId() != null
                && taskDao.existsById(task.getId())
                && task.getPriority() != null
                && checkPriority(task)
                && task.getDescription() != null
                && !task.getDescription().isEmpty()
                && task.getPerformerId() != null
                && employeeDao.existsById(task.getPerformerId())) {

            taskDao.update(task);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response saveTask(Task task) {
        if (task.getPriority() != null
                && checkPriority(task)
                && task.getDescription() != null
                && !task.getDescription().isEmpty()
                && task.getPerformerId() != null
                && employeeDao.existsById(task.getPerformerId())) {

            if (task.getId() == null) {
                taskDao.insert(task);

                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();

            } else if (taskDao.existsById(task.getId())) {
                taskDao.update(task);

                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            }
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteTask(Task task) {
        if (task.getId() != null
                && taskDao.existsById(task.getId())) {

            taskDao.delete(task);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response getTaskPriority() {
        Result result = context
                .select(TASK.ID, TASK.PRIORITY)
                .from(TASK)
                .orderBy(TASK.PRIORITY.asc())
                .fetch();

        TaskPriority taskPriority = new TaskPriority();

        if (result.isEmpty()) {
            taskPriority.setMinPriority(1);
            taskPriority.setMinPriorityTaskId(null);
            taskPriority.setMaxPriority(1);
            taskPriority.setMaxPriorityTaskId(null);
        } else if (result.size() == 1) {
            taskPriority.setMinPriority((int) result.getValue(0, "priority"));
            taskPriority.setMinPriorityTaskId((int) result.getValue(0, "id"));
            taskPriority.setMaxPriority((int) result.getValue(0, "priority"));
            taskPriority.setMaxPriorityTaskId((int) result.getValue(0, "id"));
        } else {
            taskPriority.setMinPriority((int) result.getValue(0, "priority"));
            taskPriority.setMaxPriority((int) result.getValue(result.size() - 1, "priority"));
            if (taskPriority.getMinPriority() == (int) result.getValue(1, "priority")) {
                taskPriority.setMinPriorityTaskId(null);
            } else {
                taskPriority.setMinPriorityTaskId((int) result.getValue(0, "id"));
            }
            if (taskPriority.getMaxPriority() == (int) result.getValue(result.size() - 2, "priority")) {
                taskPriority.setMaxPriorityTaskId(null);
            } else {
                taskPriority.setMaxPriorityTaskId((int) result.getValue(result.size() - 1, "id"));
            }
        }

        Response response = Response
                .status(Response.Status.OK)
                .entity(taskPriority)
                .build();

        return response;
    }

    // слижком сложный способ проверки приоритета
    private boolean checkPriority(Task task) {
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
            --minPriority;
        }

        int maxPriority = (int) result.getValue(result.size() - 1, "priority");
        if (task.getId() == null
                || (result.size() > 1
                && ((int) result.getValue(result.size() - 1, "id") != task.getId()
                || (int) result.getValue(result.size() - 2, "priority") == maxPriority))) {
            ++maxPriority;
        }

        return task.getPriority() >= minPriority && task.getPriority() <= maxPriority;
    }
}
