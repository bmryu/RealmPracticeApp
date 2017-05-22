package org.adroidtown.practiceapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bomeeryu_c on 2017. 5. 22..
 */

public class FirebaseItem {
    public String content;
    public String path;

    public FirebaseItem(){

    }

    public FirebaseItem(String content, String path){
        this.content = content;
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("content",content);
        result.put("path",path);

        return result;
    }
}
