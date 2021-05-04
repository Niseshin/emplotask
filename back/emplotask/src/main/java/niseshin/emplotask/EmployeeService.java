package niseshin.emplotask;

import static generated.Tables.*;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

public class EmployeeService {

    private static final DSLContext context = ConnectionBean.getDSLContext();

    public Response getEmployees() {
        Result result = context.select()
                .from(EMPLOYEE)
                .fetch();
        Response response = Response
                .status(Response.Status.OK)
                .entity(result.formatJSON())
                .build();

        return response;
    }

    public void addEmployee(Employee employee) {
        context.insertInto(EMPLOYEE, EMPLOYEE.NAME, EMPLOYEE.POST, EMPLOYEE.BRANCH, EMPLOYEE.BOSS_ID)
                .values(employee.name, employee.post, employee.branch, employee.boss)
                .returning()
                .fetch();
    }

    public void updateEmployee(Employee employee) {
        context.update(EMPLOYEE)
                .set(EMPLOYEE.NAME, employee.name)
                .set(EMPLOYEE.POST, employee.post)
                .set(EMPLOYEE.BRANCH, employee.branch)
                .set(EMPLOYEE.BOSS_ID, employee.boss)
                .where(EMPLOYEE.ID.eq(employee.id))
                .execute();
    }

    public Response deleteEmployee(Employee employee) {
        if (context.select().from(EMPLOYEE).where(EMPLOYEE.BOSS_ID.eq(employee.id)).fetch().isEmpty()
                && context.select().from(TASK).where(TASK.PERFORMER_ID.eq(employee.id)).fetch().isEmpty()) {
            context.delete(EMPLOYEE)
                    .where(EMPLOYEE.ID.eq(employee.id))
                    .execute();
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
        // возможно стоит выбрать другой код ответа
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
