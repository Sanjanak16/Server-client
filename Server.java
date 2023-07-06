import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
//Server class
public class Server {
    private static Map<String, String> keyValueHashingMap = new HashMap<>();
    private static Map<String, String> cachingMap = new HashMap<>();
    public static void main(String[] args) {
     //selected port number is 1234   
        int portnumsxk = 1234;
            try {
            // establishing a server socket and wait for client connection
            ServerSocket serversxkSocket = new ServerSocket(portnumsxk);
            System.out.println(" The Server is runing on port 1234 and waiting for the client connection"); 
            Socket clientsxkSocket = serversxkSocket.accept();
            System.out.println("Client has connected now: " + clientsxkSocket);
            // create reader and writer objects
            BufferedReader inputsxk = new BufferedReader(new InputStreamReader(clientsxkSocket.getInputStream()));
            PrintWriter outsxk = new PrintWriter(clientsxkSocket.getOutputStream(), true);
            while (true) {
                // reading the input from client
                String dynamicinput = inputsxk.readLine();
                if (dynamicinput == null) {
                    continue;
                }
                String[] indexes = dynamicinput.split(" ");
                String request = indexes[0];
                if (request.equals("PUT")) 
                {
                String key = indexes[1];
                String value = indexes[3];
                    keyValueHashingMap.put(key, value);
                    System.out.println("key-value pair that is saved: " + key + " = " + value);
                    outsxk.println("The key-value pair has been successfully saved.");
// retreiving GET function from client
                } else if (request.equals("GET")) {
                    String keys = indexes[1];
                    String score = keyValueHashingMap.get(keys);

                    if (score == null) 
                    {
                        outsxk.println("There was no key found..");
                    } else 
                    { //proxy GET from caching the value
                        String cachedValue = cachingMap.get(keys);
                        if (cachedValue == null) 
                        {
                            cachingMap.put(keys, score);
                            outsxk.println("Value retrieved: " + score);
                        } else 
                        {
                            System.out.println("Cache value was retrieved: " + cachedValue);
                            outsxk.println("Value retrieved: " + cachedValue);
                        }
                    } // implementing DUMP 
                } else if (request.equals("DUMP")) {
                StringBuilder dump = new StringBuilder();

    for (String keys : keyValueHashingMap.keySet()) {
                    String score = keyValueHashingMap.get(keys);
                    dump.append(keys).append(" = ").append(score).append("\n");
         }
                    outsxk.println(dump.toString());
                } else if (request.equals("EXIT")) {
                    outsxk.println("connection closed.");
                    break;
                } else {
                outsxk.println("Invalid statement.");
                }
            }
            // closing the connection
    inputsxk.close();
    outsxk.close();
    clientsxkSocket.close();
    serversxkSocket.close();       
        } catch (Exception e) {
            e.printStackTrace();
        }}
}
