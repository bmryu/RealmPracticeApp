package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by bomeeryu_c on 2017. 5. 22..
 */

public class RealmListFragment extends BaseFragment {
    private Realm realm;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    @BindView(R.id.listView)
    RecyclerView listRV;
    @BindView(R.id.writeBtn)
    Button writeBtn;
    @BindView(R.id.tabs)
    TabLayout tabs;
    OnPostListener pListener;
    public interface OnPostListener{
        void onClick();
    }
    public void setOnPostListener(OnPostListener pListener){
        this.pListener = pListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        progressON(getContext());
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_firebase_listview, container, false);
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this, rootView);
        setUpTabs();
        setUpRecyclerView();
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick();
            }
        });
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setUpRecyclerView() {
        adapter = new RecyclerViewAdapter(realm.where(Item.class).findAll());
        listRV.setLayoutManager(new LinearLayoutManager(getContext()));
        listRV.setAdapter(adapter);
        listRV.setHasFixedSize(true);

    }
    private void setUpTabs(){
        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("새글"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    adapter = new RecyclerViewAdapter(realm.where(Item.class).findAll());
                    listRV.setAdapter(null);
                    listRV.setAdapter(adapter);
                } else if (position == 1) {
                    adapter = new RecyclerViewAdapter(realm.where(Item.class).findAllSorted("content"));
                    listRV.setAdapter(null);
                    listRV.setAdapter(adapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}