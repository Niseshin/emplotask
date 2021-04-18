package niseshin.emplotask;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import static generated.Tables.EMPLOYEE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

// оставил на память как делать не нужно :)
@WebServlet("/wrong")
public class WrongServlet extends HttpServlet {

    // а здесь у нас создаются новые соединения каждый раз когда кто-то использует сервлет :) что приводит к неминуемым отказам бд
    HikariConfig config = new HikariConfig(getClass().getResource("hikari.properties").getPath());
    HikariDataSource ds = new HikariDataSource(config);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try (Connection con = ds.getConnection();
                DSLContext context = DSL.using(con, SQLDialect.POSTGRES);) {
            Result<Record> result = context.select().from(EMPLOYEE).fetch();

            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Работники</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Список работников</h1>");
            for (Record r : result) {
                Integer id = r.getValue(EMPLOYEE.ID);
                String name = r.getValue(EMPLOYEE.NAME);
                String post = r.getValue(EMPLOYEE.POST);
                String branch = r.getValue(EMPLOYEE.BRANCH);
                Integer bossId = r.getValue(EMPLOYEE.BOSS_ID);

                // пытался использовать внешний ключ для вытаскивания имени босса, но это вызывает гору селектов, вероятно есть способ лучше
                String boss = (bossId == null) ? "-" : context.select(EMPLOYEE.NAME).from(EMPLOYEE).where(EMPLOYEE.ID.eq(bossId)).fetchOne().getValue(EMPLOYEE.NAME);

                out.printf("<p>ID: %d, Имя: %s, Должность: %s, Филиал: %s, Начальник: %s</p>", id, name, post, branch, boss);
            }
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(WrongServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
