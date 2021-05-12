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
    public Response getEmployeesOld() {
        return employeeService.getEmployeesOld();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(
            @DefaultValue("100") @QueryParam("lim") int limit,
            @DefaultValue("0") @QueryParam("off") int offset) {
        return employeeService.getEmployees(limit, offset);
    }

    @GET
    @Path("getname/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getName(@PathParam("id") int id) {
        return employeeService.getName(id);
    }

    @GET
    @Path("gettaskcount/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskCount(@PathParam("id") int id) {
        return employeeService.getTaskCount(id);
    }

    @GET
    @Path("listex")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesExtended(
            @DefaultValue("100") @QueryParam("lim") int limit,
            @DefaultValue("0") @QueryParam("off") int offset) {
        return employeeService.getEmployeesExtended(limit, offset);
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
