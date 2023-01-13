package SocketFun;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server {

    public static void main(String[] args) throws IOException {
        try (
                final var serverSocket = new ServerSocket(34567);
                final var executor = Executors.newVirtualThreadPerTaskExecutor();
        ) {
            // connect a client
            while (true) {
                System.out.println("Waiting for connection");
                final var socket = serverSocket.accept();
                System.out.println("Connection established, creating echo session");
                final var session = new Session(socket);
                System.out.println("Starting echo session");

                executor.execute(session);

                //commonPool.  execute(session);
            }
        }
    }

    static class Session implements Runnable {
        private final Socket socket;

        Session(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    var inputStream = new DataInputStream(socket.getInputStream());
                    var outputStream = new DataOutputStream(socket.getOutputStream())
            ) {
                while (true) {
                    final var input = inputStream.readUTF();
                    if (input.equals("DONE")) {
                        System.out.println("I'm done, terminating.");
                        break;
                    } else {
                        System.out.println("Echoing: " + input);
                        outputStream.writeUTF(input);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
