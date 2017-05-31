package org.adroidtown.practiceapp;

import io.realm.RealmObject;

/**
 * Created by bomeeryu_c on 2017. 5. 31..
 */

public class Item extends RealmObject {
    public String content;
    public String path;

    public Item(){

    }

    public Item(String content, String path){
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

