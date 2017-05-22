package org.adroidtown.practiceapp;

/**
 * Created by bomeeryu_c on 2017. 5. 10..
 */

public class ListItem {

    int resID;
    String name;
    String content;
    Integer index;
    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIndex(){return index;}

    public ListItem(int resID, String name, String content, Integer i){
        this.resID = resID;
        this.name = name;
        this.content = content;
        this.index = i;
    }



}
