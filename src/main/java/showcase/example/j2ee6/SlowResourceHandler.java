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

    private final Logger logger = LoggerFactory.getLogger(SlowResourceHandler.class);

    private AsyncContext asyncCtx;

    public void setAsyncCtx(AsyncContext asyncCtx) {
        this.asyncCtx = asyncCtx;
    }

    @Override
    public void run() {
        logger.info("start asynchronous execute...");
        PrintWriter writer = null;
        try {
            writer = asyncCtx.getResponse().getWriter();
        } catch (IOException e) {
        }
        writer.println("start asynchronous execute...");
        writer.flush();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        writer.println("sleep 10 second continue execute...");
        writer.flush();
        asyncCtx.complete();
    }
}
