package com.rishab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echo extends Thread {
    private Socket socket;

    public Echo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                System.out.println("Received client input: " + echoString);

                if (echoString.equals("exit")) {
                    break;
                }

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }

                output.println(echoString);
            }
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error in closing socket: " + e.getMessage());
            }
        }
    }
}
