import java.io.*;
import java.net.*;
import java.util.Random;

class Server {
    public static void main (String args[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            String name = "Server of Gabriel Soto";
            System.out.println(name);
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            Random rand = new Random();

            String nameMessage = inFromClient.readLine();
            String numMessage = inFromClient.readLine();
            if (Integer.parseInt(numMessage) <1 || Integer.parseInt(numMessage) >100) {
                System.out.println("Shutting down...");
                connectionSocket.close();
                welcomeSocket.close();
                break;
            }

            int num = rand.nextInt(100)+1;
            System.out.println("Client name: " + nameMessage);
            System.out.println("Client number: " + numMessage);
            System.out.println("Server number: " + num);
            int sum = (num + Integer.parseInt(numMessage));
            System.out.println("Sum of two numbers: " + sum + "\n");

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeBytes(name + '\n');
            outToClient.writeBytes(String.valueOf(num) + '\n');

            connectionSocket.close();
        }
        welcomeSocket.close();
    }
}