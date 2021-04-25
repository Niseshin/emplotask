package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import static generated.Tables.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ws.rs.*;
import org.jooq.DSLContext;
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
        Result result;

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            result = context.select(TASK.ID, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID, EMPLOYEE.NAME)
                    .from(TASK.join(EMPLOYEE).on(EMPLOYEE.ID.eq(TASK.PERFORMER_ID)))
                    .fetch();
        }

        return result.formatJSON();
    }

    @POST
    @Path("add")
    public void addTask(String content) throws SQLException {
        String[] args = content.substring(2, content.length() - 2).split("\",\"");

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            context.insertInto(TASK, TASK.PRIORITY, TASK.DESCRIPTION, TASK.PERFORMER_ID)
                    .values(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]))
                    .returning()
                    .fetch();
        }
    }

    @POST
    @Path("update")
    public void updateTask(String content) throws SQLException {
        String[] args = content.substring(2, content.length() - 2).split("\",\"");

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            context.update(TASK)
                    .set(TASK.PRIORITY, Integer.parseInt(args[0]))
                    .set(TASK.DESCRIPTION, args[1])
                    .set(TASK.PERFORMER_ID, Integer.parseInt(args[2]))
                    .where(TASK.ID.eq(Integer.parseInt(args[3])))
                    .execute();
        }
    }

    @POST
    @Path("delete")
    public void deleteTask(String content) throws SQLException {

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            context.delete(TASK)
                    .where(TASK.ID.eq(Integer.parseInt(content)))
                    .execute();
        }
    }
}
