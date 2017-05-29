package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;
import static org.adroidtown.practiceapp.R.id.listView;

/**
 * Created by bomeeryu_c on 2017. 5. 22..
 */

public class FirebaseListFragment extends Fragment{
    private DatabaseReference mDatabase;
    Button writeBtn;
    OnPostListener pListener;
    TextView textView;
    ImageView imageView;
    FirebaseRecyclerAdapter<FirebaseItem, PostViewHolder> mPostAdapter;

    FirebaseListAdapter firebaseListAdapter;
    public interface   OnPostListener {
        void onClick();
    }

    public void setOnPostListener (OnPostListener pListener){
        this.pListener = pListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_firebase_listview, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://practiceapp-ce6dc.firebaseio.com/post");
       // final ListView listView = (ListView)rootView.findViewById(R.id.listView);
        RecyclerView mPostRV = (RecyclerView)rootView.findViewById(listView);
        mPostRV.setLayoutManager(new LinearLayoutManager(getContext()));
        setupAdapter();
        mPostRV.setAdapter(mPostAdapter);
        writeBtn = (Button)rootView.findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick();
            }
        });
        final TabLayout tabs = (TabLayout)rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("내글"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                if (position == 0) {
                    Log.d("MainActivity", "선택된 탭 : " + position + "if position == 0");
                    orderByChild();
                } else if (position == 1) {
                    orderByValue();
                    Log.d("MainActivity", "선택된 탭 : " + position + "if position == 1");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


/*
       firebaseListAdapter = new FirebaseListAdapter(
                getActivity(),
             Object.class,
                R.layout.list_firebase_item,
                mDatabase
        ) {
            @Override
            protected void populateView(View v, Object model, int position) {
                textView = (TextView)v.findViewById(R.id.name);
              //  imageView = (ImageView)v.findViewById(R.id.imageView);

                HashMap<String, Object> data = (HashMap) model;
                String dataText = data.get("content").toString();
                String dataImagePath = data.get("path").toString();
                textView.setText(dataText);
             //   Glide.with(getActivity()).load(Uri.parse(dataImagePath)).override(100,100).into(imageView);
            }
        };

        listView.setAdapter(firebaseListAdapter);


*/


        return rootView;

    }

    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<FirebaseItem, PostViewHolder> (
                FirebaseItem.class,
                R.layout.list_firebase_item,
                PostViewHolder.class,
                mDatabase
        ) {

            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, FirebaseItem model, int position) {
//                viewHolder.imageView.setImageURI(Uri.parse(model.getPath()));
                viewHolder.textView.setText(model.getContent());
            }

        };
    }


    public void orderByValue(){

    }
    public void orderByChild(){
//        firebaseListAdapter.cleanup();
        mDatabase.orderByChild("post");
//        this.firebaseListAdapter.notifyDataSetChanged();
    }


    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public PostViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.recycleContent);
            imageView = (ImageView)itemView.findViewById(R.id.recycleImage);
        }


    }
}
