import Audio.AudioProcessor;
import Audio.RecogniseAudio;
import Database.DatabaseReference;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //user inputs file and name for reference
        String filePath = args[0];
        String videoName = args[1];

        int startTime = (int)System.currentTimeMillis();

        //intialize objects to be used
        AudioProcessor audioProcessor = new AudioProcessor();
        RecogniseAudio recogniseAudio = new RecogniseAudio();
        DatabaseReference databaseReference = new DatabaseReference();

        //Get paths to the cut up segments of original audio file
        ArrayList<String> audioFilePaths = audioProcessor.getAudioSegments(filePath);

        //Send segments to audio recognition API, receive a list of the songs it recognises
        Map<String, JSONObject> songList = recogniseAudio.getSongs(audioFilePaths);

        //Add the the returned song list to the database under the collection name the user initially inputs

        if (!songList.isEmpty()) {
            databaseReference.storeSongData(songList, videoName);
        } else {
            System.out.println("Didn't find any songs...");
        }

        int timeToProcessSeconds = ((int)System.currentTimeMillis() - startTime)/1000;
        System.out.println("Finished in " +   timeToProcessSeconds + " seconds..." );
    }
}