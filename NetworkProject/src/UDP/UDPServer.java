package UDP;

import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        // Create server socket and bind it to port 5566
        DatagramSocket serverSocket = new DatagramSocket(5566);
        System.out.println("UDP Server listening on port 5566");

        // Count the number of received messages
        int count = 0;

        // Create buffer for incoming data
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        long startTime = System.currentTimeMillis();
        long endTime= System.currentTimeMillis();
        // Start server loop
        while (true) {
            // Wait for a packet to be received
            serverSocket.receive(packet);
            byte[] data = packet.getData();
            String dataString = new String(data, 0, packet.getLength());
           // System.out.println(dataString);
            if(count==0) {
            	// Measure start time
                 startTime = System.currentTimeMillis();
                 System.out.println("Reciving Packets from " + packet.getAddress().getHostAddress()
                 		+" , Port: " + packet.getPort());
            }
            
            // Increment count for each received packet
            count++;
            
            // Reset packet for next receive
            packet.setLength(buffer.length);
            
            if (count ==1000001 || dataString.equals("1000000")) {
                
            	endTime= System.currentTimeMillis();
                double elapsedTime = (endTime - startTime)/1000.0;
                System.out.println("Elapsed Time to recive the packets: "+ elapsedTime + " seconds");
                System.out.println("No. Of Packets Recived: "+count);
                count=0;
                }
        }    
    }
}
