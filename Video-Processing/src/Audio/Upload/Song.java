package Audio.Upload;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.json.JSONObject;

public class Song {
    public DBObject getSong(String time, JSONObject obj)  {
        String artist = obj.getJSONArray("matches").getJSONObject(0).getJSONObject("metadata").get("artist").toString();
        String title = obj.getJSONArray("matches").getJSONObject(0).getJSONObject("metadata").get("title").toString();
        DBObject song = new BasicDBObject("timeInVideo", time).append("artist", artist).append("title", title);
        return song;
    }
}
