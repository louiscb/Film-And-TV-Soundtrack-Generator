package Audio;

import Audio.Upload.FileUpload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class RecogniseAudio {
    private FileUpload upload;
    private String lastSongID;

    public RecogniseAudio() {
        upload = new FileUpload();
    }

    public Map<String, JSONObject> getSongs(ArrayList<String> audioFilePaths) {
        Map<String, JSONObject> songs = new HashMap<>();

        System.out.println("Querying audio recognition API...");

        audioFilePaths.forEach(path -> {
            JSONObject song = getSongFromAPI(path);

            if (song != null) {
                //Need to change path to time in song
                songs.put(getTimeFromPath(path), song);
                System.out.println("Found song: " + song.toString());
            }
        });

        System.out.println("Found " + songs.size() + " songs in video");

        return songs;
    }

    private String getTimeFromPath(String path) {
        int lastSlash = path.lastIndexOf("/");
        return path.substring(lastSlash+1, path.length()-4);
    }

    private JSONObject getSongFromAPI(String path) {
        String responseFromAPI = upload.getAPIResponse(path);

        if (responseFromAPI.contains("{\"matches\":[]}") || responseFromAPI.isEmpty())
            return null;

        JSONObject song = new JSONObject(responseFromAPI);

        String id = song.getJSONArray("matches").getJSONObject(0).get("trackId").toString();

        if (id.equals(lastSongID))
            return null;

        lastSongID = id;
        return song;
    }
}
