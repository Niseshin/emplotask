package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.records.EmployeeRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.Result;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private ConnectionBean conBean;

    public Response getEmployees() {
        Result result = conBean.getDSLContext()
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
        if (employee.getName() != null
                && employee.getPost() != null
                && employee.getBranch() != null) {
//            context.insertInto(EMPLOYEE, EMPLOYEE.NAME, EMPLOYEE.POST, EMPLOYEE.BRANCH, EMPLOYEE.BOSS_ID)
//                    .values(employee.getName(), employee.getPost(), employee.getBranch(), employee.getBossId())
//                    .returning()
//                    .fetch();

            // обнаружил еще такой вариант, работает, но прежняя версия мне больше нравится
            employee.attach(conBean.getDSLContext().configuration());
            employee.reset("id");
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
                && employee.getName() != null
                && employee.getPost() != null
                && employee.getBranch() != null) {
//            context.update(EMPLOYEE)
//                    .set(EMPLOYEE.NAME, employee.getName())
//                    .set(EMPLOYEE.POST, employee.getPost())
//                    .set(EMPLOYEE.BRANCH, employee.getBranch())
//                    .set(EMPLOYEE.BOSS_ID, employee.getBossId())
//                    .where(EMPLOYEE.ID.eq(employee.getId()))
//                    .execute();

            // а может быть новая версия не так уж плоха :)
            employee.attach(conBean.getDSLContext().configuration());
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
                && conBean.getDSLContext().select().from(EMPLOYEE).where(EMPLOYEE.BOSS_ID.eq(employee.getId())).fetch().isEmpty()
                && conBean.getDSLContext().select().from(TASK).where(TASK.PERFORMER_ID.eq(employee.getId())).fetch().isEmpty()) {
//            context.delete(EMPLOYEE)
//                    .where(EMPLOYEE.ID.eq(employee.getId()))
//                    .execute();

            // надо найти способ приклеивать конфигурацию автоматически (инъекции?)
            employee.attach(conBean.getDSLContext().configuration());
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
