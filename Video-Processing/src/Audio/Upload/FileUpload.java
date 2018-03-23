package Audio.Upload;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUpload {
    private URL url;
    private URLConnection conn;

    public String getAPIResponse(String path) {
        Path filePath = new File(path).toPath();
        StringBuilder song = new StringBuilder();

        //Inefficient way to do it!
        try {
            url = new URL("https://beta-amp.shazam.com/partner/recognise");
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setAllowUserInteraction(false);
            conn.addRequestProperty("X-Shazam-Api-Key", "04ccd01c-df3c-4da8-b805-8b9286080a32");
            conn.addRequestProperty("Content-Type", "application/octet-stream");

            byte[] content = Files.readAllBytes(filePath);

            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.write(content);
            outputStream.close();

            InputStream in = conn.getInputStream();
            InputStream bufferedInputStream = new BufferedInputStream(in);
            bufferedInputStream.mark(content.length);

            int x;
            while ((x = bufferedInputStream.read()) != -1) {
                char c = (char)x;
                song.append(c);
            }

            bufferedInputStream.close();
            return song.toString();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return null;
    }
}
