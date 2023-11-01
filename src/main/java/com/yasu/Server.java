package com.yasu;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        int port = 3456;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Sunucu çalışıyor...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("İstemci bağlandı: " + clientSocket.getInetAddress());

                // Dosya alımı işlemi
                //TODO Login işlemi yapılacak
                receiveFile(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(Socket clientSocket) {
        try {//TODO işlemler ayrılacak dışardan veri almaya elverişli hale getirilecek
            InputStream is = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String fileName = reader.readLine();
            long fileSize = Long.parseLong(reader.readLine());

            // Dosyanın kaydedileceği hedef klasör yolunu belirtin
            String targetDirectory = "C:\\Users\\yusuf\\OneDrive\\Masaüstü\\ftpS\\src\\main\\java\\com"; // Değiştirmeniz gereken yer

            // Dosya yolu
            String filePath = targetDirectory  + "ninna.mp3";

            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;

            System.out.println("Dosya alımı başlıyor: " + fileName);
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                if (totalBytesRead >= fileSize) {
                    break;
                }
            }

            System.out.println("Dosya alımı tamamlandı: " + fileName);
            fos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}