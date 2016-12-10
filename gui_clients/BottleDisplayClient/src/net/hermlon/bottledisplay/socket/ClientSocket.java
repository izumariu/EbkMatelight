package net.hermlon.bottledisplay.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hermlon on 26.11.16.
 */
public class ClientSocket {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip;
    private int port;

    public ClientSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void send(String msg) {
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(msg + "\r\n");


            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
