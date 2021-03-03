package com.tut5_1801040106.tut5.models;

import java.util.ArrayList;
import java.util.List;

public class FriendMangament {

    private List<Friend> friendList = new ArrayList<>();
    private static FriendMangament instance = null;

    private FriendMangament (){
    }

    public static FriendMangament getInstance(){
        if(instance == null)
        {
            instance = new FriendMangament();
            instance.add(new Friend(" huy1", "012345", "quanghuy@gmail.com"));
            instance.add(new Friend("2", "012345", "quanghuy@gmail.com"));
            instance.add(new Friend("3", "012345", "quanghuy@gmail.com"));
            instance.add(new Friend("4", "012345", "quanghuy@gmail.com"));
            instance.add(new Friend("quang5", "012345", "quanghuy@gmail.com"));
        }
        return instance;
    }

    public void add(Friend friend)
    {
        this.friendList.add(friend);
    }

    public void remove(Friend friend)
    {
        this.friendList.remove(friend);
    }

    public Friend get(int position)
    {
        return this.friendList.get(position);
    }

    public int size(){
        return this.friendList.size();
    }

    public List<Friend> getList()
    {
        return friendList;
    }

    public void set (int i, Friend friend)
    {
        this.friendList.set(i,friend);
    }
}
