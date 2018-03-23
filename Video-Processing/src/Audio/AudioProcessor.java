package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AudioProcessor {
    private static final int segmentLength = 20;
    private static final int audioLength = 5;

    public ArrayList<String> getAudioSegments(String path) {
        ArrayList<String> audioFilePaths = null;

        try {
            System.out.println("Splitting audio into segments...");
            audioFilePaths = splitAudio(path);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return audioFilePaths;
    }

    private static ArrayList<String> splitAudio(String path) throws UnsupportedAudioFileException, IOException {
        File file = new File(path);
        String name = file.getName();

        String folderPath = "audio/" + name.substring(0, name.length()-4);
        Boolean isFolderCreated = new File(folderPath).mkdirs();

        if (!isFolderCreated) {
            FileNotFoundException e = new FileNotFoundException();
            System.out.println("COULD NOT CREATE FOLDER");
            throw e;
        }

        int durationInSeconds = getFileLength(file);

        ArrayList<String> audioFilePaths = new ArrayList<>();

        for (int i = 0; i < durationInSeconds-segmentLength; i+= segmentLength) {
            //each segment file name will be time in seconds at which it occurs in original video file
            String newFilePath = folderPath + "/" + i + ".wav";
            createSegment(file, newFilePath, i, audioLength);
            audioFilePaths.add(newFilePath);
        }

        return audioFilePaths;
    }

    private static void createSegment(File file, String newFilePath, int startingPoint, int length) throws IOException, UnsupportedAudioFileException {
        AudioInputStream inputStream;
        AudioInputStream outputStream;

        inputStream = AudioSystem.getAudioInputStream(file);
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        AudioFormat format = fileFormat.getFormat();

        int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
        inputStream.skip(startingPoint * bytesPerSecond);
        long audioToCopy = length * (int)format.getFrameRate();

        outputStream = new AudioInputStream(inputStream, format, audioToCopy);
        File outputFile = new File(newFilePath);

        AudioSystem.write(outputStream, fileFormat.getType() , outputFile);
    }

    private static int getFileLength(File file) throws IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = null;
        audioInputStream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        int durationInSeconds = (int)frames / (int)format.getFrameRate();
        return  durationInSeconds;
    }
}
