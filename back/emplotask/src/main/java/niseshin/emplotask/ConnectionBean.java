package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import generated.tables.daos.EmployeeDao;
import generated.tables.daos.TaskDao;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

@ApplicationScoped
public class ConnectionBean {

    private final HikariConfig config = new HikariConfig(EmployeeResource.class.getResource("hikari.properties").getPath());
    private final HikariDataSource ds = new HikariDataSource(config);
    private final DSLContext context = DSL.using(ds, SQLDialect.POSTGRES);
    private final EmployeeDao employeeDao = new EmployeeDao(context.configuration());
    private final TaskDao taskDao = new TaskDao(context.configuration());

    @Produces
    @ApplicationScoped
    public DSLContext getDSLContext() {
        return context;
    }

    @Produces
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    @Produces
    public TaskDao getTaskDao() {
        return taskDao;
    }
}
