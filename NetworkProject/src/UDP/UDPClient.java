package UDP;

import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		// Create client socket and connect to server on localhost (127.0.0.1) and port 5566
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

		// Create buffer for outgoing data
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 5566);

		// Measure start time
        long startTime = System.currentTimeMillis();
        
        System.out.println("Sending Packets to " + packet.getAddress() +" , Port:"+ packet.getPort()+ " ...");
		// Send numbers from 0 to 1,000,000 to server
		for (int i = 0; i <= 1000000; i++) {
			// Convert number to bytes
			byte[] data = String.valueOf(i).getBytes();
			// Set data and length in packet
			packet.setData(data);
			packet.setLength(data.length);
			// Send packet
			clientSocket.send(packet);
		}
		// Measure end time
        long endTime = System.currentTimeMillis();
        
     // Calculate elapsed time to send the packets
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed Time to send the packets: "+ elapsedTime/1000.0 + " seconds");
	}
}
