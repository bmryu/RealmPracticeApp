package org.adroidtown.practiceapp;

import android.support.annotation.Nullable;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by bomeeryu_c on 2017. 5. 31..
 */

public class MyListAdapter extends RealmBaseAdapter<Item> implements ListAdapter {

    public MyListAdapter(@Nullable OrderedRealmCollection<Item> data) {
        super(data);
    }

}
