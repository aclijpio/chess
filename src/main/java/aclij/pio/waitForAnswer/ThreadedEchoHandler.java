package aclij.pio.waitForAnswer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable{

    private Socket incoming;

    @Override
    public void run() {
        try (InputStream inputStream = incoming.getInputStream();
            OutputStream outputStream = incoming.getOutputStream()){

            Scanner in = new Scanner(inputStream, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
