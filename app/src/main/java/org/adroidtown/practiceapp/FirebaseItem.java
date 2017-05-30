package org.adroidtown.practiceapp;

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

}
