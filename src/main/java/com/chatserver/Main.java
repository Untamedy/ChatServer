package com.chatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class Main {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(3345)) {
            Socket client = server.accept();

            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            while (!client.isClosed()) {
                String entry = in.readUTF();

                if (entry.equalsIgnoreCase("exit")) {
                    out.writeUTF("Server reply - " + entry + " - OK");
                    out.flush();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                }

                out.writeUTF("Server reply - " + entry + " - OK");
                out.flush();
                out.flush();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
