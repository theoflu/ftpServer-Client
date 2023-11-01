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
            String fileName = "C:\\Users\\yusuf\\OneDrive\\Masaüstü\\ftpS\\src\\main\\java\\com\\yasu\\ninna.mp3";
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
            Listele();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Listele(){
        String directoryPath = "C:\\Users\\yusuf\\OneDrive\\Masaüstü\\ftpS\\src\\main\\java"; // Klasör yolunu değiştirin
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                System.out.println("Klasördeki Dosyalar:");
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Klasör boş.");
            }
        } else {
            System.out.println("Belirtilen klasör bulunamadı veya bir klasör değil.");
        }
    }
    }
