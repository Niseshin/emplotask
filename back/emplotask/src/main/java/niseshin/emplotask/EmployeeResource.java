package niseshin.emplotask;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
public class EmployeeResource {

    private final EmployeeService employeeService = new EmployeeService();

    @GET
    public Response getEmployees() {
        return employeeService.getEmployees();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(Employee employee) {
        return employeeService.deleteEmployee(employee);
    }
}
