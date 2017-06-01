package org.adroidtown.practiceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

public class RealmListFragment extends Fragment {
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
    ItemList itemList;
    
    public interface OnPostListener{
        void onClick();
    }
    public void setOnPostListener(OnPostListener pListener){
        this.pListener = pListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_firebase_listview, container, false);
        Realm.init(getContext());
        realm = Realm.getDefaultInstance(); //realm 데이터베이스를 가져온다
        ButterKnife.bind(this, rootView);
        setUpTabs();
        realm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                Item item = realm.createObject(Item.class);
                item.setContent("보미보미~~");
                item.setPath("--");
                itemList.getItemRealmList().add(item);
            }
        });

        realm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                Item item = realm.createObject(Item.class);
                item.setContent("리사이클 테스트용 1");
                item.setPath("--");
                itemList.getItemRealmList().add(item);
            }
        });


        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pListener.onClick();
            }
        });
        setUpRecyclerView();
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    private void setUpRecyclerView() {
        adapter = new RecyclerViewAdapter(realm.where(ItemList.class).findFirst().getItemRealmList()); //지금 null임 겟아이템림리스트를 할 오브젝트들을 만들어줘야함
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

                } else if (position == 1) {

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