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

@Path("tasks")
public class TaskResource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        config = new HikariConfig(TaskResource.class.getResource("hikari.properties").getPath());
        ds = new HikariDataSource(config);
    }

    @GET
    public String getTasks() throws SQLException {
        Result<Record> result;

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            result = context.select().from(TASK).fetch();
        }

        return result.formatJSON();
    }

    @POST
    @Path("add")
    public void addTask(String content) throws SQLException {
        String[] args = content.substring(2, content.length() - 2).split("\",\"");

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            int performerId = context.select(EMPLOYEE.ID).from(EMPLOYEE).where(EMPLOYEE.NAME.eq(args[2])).fetchOne().getValue(EMPLOYEE.ID);
            context.insertInto(TASK, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER, TASK.PERFORMER_ID).values(Integer.parseInt(args[0]), args[1], args[2], performerId).returning().fetch();
        }
    }
}
