package org.adroidtown.practiceapp;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by bomeeryu_c on 2017. 6. 1..
 */

public class ItemList extends RealmObject{
    private RealmList<Item> itemRealmList;

    public RealmList<Item> getItemRealmList() {
        return itemRealmList;
    }
}
