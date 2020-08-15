package com.xibo.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author xihao.pan
 */
public class SocketUtil {

    public static void server(int port, Consumer<? super InputStream> function) throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                function.accept(socket.getInputStream());
            }
        }
    }

    public static InputStream send(InetAddress address, int port, String mes) throws IOException {
        try (Socket socket = new Socket(address, port)) {
            socket.getOutputStream().write(mes.getBytes("UTF-8"));
            socket.shutdownOutput();
            return socket.getInputStream();
        }
    }

    public static Consumer<InputStream> printer = (in) -> {
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    };

}
