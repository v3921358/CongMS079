package gui;

import java.io.IOException;
import java.net.Socket;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class 监听
{
    public static void oneServer() throws IOException {
        final String receive = null;
        final ServerSocket server = new ServerSocket(8484);
        System.out.println("服务器启动成功");
        final Socket socket = server.accept();
        final BufferedReader in = new BufferedReader((Reader)new InputStreamReader(socket.getInputStream()));
        final BufferedWriter writer = new BufferedWriter((Writer)new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            System.out.println("客户端发来:" + receive);
            writer.write("服务器接收到了:" + receive + '\n');
            writer.flush();
        }
    }
}
