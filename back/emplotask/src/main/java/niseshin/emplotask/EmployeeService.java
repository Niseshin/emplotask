package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.daos.EmployeeDao;
import generated.tables.daos.TaskDao;
import generated.tables.pojos.Employee;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import org.jooq.Result;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private DSLContext context;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private TaskDao taskDao;

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

    public Response getEmployeesThroughDao() {
        Response response = Response
                .status(Response.Status.OK)
                .entity(employeeDao.findAll())
                .build();

        return response;
    }

    public Response addEmployee(Employee employee) {
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
//
//            employee.reset("id");
//            employee.attach(context.configuration());
//            employee.insert();

            employeeDao.insert(employee);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response updateEmployee(Employee employee) {
        if (employee.getId() != null
                // && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(employee.getId())).fetchOne() != null
                && employeeDao.existsById(employee.getId())
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
//
//            employee.attach(context.configuration());
//            employee.update();

            employeeDao.update(employee);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteEmployee(Employee employee) {
        if (employee.getId() != null
                // && context.select().from(EMPLOYEE).where(EMPLOYEE.ID.eq(employee.getId())).fetchOne() != null
                && employeeDao.existsById(employee.getId())
                // && context.select().from(EMPLOYEE).where(EMPLOYEE.BOSS_ID.eq(employee.getId())).fetch().isEmpty()
                && employeeDao.fetchByBossId(employee.getId()).isEmpty()
                // && context.select().from(TASK).where(TASK.PERFORMER_ID.eq(employee.getId())).fetch().isEmpty()
                && taskDao.fetchByPerformerId(employee.getId()).isEmpty()) {
//            context.delete(EMPLOYEE)
//                    .where(EMPLOYEE.ID.eq(employee.getId()))
//                    .execute();
//
//            employee.attach(context.configuration());
//            employee.delete();

            employeeDao.deleteById(employee.getId());

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
