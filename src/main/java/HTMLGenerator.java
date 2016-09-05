import java.io.*;

public class HTMLGenerator {
    private static String inputTextPath = "";
    private static String title = "";
    public static void main(String[] args){

        try {
            inputTextPath = args[0];
            generateHtml();
        }catch(Exception e){
            System.out.println("W argumencie podaj ścieżkę do pliku");
        }
    }

    private static void generateHtml() throws IOException {
        System.out.println(inputTextPath);
        title = inputTextPath.substring(inputTextPath.lastIndexOf("\\")+1, inputTextPath.length()-4);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputTextPath.substring(0,inputTextPath.length()-3) + "html"), "UTF-8"));
        writer.write("<!DOCTYPE html>");
        writer.write("<head>");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("<title>"+title+"</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>"+title+"</h1>");
        generateTheRest(writer);
        writer.write("</body>");
        writer.write("</html>");
        writer.close();
    }

    private static void generateTheRest(Writer writer) {
        File file = new File(inputTextPath);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(file), "UTF8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            writeToHtml(writer, bufferedReader);
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

    private static void writeToHtml(Writer writer, BufferedReader bufferedReader) throws IOException {
        String line = "";
        String previousLine = "";
        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains("iframe")){
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
