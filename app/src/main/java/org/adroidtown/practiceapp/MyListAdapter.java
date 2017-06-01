package org.adroidtown.practiceapp;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
/**
 * Created by bomeeryu_c on 2017. 5. 31..
 */

class MyListAdapter extends RealmBaseAdapter<Item> implements ListAdapter {

    public MyListAdapter(@Nullable OrderedRealmCollection<Item> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_firebase_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView) convertView.findViewById(R.id.recycleContent);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.recycleImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            final Item item = adapterData.get(position);
            viewHolder.content.setText(item.getContent());
            viewHolder.image.setImageURI(Uri.parse(item.getPath()));
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView content;
        ImageView image;
    }
}
