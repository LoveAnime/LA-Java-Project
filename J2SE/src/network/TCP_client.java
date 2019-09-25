package network;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 19:56
 * @description todo 有异常
 */
public class TCP_client {
    public static void main(String[] args) throws Exception {
        // 发出一个请求
        Socket s = new Socket("127.0.0.1", 6666);

        // 获得该连接的输出管道
        OutputStream os = s.getOutputStream();
        // 新建输出流
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("hello server");
        dos.flush();
        int i = 0;
        while (i < 10) {
            Thread.sleep(1000);
            dos.writeUTF("send " + (++i));
            dos.flush();
        }
        dos.close();
        s.close();
    }
}
