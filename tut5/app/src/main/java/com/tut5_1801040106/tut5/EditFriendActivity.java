package com.tut5_1801040106.tut5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.tut5_1801040106.tut5.models.Friend;
import com.tut5_1801040106.tut5.models.FriendMangament;

public class EditFriendActivity extends AppCompatActivity {

    Friend friend ;

    FriendMangament friends = FriendMangament.getInstance();

    EditText inputName,inputEmail,inputPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);

        friend = (Friend) getIntent().getSerializableExtra("friend");

        int index = getIndex(friend);


        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);

        inputName.setText(friend.getName());
        inputEmail.setText(friend.getEmail());
        inputPhoneNumber.setText(friend.getPhoneNumber());



        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmEditFriend(index);
            }
        });

        ImageButton cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void editFriend(int index)
    {

        if(index != -1)
        {
            String name = inputName.getText().toString();
            String phoneNumber = inputPhoneNumber.getText().toString();
            String email = inputEmail.getText().toString();
            Friend f = new Friend(name,phoneNumber,email);
            friends.set(index,f);


        }
        else {
            Log.d("name", "fuck");
        }
    }

    public int getIndex(Friend friend)
    {
        for(int i=0;i< friends.size();i++)
        {
            Friend x = friends.get(i);
            if(x.getName().equalsIgnoreCase(friend.getName())
                    && x.getPhoneNumber().equalsIgnoreCase(friend.getPhoneNumber())
                    && x.getEmail().equalsIgnoreCase(friend.getEmail()))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void confirmEditFriend(int index)
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Edit Friend")
                .setMessage("Are you sure you want to edit this friend?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editFriend(index);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}