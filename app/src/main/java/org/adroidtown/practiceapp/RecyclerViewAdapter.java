package org.adroidtown.practiceapp;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by bomeeryu_c on 2017. 6. 1..
 */

public class RecyclerViewAdapter extends RealmRecyclerViewAdapter<Item, RecyclerViewAdapter.MyViewHolder> {


    public RecyclerViewAdapter(@Nullable OrderedRealmCollection<Item> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_firebase_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Item obj = getItem(position);
        holder.itemData = obj;
        //noinspection ConstantConditions
        holder.content.setText(obj.getContent());
       // holder.image.setImageURI(Uri.parse(obj.getPath()));

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycleContent)
        TextView content;
        @BindView(R.id.recycleImage)
        ImageView image;
        public Item itemData;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
