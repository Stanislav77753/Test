package main.java.com.popovich.model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Service {
    private static File mailProperties = new File("src/main/resources/mail.properties");
    private static final int PORT = 10000;
    private static Socket socket;
    public static void main(String[] args) throws IOException, MessagingException {
        try(ServerSocket server = new ServerSocket(PORT)){
            while(true){
                socket = server.accept();
                try(InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream()){
                    byte[] buf = new byte[1024];
                    int readBytes = in.read(buf);
                    String command = new String (buf, 0, readBytes);

                }
            }
        }

        final Properties properties = new Properties();
        properties.load(new FileInputStream(mailProperties));
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("popovichtestsocket@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("maikydyk@mail.ru"));
        message.setSubject("hello");
        message.setText("Hi! bla bla bla");

        Transport tr = mailSession.getTransport();
        tr.connect(null, "TestJava");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
}
