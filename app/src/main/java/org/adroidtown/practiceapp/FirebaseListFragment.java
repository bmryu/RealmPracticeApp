package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by bomeeryu_c on 2017. 5. 22..
 */

public class FirebaseListFragment extends Fragment implements View.OnClickListener{
    private DatabaseReference mDatabase;
    Button writeBtn;
    OnPostListener pListener;
    TextView textView;
    ImageView imageView;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
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
        final ListView listView = (ListView)rootView.findViewById(R.id.listView);

        button1 = (Button) rootView.findViewById(R.id.button1);
        button2 = (Button) rootView.findViewById(R.id.button2);
        button3 = (Button) rootView.findViewById(R.id.button3);
        button4 = (Button) rootView.findViewById(R.id.button4);
        button5 = (Button) rootView.findViewById(R.id.button5);
        button6 = (Button) rootView.findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        writeBtn = (Button)rootView.findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick();
            }
        });




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

        final TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("내글"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                if (position == 0) {
                  orderByValue();

                } else if (position == 1) {
                   orderByChild();
                    listView.setAdapter(firebaseListAdapter);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;

    }


    @Override
    public void onClick(View v) {
        setFalse();
        v.setSelected(true);
    }

    public void setFalse() {
        button1.setSelected(false);
        button2.setSelected(false);
        button3.setSelected(false);
        button4.setSelected(false);
        button5.setSelected(false);
        button6.setSelected(false);
    }

    public void orderByValue(){
            mDatabase.orderByKey();
    }
    public void orderByChild(){
        firebaseListAdapter.cleanup();
        mDatabase.orderByChild("content");
        this.firebaseListAdapter.notifyDataSetChanged();
    }


}
