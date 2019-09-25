package network;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 19:56
 * @description
 */
public class TCP_server {
    public static void main(String[] args) throws Exception {
        // 	启动一个服务器
        ServerSocket ss = new ServerSocket(6666);

        while (true) {
            // 新一个插槽用来接收一个请求
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis.readUTF());
            dis.close();
            s.close();
        }
    }
}
