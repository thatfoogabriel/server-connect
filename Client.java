import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {
    public static void main (String args[]) throws Exception {
        Scanner kb = new Scanner(System.in);
        int num = Integer.parseInt(args[0]);
        String name = "Client of Gabriel Soto";
        System.out.println(name);

        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(name + '\n');
        outToServer.writeBytes(String.valueOf(num) + '\n');
        if (num <1 || num >100) {
            System.out.println("Enter a number between 1 and 100");
            clientSocket.close();
            System.exit(0);
        }
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String nameMessage = inFromServer.readLine();
        String numMessage = inFromServer.readLine();

        System.out.println("Server Name: " + nameMessage);
        System.out.println("Client Number: " + num);
        System.out.println("Server Number: " + numMessage);
        int sum = (num + Integer.parseInt(numMessage));
        System.out.println("Sum of two numbers: " + sum);

        clientSocket.close();
    }
}