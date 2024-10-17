import java.io.*;
import java.nio.ByteBuffer;
import java.net.Socket;

public class Client {
    public Client() {
    }

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 1342);
        File file = new File("test.txt");
        long length = file.length();

        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
        buf.putLong(length);
        out.write(buf.array());

        byte bytes[] = new byte[4096];
        int count;
        while ((count = in.read(bytes)) != -1){
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();

        System.out.println("Файл отправлен");

    }
}
