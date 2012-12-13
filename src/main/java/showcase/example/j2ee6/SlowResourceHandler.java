package showcase.example.j2ee6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zfc827@gmail.com
 */
public class SlowResourceHandler implements Runnable {

    private AsyncContext asyncCtx;

    public void setAsyncCtx(AsyncContext asyncCtx) {
        this.asyncCtx = asyncCtx;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = asyncCtx.getResponse().getWriter();
        } catch (IOException e) {
        }
        writer.println("<br/>start asynchronous execute...");
        writer.flush();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        writer.println("<br/>sleep 2 second continue execute...");
        asyncCtx.complete();
    }
}
