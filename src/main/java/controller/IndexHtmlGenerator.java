package controller;

import java.io.*;
import java.util.List;

public class IndexHtmlGenerator {

    public void generate(List<String> songList, String ftpFilesDirectory, String host) throws IOException {
        String title = "Music Holder by Matthew";
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ftpFilesDirectory + "\\" + "index.html"), "UTF-8"));
        writer.write("<!DOCTYPE html>");
        writer.write("<head>");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("<link rel=\"shortcut icon\" type=\"image/png\" href=\"/guitar.png\"/>\n");
        writer.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<link rel=\"stylesheet\" href=\"http://www.w3schools.com/lib/w3.css\">\n" +
                "<link rel=\"stylesheet\" href=\"http://www.w3schools.com/lib/w3-theme-black.css\">\n" +
                "<link rel=\"stylesheet\" href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css\">");
        writer.write("<header class=\"w3-container w3-theme w3-padding\" id=\"myHeader\">\n" +
                "  <div class=\"w3-center\">\n" +
                "  </br><h1 class=\"w3-xxxlarge w3-animate-bottom\">" + title + "</h1></br></br>\n" +
                "  </div>");
        writer.write("<title>" + title + "</title>");
        writer.write("</head>");
        writer.write("<body>");writer.write("</header>");
        generateLinks(songList, writer, host);
        writer.write("</body>");
        writer.write("</html>");
        writer.close();
    }

    private void generateLinks(List<String> songList, Writer writer, String host) throws IOException {
        for (String song : songList) {
            writer.write("</br><a href=\"" + songTitleToFileName(song) + ".html\">" + song + "</a>");
        }
    }

    protected String songTitleToFileName(String song) {
        song = song.replaceAll("ę","e");
        song = song.replaceAll("ż","z");
        song = song.replaceAll("ó","o");
        song = song.replaceAll("ł","l");
        song = song.replaceAll("ć","c");
        song = song.replaceAll("ś","s");
        song = song.replaceAll("ź","z");
        song = song.replaceAll("ń","n");
        song = song.replaceAll("ą","a");
        song = song.replaceAll("Ę","E");
        song = song.replaceAll("Ż","Z");
        song = song.replaceAll("Ó","O");
        song = song.replaceAll("Ł","L");
        song = song.replaceAll("Ć","C");
        song = song.replaceAll("Ś","S");
        song = song.replaceAll("Ź","Z");
        song = song.replaceAll("Ń","N");
        song = song.replaceAll("Ą","A");
        return song.replaceAll(" ", "");
    }
}
