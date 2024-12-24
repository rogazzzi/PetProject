package ApiServerModule;

public class StartServer implements Runnable {
    @Override
    public void run() {
        ApiServer as = new ApiServer();
        as.StartServer();
    }
}
