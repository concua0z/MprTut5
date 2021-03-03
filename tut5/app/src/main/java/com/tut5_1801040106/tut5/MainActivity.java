package com.tut5_1801040106.tut5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.tut5_1801040106.tut5.models.Friend;
import com.tut5_1801040106.tut5.models.FriendMangament;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {





    FriendMangament listFriends = FriendMangament.getInstance();
    private RecyclerView recyclerView;
    EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = findViewById(R.id.recycleFriends);

        query = findViewById(R.id.searchBar);


        FriendAdapter friendAdapter = new FriendAdapter(listFriends.getList());
        setAdapter(friendAdapter);


        query.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            String keywords = query.getText().toString();
                            getSearchFriends(keywords);
                            ImageButton turnBack = findViewById(R.id.turnBackBtn);
                            turnBack.setVisibility(View.VISIBLE);
                            turnBack.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    query.setText("");
                                    FriendAdapter friendAdapter = new FriendAdapter(listFriends.getList());
                                    setAdapter(friendAdapter);
                                    turnBack.setVisibility(View.INVISIBLE);
                                }
                            });
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        ImageButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend(v);

            }
        });
    }

    public void addFriend(View view)
    {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
    }

    public void setAdapter(RecyclerView.Adapter adapter)
    {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getSearchFriends(String keywords)
    {
        if(keywords.length() >0 || keywords != null)
        {
            List<Friend> searchList = searchFriends(keywords);
            FriendAdapter friendAdapter = new FriendAdapter(searchList);
            setAdapter(friendAdapter);
        }

    }

    public List<Friend> searchFriends(String keywords)
    {
        List<Friend> query = new ArrayList<>();
        for(Friend x : listFriends.getList())
        {
            if(x.getName().contains(keywords) || x.getPhoneNumber().contains(keywords))
            {
                query.add(x);
            }
        }

        return query;
    }
}