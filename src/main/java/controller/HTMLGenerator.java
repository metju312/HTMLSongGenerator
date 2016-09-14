package controller;

import java.io.*;

//TODO
//jedno załączenie wszystkie piosenki nowe/nowe i stare
//double click na ikonkę generatora i wsyzstko się robi

public class HTMLGenerator {
    private static String title = "";
    public void generate(String path, String ftpFilesDirectory, Integer iframeWidth, Integer iframeHeight){
        try {
            generateHtml(path,  ftpFilesDirectory, iframeWidth, iframeHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void generateHtml(String path, String ftpFilesDirectory, Integer iframeWidth, Integer iframeHeight) throws IOException {
        System.out.println(path);
        title = path.substring(path.lastIndexOf("\\")+1, path.length()-4);
        String titleWithoutWhite = titleToFileName(title);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ftpFilesDirectory + "\\" + titleWithoutWhite + ".html"), "UTF-8"));
        writer.write("<!DOCTYPE html>");
        writer.write("<head>");
        writer.write("<title>" + title + "</title>");
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
        writer.write("<body>");
        writer.write("<a href=\"" + "/" + "\">" + "Home" + "</a></br>");
        writer.write("</header>");
        generateTheRest(writer, path, iframeWidth, iframeHeight);
        writer.write("</body>");
        writer.write("</html>");
        writer.close();
    }

    protected String titleToFileName(String title) {
        title = title.replaceAll("ę","e");
        title = title.replaceAll("ż","z");
        title = title.replaceAll("ó","o");
        title = title.replaceAll("ł","l");
        title = title.replaceAll("ć","c");
        title = title.replaceAll("ś","s");
        title = title.replaceAll("ź","z");
        title = title.replaceAll("ń","n");
        title = title.replaceAll("ą","a");
        title = title.replaceAll("Ę","E");
        title = title.replaceAll("Ż","Z");
        title = title.replaceAll("Ó","O");
        title = title.replaceAll("Ł","L");
        title = title.replaceAll("Ć","C");
        title = title.replaceAll("Ś","S");
        title = title.replaceAll("Ź","Z");
        title = title.replaceAll("Ń","N");
        title = title.replaceAll("Ą","A");
        return title.replaceAll(" ", "");
    }

    private void generateTheRest(Writer writer, String path, Integer iframeWidth, Integer iframeHeight) {
        File file = new File(path);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            writeToHtml(writer, bufferedReader, iframeWidth, iframeHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private void writeToHtml(Writer writer, BufferedReader bufferedReader, Integer iframeWidth, Integer iframeHeight) throws IOException {
        String line = "";
        String previousLine = "";
        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains("iframe")){
                line = line.replaceAll("width=\"[0-9]+\"", "width=\"" + iframeWidth + "\"");
                line = line.replaceAll("height=\"[0-9]+\"", "height=\"" + iframeHeight + "\"");
                writer.write(line);
            }else if(line.contains("http") ){
                previousLine = line;
                line = bufferedReader.readLine();
                writer.write("</br><a href=\""+previousLine+"\">"+line+"</a>");
            }else{
                writer.write("</br>" + line);
            }
        }
    }
}
