package niseshin.emplotask;

import static generated.Tables.*;
import generated.tables.daos.EmployeeDao;
import generated.tables.daos.TaskDao;
import generated.tables.pojos.Employee;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.count;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private DSLContext context;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private TaskDao taskDao;

    public Response getEmployees(int limit, int offset) {
        List<Employee> result = context
                .select()
                .from(EMPLOYEE)
                .orderBy(EMPLOYEE.ID.asc())
                .limit(limit)
                .offset(offset)
                .fetchInto(Employee.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
                .build();

        return response;
    }

    public Response getName(int id) {
        Employee result = context
                .select(EMPLOYEE.ID, EMPLOYEE.NAME)
                .from(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .fetchOneInto(Employee.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
                .build();

        return response;
    }

    public Response getTaskCount(int id) {
        EmployeeExtended result = context
                .select(EMPLOYEE.ID, count(TASK.ID))
                .from(EMPLOYEE)
                .leftJoin(TASK)
                .on(TASK.PERFORMER_ID.eq(id))
                .where(EMPLOYEE.ID.eq(id))
                .groupBy(EMPLOYEE.ID)
                .fetchOneInto(EmployeeExtended.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
                .build();

        return response;
    }

    public Response getEmployeesExtended(int limit, int offset) {
        generated.tables.Employee e0 = EMPLOYEE.as("e0");
        generated.tables.Employee e1 = EMPLOYEE.as("e1");

        List<EmployeeExtended> result = context
                .select(e0.ID, e0.NAME, e0.POST, e0.BRANCH, e0.BOSS_ID, e1.NAME, count(TASK.ID))
                .from(e0)
                .leftJoin(e1)
                .on(e1.ID.eq(e0.BOSS_ID))
                .leftJoin(TASK)
                .on(TASK.PERFORMER_ID.eq(e0.ID))
                .groupBy(e0.ID, e1.NAME)
                .orderBy(e0.ID.asc())
                .limit(limit)
                .offset(offset)
                .fetchInto(EmployeeExtended.class);

        Response response = Response
                .status(result == null ? Response.Status.BAD_REQUEST : Response.Status.OK)
                .entity(result)
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
                && employeeDao.existsById(employee.getId())
                && employee.getName() != null
                && !employee.getName().isEmpty()
                && employee.getPost() != null
                && !employee.getPost().isEmpty()
                && employee.getBranch() != null
                && !employee.getBranch().isEmpty()) {

            employeeDao.update(employee);

            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response saveEmployee(Employee employee) {
        if (employee.getName() != null
                && !employee.getName().isEmpty()
                && employee.getPost() != null
                && !employee.getPost().isEmpty()
                && employee.getBranch() != null
                && !employee.getBranch().isEmpty()) {

            if (employee.getId() == null) {
                employeeDao.insert(employee);

                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();

            } else if (employeeDao.existsById(employee.getId())) {
                employeeDao.update(employee);

                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            }
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    public Response deleteEmployee(Employee employee) {
        if (employee.getId() != null
                && employeeDao.existsById(employee.getId())
                && employeeDao.fetchByBossId(employee.getId()).isEmpty()
                && taskDao.fetchByPerformerId(employee.getId()).isEmpty()) {

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
