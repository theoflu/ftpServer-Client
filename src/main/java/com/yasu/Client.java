package com.yasu;

import java.io.*;

import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 3456;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            OutputStream os = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);

            // Dosya adını ve boyutunu gönder
            String fileName = "C:\\Users\\yusuf\\OneDrive\\Masaüstü\\ftpS\\src\\main\\java\\com\\yasu\\deneme.txt";
            File file = new File(fileName);
            long fileSize = file.length();
            writer.println(fileName);
            writer.println(fileSize);

            // Dosyayı gönder
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            System.out.println("Dosya gönderildi: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
