package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.records.EmployeeRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private DSLContext context;

    public Response getEmployees() {
        Result result = context
                .select()
                .from(EMPLOYEE)
                .fetch();
        Response response = Response
                .status(Response.Status.OK)
                .entity(result.formatJSON())
                .build();

        return response;
    }

    public Response addEmployee(EmployeeRecord employee) {
        if (employee.getId() == null
                && employee.getName() != null
                && !employee.getName().isEmpty()
                && employee.getPost() != null
                && !employee.getPost().isEmpty()
                && employee.getBranch() != null
                && !employee.getBranch().isEmpty()) {
//            context.insertInto(EMPLOYEE, EMPLOYEE.NAME, EMPLOYEE.POST, EMPLOYEE.BRANCH, EMPLOYEE.BOSS_ID)
//                    .values(employee.getName(), employee.getPost(), employee.getBranch(), employee.getBossId())
//                    .returning()
//                    .fetch();

            employee.reset("id");
            employee.attach(context.configuration());
            employee.insert();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response updateEmployee(EmployeeRecord employee) {
        if (employee.getId() != null
                && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(employee.getId())).fetchOne() != null
                && employee.getName() != null
                && !employee.getName().isEmpty()
                && employee.getPost() != null
                && !employee.getPost().isEmpty()
                && employee.getBranch() != null
                && !employee.getBranch().isEmpty()) {
//            context.update(EMPLOYEE)
//                    .set(EMPLOYEE.NAME, employee.getName())
//                    .set(EMPLOYEE.POST, employee.getPost())
//                    .set(EMPLOYEE.BRANCH, employee.getBranch())
//                    .set(EMPLOYEE.BOSS_ID, employee.getBossId())
//                    .where(EMPLOYEE.ID.eq(employee.getId()))
//                    .execute();

            employee.attach(context.configuration());
            employee.update();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteEmployee(EmployeeRecord employee) {
        if (employee.getId() != null
                && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(employee.getId())).fetchOne() != null
                && context.select().from(EMPLOYEE).where(EMPLOYEE.BOSS_ID.eq(employee.getId())).fetch().isEmpty()
                && context.select().from(TASK).where(TASK.PERFORMER_ID.eq(employee.getId())).fetch().isEmpty()) {
//            context.delete(EMPLOYEE)
//                    .where(EMPLOYEE.ID.eq(employee.getId()))
//                    .execute();

            employee.attach(context.configuration());
            employee.delete();

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
