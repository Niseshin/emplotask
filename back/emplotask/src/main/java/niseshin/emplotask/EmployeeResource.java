package niseshin.emplotask;

import generated.tables.pojos.Employee;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
@ApplicationScoped
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getEmployees() {
        return employeeService.getEmployees();
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesThroughDao() {
        return employeeService.getEmployeesThroughDao();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(Employee employee) {
        return employeeService.deleteEmployee(employee);
    }
}
