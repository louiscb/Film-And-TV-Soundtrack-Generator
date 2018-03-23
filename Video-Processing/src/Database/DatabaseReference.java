package Database;

import Audio.Upload.Song;
import com.mongodb.*;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.Map;

public class DatabaseReference {
    private DBCollection collection;

    public void storeSongData(Map<String, JSONObject> songList, String name) {
        try {
            MongoClient mongoClient = new MongoClient();

            DB database = mongoClient.getDB("java-test");
            collection = database.getCollection(name);

            System.out.println("Adding songs to database collection " + collection.getName() + "...");

            songList.forEach(this::addToDatabase);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void addToDatabase(String s, JSONObject o) {
        Song emptySong = new Song();
        DBObject song = emptySong.getSong(s, o);

       // DBObject song = new BasicDBObject("Time", s).append("info", o);
        collection.insert(song);
    }
}