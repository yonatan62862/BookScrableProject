package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private int port;
    private volatile boolean running = false;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
    }

    public void start() {
        running = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        clientHandler.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
                    } catch (IOException e) {
                        if (!running) break;
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stop();
            }
        }).start();
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stop();
    }
}
