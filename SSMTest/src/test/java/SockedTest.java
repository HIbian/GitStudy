import org.junit.Test;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SockedTest {
    @Test
    public void test1() throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();


    }
}
