import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        int port = 1342;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started");

        try {
            Socket input = server.accept();
            System.out.println("Client connected");

            InputStream in = input.getInputStream();
            OutputStream out = input.getOutputStream();

            byte[] bytes = new byte[8];
            in.read(bytes);
            long fileLength = ByteBuffer.wrap(bytes).getLong();
            System.out.println("Receiving file of length: " + fileLength + " bytes");


            File file = new File("givefile.txt");
            FileOutputStream fileout = new FileOutputStream(file);

            byte[] buffer = new byte[4096];
            int byteread;
            long totalread = 0;

            if((byteread = in.read(buffer)) != -1 && totalread < fileLength){
                fileout.write(buffer, 0, byteread);
                totalread += byteread;
            }

            System.out.println("File received successfully. ");

            fileout.close();
            input.close();
            System.out.println("Client disconnected");
        } catch (Exception var7) {
            Exception e = var7;
            e.printStackTrace();
        } finally {
            server.close();
        }

    }
}