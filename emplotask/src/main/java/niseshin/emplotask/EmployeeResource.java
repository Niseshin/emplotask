package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import static generated.Tables.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ws.rs.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

@Path("employees")
public class EmployeeResource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        config = new HikariConfig(EmployeeResource.class.getResource("hikari.properties").getPath());
        ds = new HikariDataSource(config);
    }

    @GET
    public String getEmployees() throws SQLException {
        Result<Record> result;

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            result = context.select().from(EMPLOYEE).fetch();
        }

        return result.formatJSON();
    }

    @POST
    @Path("add")
    public void addEmployee(String content) throws SQLException {
        String[] args = content.substring(2, content.length() - 2).split("\",\"");

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            int bossId = context.select(EMPLOYEE.ID).from(EMPLOYEE).where(EMPLOYEE.NAME.eq(args[3])).fetchOne().getValue(EMPLOYEE.ID);
            context.insertInto(EMPLOYEE, EMPLOYEE.NAME, EMPLOYEE.POST, EMPLOYEE.BRANCH, EMPLOYEE.BOSS, EMPLOYEE.BOSS_ID).values(args[0], args[1], args[2], args[3], bossId).returning().fetch();
        }
    }
}
