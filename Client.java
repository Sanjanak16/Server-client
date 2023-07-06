import java.io.*;
import java.net.Socket;
import java.util.*;
//implementing Client class
public class Client {
    public static void main(String[] args) {
     String hostsxk = "localhost";
     //initiating port number to 5000
    int portnumsxk = 1234;
        try {
            // creating the server socket connection
            Socket serversxkSocket = new Socket(hostsxk, portnumsxk);
            PrintWriter outsxk = new PrintWriter(serversxkSocket.getOutputStream(), true);
            BufferedReader inputsxk = new BufferedReader(new InputStreamReader(serversxkSocket.getInputStream()));
            Scanner scsxk = new Scanner(System.in); //menu options to display on console
                 while (true) {
                System.out.println("********Select any option:");
                System.out.println("1. PUT");
                System.out.println("2. GET");
                System.out.println("3. DUMP");
                System.out.println("4. EXIT");
                System.out.print("Enter the option number: ");
                int commandNumber = scsxk.nextInt();
                scsxk.nextLine();
                switch (commandNumber) {
                    case 1:
                        System.out.print("Enter key: ");
                        String keysxk = scsxk.nextLine();
                        System.out.print("Enter value: ");
                        String value = scsxk.nextLine();
                        outsxk.println("PUT " + keysxk + " = " + value);
                        String response = inputsxk.readLine();
                        System.out.println("Server response: " + response);
                        break;
                    case 2:
                        System.out.print("Enter key: ");
                        keysxk = scsxk.nextLine();
                        outsxk.println("GET " + keysxk);
                        response = inputsxk.readLine();
                        System.out.println("Server response: " + response);
                        break;
                    case 3:
                        outsxk.println("DUMP");
                        response = inputsxk.readLine();
                        break;
                    case 4:
                        scsxk.close();
                        outsxk.println("EXIT");
                        response = inputsxk.readLine();
                        System.exit(0);
                    default:
                        System.out.println("Invalid command number.");
                        break;
                }}
        } catch (Exception ex) {
         ex.printStackTrace();
 }
}
}
