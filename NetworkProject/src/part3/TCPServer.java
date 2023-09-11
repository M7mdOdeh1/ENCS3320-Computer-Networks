package part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException  {
    	
    	//welcoming socket
        ServerSocket serverSocket = new ServerSocket(7788);
        System.out.println("Server listening on port 7788");

        //loop forever
        while (true) {
            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();  
            System.out.println("-----------------------------------------");
            System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress()
            		+" , Port: "+ clientSocket.getPort());

            // Get input stream from client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();
            
            String path = "";
            // Read input from client
            String inputLine;
            while ((inputLine = in.readLine()) != null  && !inputLine.isEmpty()) {
            	System.out.println(inputLine);
            	if (inputLine.startsWith("GET")) {
            		//to get the user request
                    int index = inputLine.indexOf("/");
                    path = inputLine.substring(index);
                    path = path.substring(0, path.indexOf(" "));
                    
            	}
			}
			System.out.println("Path: " + path);

			if (path.equals("/") || path.equals("/index.html") || path.equals("/main_en.html") || path.equals("/en")) {

				FileInputStream fileInputStream = new FileInputStream("src/part3/main_en.html");
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);

				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);
				
			} else if (path.equals("/ar") || path.equals("/main_ar.html")) {
				FileInputStream fileInputStream = new FileInputStream("src/part3/main_ar.html");
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);

				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);

			} else if (path.endsWith(".html")) {
				FileInputStream fileInputStream = null;
				if(new File("src/part3"+path).exists()) {
					fileInputStream = new FileInputStream("src/part3"+path);
				}
				
				else {
					fileInputStream = new FileInputStream("src/part3/simplePage.html");
				}
				
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);
				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);

			} else if (path.endsWith(".css")) {
					
				FileInputStream fileInputStream=null;
				if(new File("src/part3"+path).exists()) {
					fileInputStream = new FileInputStream("src/part3"+path);
				}
				
				else {
					fileInputStream = new FileInputStream("src/part3/style2.css");
				}
				
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);
				
				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write("Content-Type: text/css\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);

			} else if (path.endsWith(".png")) {
				
				FileInputStream fileInputStream = null;
				if(new File("src/part3"+path).exists()) {
					fileInputStream = new FileInputStream("src/part3"+path);
				}
				
				else {
					fileInputStream = new FileInputStream("src/part3/image1.png");
				}
				
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);
				
				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write(("Content-Length: " + data.length + "\r\n").getBytes());
				out.write("Content-Type: image/png \r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);

				
				
				

			} else if (path.endsWith(".jpg")) {
				
				FileInputStream fileInputStream = null;
				if(new File("src/part3"+path).exists()) {
					fileInputStream = new FileInputStream("src/part3"+path);
				}
				else {
					fileInputStream = new FileInputStream("src/part3/image2.jpg");
				}
				
				byte[] data = new byte[fileInputStream.available()];
				fileInputStream.read(data);
				fileInputStream.read(data);

				out.write("HTTP/1.1 200 OK\r\n".getBytes());
				out.write(("Content-Length: " + data.length + "\r\n").getBytes());
				out.write("Content-Type: image/jpeg\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(data);

				

			} else if (path.equals("/go")) {
				out.write("HTTP/1.1 307 Temporary Redirect\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("Location: https://www.google.com".getBytes());
				out.write("\r\n".getBytes());
				
			} else if (path.equals("/so")) {
				out.write("HTTP/1.1 307 Temporary Redirect\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("Location: https://www.stackoverflow.com".getBytes());
				out.write("\r\n".getBytes());
				
			} else if (path.equals("/bzu")) {
				out.write("HTTP/1.1 307 Temporary Redirect\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("Location: https://www.birzeit.edu/".getBytes());
				out.write("\r\n".getBytes());
				
			} else {
				String s = "<!DOCTYPE html> <html> <head> <title>Error</title></head>"
	                    + "<body style=\"background-image: url('k5.jpg'); background-size: cover; color: red;"
	                    + " <div style=\"text-align: center\">"
	                    + "   <h1 style=\"text-align: center; text-shadow: 2px 2px 4px #000000\">The file is not found</h1>"
	                    + "   <p style=\"text-align: center\"><strong>Mohammed Owda (ID: 1200089)</strong><br>"
	                    + "   <strong>Mohammad Abu Shams (ID: 1200549)</strong><br>"
	                    + "   <strong>Mohammad Dallash (ID: 1200937)</strong></p>" 
	                    + "   <p style=\"text-align: center\">Client IP: " + clientSocket.getInetAddress().getHostAddress() + "<br>"
	                    + "   Client port: " + clientSocket.getPort() + "</p>"
	                    + " </div>"
	                    + "</body></html>";

				out.write("HTTP/1.1 404 Not Found\r\n".getBytes());
				out.write("Content-Type: text/html\r\n".getBytes());
				out.write("\r\n".getBytes());
				out.write(s.getBytes());
			}

			out.flush();
			clientSocket.close();
			out.close();
			// Close client socket
			clientSocket.close();
			System.out.println("Connection Closed");
			System.out.println("-----------------------------------------");

        }
    }
}