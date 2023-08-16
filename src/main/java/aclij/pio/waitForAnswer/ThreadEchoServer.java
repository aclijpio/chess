package aclij.pio.waitForAnswer;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadEchoServer {
    ServerSocket serverSocket;

    public ThreadEchoServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void open() throws IOException {
        try (ServerSocket openedserverSocket = this.serverSocket){
            while (true){
                Socket incoming = openedserverSocket.accept();

            }
        }
    }

}
