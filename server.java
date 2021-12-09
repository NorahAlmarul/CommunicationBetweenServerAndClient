import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class server {
    static ServerSocket serverSocket;
    static int serverPort = 1342;
    static DataOutputStream toClient;//data that goes to client
    static BufferedReader fromClient;//data that came from client
    
    public static void main(String[] args) throws Exception {
        serverSocket = new ServerSocket(serverPort);//create socket for conncetions
        System.out.println("Server in passive mode");
        
        while (true) {//to keep server alive 
            Socket handShaking = serverSocket.accept();//accept connection with client
            //get data from handShaking socket to deliver it to client
            toClient = new DataOutputStream(handShaking.getOutputStream());
            //get data from client(handShaking socket)and store it in BufferedReader
            fromClient = new BufferedReader(new InputStreamReader(handShaking.getInputStream()));

            for (int i = 0; i < 3; i++) {
                String option = fromClient.readLine();//read option from client
                switch (option) {
                    case "1": {
                        System.out.println("open mode");
                        toClient.writeBytes(option + '\n');//to inform client that server is ready to get the message
                        String Msg = fromClient.readLine();//read message from client
                        System.out.println("Received message from client: " + Msg);
                        break;
                    }
                    case "2": {
                        System.out.println("secure mode");
                        toClient.writeBytes(encryptString(option) + '\n');//encrypt the message from server to client
                        String SecureMsg = fromClient.readLine();//message that came from client
                        System.out.println("Message has decrypted Successfully!");
                        System.out.println("the secure message that have been decrypted is: " + encryptString(decryptString(SecureMsg)));
                        break;
                    }
                    case "3": {
                        System.out.println("quit mode");
                        handShaking.close();//close socket 
                        break;
                    }
                    default: {//if client did not choose one of these numbers
                        System.out.println("error");
                        break;
                    }
                }
                System.out.println("---------------------------------------------------");
            }
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

    public static String decryptString(String Msg) {
        int key = 3;
        char[] chars = Msg.toCharArray();
        String decyptedMsg = "";
        for (char c : chars) {
            c -= key;//replace character by jumping three characters backward to decrypt it
            decyptedMsg += c;
        }
        return decyptedMsg;
    }
}
