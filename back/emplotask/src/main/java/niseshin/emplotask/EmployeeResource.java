package niseshin.emplotask;

import generated.tables.records.EmployeeRecord;
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

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(EmployeeRecord employee) {
        return employeeService.addEmployee(employee);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmployeeRecord employee) {
        return employeeService.updateEmployee(employee);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(EmployeeRecord employee) {
        return employeeService.deleteEmployee(employee);
    }
}
