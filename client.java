import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class client {
    static String Servrer_IP = "127.0.0.1";
    static BufferedReader fromserver;//data that came from server
    static DataOutputStream toserver;//data that goes to server
    static Socket clientsocket;

    public static void main(String[] args) throws Exception {
        try {
            clientsocket = new Socket(Servrer_IP, 1342);//creat client socket
            System.out.println("Connecting to Server IP: " + Servrer_IP);
            toserver = new DataOutputStream(clientsocket.getOutputStream());
            fromserver = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

            for (int i = 0; i < 3; i++){//enter more than one message in same connection
                System.out.println("Choose one of these options:\n1-open mode \n2-Secure mode \n3-Quit application");
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();

                switch (option){
                    case "1":{//open mode
                        toserver.writeBytes(option+ '\n');//to send the option to server
                        String ready = fromserver.readLine();//wating server respoonse
                        if (ready.equalsIgnoreCase("1")){//to ensure server in ready mode
                            System.out.println("Open mode started. please enter your message.");
                            String Msg = scanner.next();
                            toserver.writeBytes(Msg + '\n');//to dilever the message to server
                            System.out.println("Send massage to server successfully.");
                        }
                        break;
                    }
                    case "2":{//secure mode
                        toserver.writeBytes(option+ '\n');//to send the option to server
                        String readysecure = fromserver.readLine();//wating server respoonse
                        if (readysecure.equalsIgnoreCase(encryptString(option))){//to ensure server in secure mode
                            System.out.println("Secure mode started. please enter your message.");
                            String plainMsg = scanner.next();
                            toserver.writeBytes(encryptString(plainMsg) + '\n');//to dilever the encrypted message to server
                            System.out.println("Send massage to Server Successfully.");
                        }
                        break;
                    }
                    case "3":{//quit mode
                        toserver.writeBytes(option+ '\n');//to send the option to server
                        clientsocket.close();//close socket
                        break;
                    }
                    default:
                        System.err.print("error");
                        break;
                }
                System.out.println("---------------------------------------------------");
            }
        } 
        catch (Exception ex){//if server is dowen
            System.err.println("Server Connection Error.");
        }
    }

    public static String encryptString(String Msg) {
        int key = 3;
        char[] chars = Msg.toCharArray();
        String encyptedMsg = "";
        for (char c : chars) {
            c += key;//replace character by jumping three characters forward
            encyptedMsg += c;
        }
        return encyptedMsg;
    }
}
