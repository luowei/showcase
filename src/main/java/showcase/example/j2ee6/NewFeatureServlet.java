package showcase.example.j2ee6;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zfc827@gmail.com
 */
@WebServlet(name = "newServlet", urlPatterns = "/new", asyncSupported = true)
public class NewFeatureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        SlowResourceHandler slowResourceHandler = new SlowResourceHandler();
        slowResourceHandler.setAsyncCtx(asyncContext);

        asyncContext.start(slowResourceHandler);

        resp.getWriter().println("servlet method executed...");
        resp.getWriter().flush();
    }
}
