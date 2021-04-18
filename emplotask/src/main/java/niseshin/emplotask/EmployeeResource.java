package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import static generated.Tables.EMPLOYEE;
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
    public String employees() throws SQLException {
        Result<Record> result;

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            result = context.select().from(EMPLOYEE).fetch();
        }

        return result.formatJSON();
    }
}
