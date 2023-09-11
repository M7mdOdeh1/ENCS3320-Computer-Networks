package TCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        // Create socket and connect to server on localhost (127.0.0.1) and port 5566
        Socket clientSocket = new Socket("127.0.0.1", 5566);
        System.out.println("Connected to server at " + clientSocket.getInetAddress().getHostAddress());

        // Get output stream from client socket
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedWriter out = new BufferedWriter(outputStreamWriter);

        // Measure start time
        long startTime = System.currentTimeMillis();
        
        // Send numbers from 0 to 1,000,000 to server
        for (int i = 0; i <= 1000000; i++) {
           // System.out.print(i);
            out.write(String.valueOf(i));
            out.newLine();
            out.flush();
        }
        // Measure end time
        long endTime = System.currentTimeMillis();
        
        // Calculate elapsed time to send the packets
        long elapsedTime = endTime - startTime;
        
        System.out.println("\nElapsed Time to send the packets: "+ elapsedTime/1000.0 + " seconds");

        // Close client socket
        clientSocket.close();
    }
}
