package controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//TODO
// 1. Wczytuje np. song.txt z folderu songsDirectory
// 2. Generuje song.html
// 3. Zapisuje nazwę song aby dodać ją do linków w index.html
// 4. 1. 2. i 3. wszystkie pliki z songsDirectory
// 5. Generuje index.html
public class Main {
    private static String host = "";
    private static String login = "";
    private static String password = "";
    private static String songsDirectory = "";
    private static String ftpFilesDirectory = "";
    private static Integer port = 21;
    private static Integer iframeWidth;
    private static Integer iframeHeight;


    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        loadProperties(properties);
        HTMLGenerator htmlGenerator= new HTMLGenerator();
        IndexHtmlGenerator indexHtmlGenerator = new IndexHtmlGenerator();
        FTPUploader ftpUploader = new FTPUploader();
        List<String> songList = new ArrayList<String>();
        File folder = new File(songsDirectory);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
                htmlGenerator.generate(file.getAbsolutePath(), ftpFilesDirectory, iframeWidth, iframeHeight);
                songList.add(file.getName().substring(0, file.getName().length() - ".txt".length()));
            }
        }
        indexHtmlGenerator.generate(songList,ftpFilesDirectory, host);
        ftpUploader.uploadFiles(properties, ftpFilesDirectory);
    }

    private static void loadProperties(Properties properties) {
        try {
            properties.load(FTPUploader.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        host = properties.getProperty("ftpHost");
        login = properties.getProperty("ftpLogin");
        password = properties.getProperty("ftpPassword");
        songsDirectory = properties.getProperty("songsDirectory");
        ftpFilesDirectory = properties.getProperty("ftpFilesDirectory");
        iframeWidth = Integer.parseInt(properties.getProperty("iframeWidth"));
        iframeHeight = Integer.parseInt(properties.getProperty("iframeHeight"));
    }
}
