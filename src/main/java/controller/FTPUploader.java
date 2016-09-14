package controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Properties;

/**
 * Created by Matthew on 10.09.2016.
 */
public class FTPUploader {

static Integer port = 21;


    public void uploadFiles(Properties properties, String ftpFilesDirectory) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(properties.getProperty("ftpHost"), port);
            ftpClient.login(properties.getProperty("ftpLogin"), properties.getProperty("ftpPassword"));
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory("//" + properties.getProperty("ftpHost"));

            //ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File folder = new File(ftpFilesDirectory);
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    InputStream inputStream = new FileInputStream(file);
                    System.out.println("Start uploading file...");
                    boolean done = ftpClient.storeFile(file.getName(), inputStream);
                    inputStream.close();
                    if (done) {
                        System.out.println("The file is uploaded successfully.");
                    }else{
                        System.out.println(ftpClient.getStatus());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}