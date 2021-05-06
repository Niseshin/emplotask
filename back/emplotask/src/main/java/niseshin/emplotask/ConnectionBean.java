package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

    @Produces
    @ApplicationScoped
    public DSLContext getDSLContext() {
        return context;
    }
}
