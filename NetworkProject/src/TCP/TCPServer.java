package TCP;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException  {
    	
    	//welcoming socket
        ServerSocket serverSocket = new ServerSocket(5566);
        System.out.println("Server listening on port 5566");


        //loop forever
        while (true) {
            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();  
            System.out.println("-----------------------------------------");
            System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress()
            		+" , Port: "+ clientSocket.getPort());

            // Get input stream from client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Measure start time
            long startTime = System.currentTimeMillis();
            
            int count = 0;

            // Read input from client
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	//System.out.print(inputLine+" ");
                 count++;
            }
            // Measure end time
            long endTime = System.currentTimeMillis();
            
            // Calculate elapsed time
            long elapsedTime = endTime - startTime;
            
            
            
            System.out.println("No. Of Packets recived: "+count);
            System.out.println("Eapsed Time to recive the packet: " + elapsedTime/1000.0 + " seconds");
            

            // Close client socket
            clientSocket.close();
            System.out.println("Connection Closed");
            System.out.println("-----------------------------------------");

        }
    }
}