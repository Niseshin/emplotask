package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import javax.ejb.Singleton;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

//@Singleton
public class ConnectionBean {

    private static final HikariConfig config = new HikariConfig(EmployeeResource.class.getResource("hikari.properties").getPath());
    private static final HikariDataSource ds = new HikariDataSource(config);
    private static final DSLContext context = DSL.using(ds, SQLDialect.POSTGRES);

    public static DSLContext getDSLContext() {
        return context;
    }
}
