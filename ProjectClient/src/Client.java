import views.LoggingScene;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client  {




    public static void main(String[] args) {


        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
            Socket socket = new Socket("127.0.0.1", 9500);

//            BufferedReader reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter writer =  new PrintWriter( socket.getOutputStream(),true);


            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());



            LoggingScene.reader = reader;
            LoggingScene.writer = writer;
            LoggingScene.main(args);


            reader.close();
            writer.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}
