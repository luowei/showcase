package showcase.example.j2ee6;

import org.joda.time.DateTime;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zfc827@gmail.com
 */
@WebServlet(name = "asyncServlet", urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("current date: " + new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        AsyncContext asyncContext = req.startAsync();

        ServletRequest request = asyncContext.getRequest();
        boolean flag = request.isAsyncStarted();

        writer.println("<br/>AsyncStarted : " + flag);

        flag = request.isAsyncStarted();

        writer.println("<br/>AsyncStarted : " + flag);

        SlowResourceHandler slowResourceHandler = new SlowResourceHandler();
        slowResourceHandler.setAsyncCtx(asyncContext);

        asyncContext.start(slowResourceHandler);

        writer.println("<br/>servlet method executed...");
        writer.flush();
    }
}
