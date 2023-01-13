package SocketFun;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        final var scanner = new Scanner(System.in);

        try (
                final var socket = new Socket("127.0.0.1", 34567);
                final var inputStream = new DataInputStream(socket.getInputStream());
                final var outputStream = new DataOutputStream(socket.getOutputStream());
        ) {
            while (true) {
                var input = scanner.nextLine();
                outputStream.writeUTF(input);
                if (input.equals("DONE")) {
                    break;
                } else {
                    System.out.println("Server says: " + inputStream.readUTF());
                }
            }
        }
    }
}
