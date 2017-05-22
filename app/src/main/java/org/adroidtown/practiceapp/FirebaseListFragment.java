package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by bomeeryu_c on 2017. 5. 22..
 */

public class FirebaseListFragment extends Fragment implements View.OnClickListener{
    private DatabaseReference mDatabase;
    Button writeBtn;
    OnPostListener pListener;
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
        ListView listView = (ListView)rootView.findViewById(R.id.listView);
        writeBtn = (Button)rootView.findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick();
            }
        });

        FirebaseListAdapter firebaseListAdapter = new FirebaseListAdapter(
                getActivity(),
                FirebaseItem.class,
                R.layout.list_firebase_item,
                mDatabase
        ) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView textView = (TextView)v.findViewById(R.id.name);
                ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
            //    textView.setText(model);

            }
        };
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(firebaseListAdapter);


        return rootView;

    }


    @Override
    public void onClick(View v) {

    }

}
