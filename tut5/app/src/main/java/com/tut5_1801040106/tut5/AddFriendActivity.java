package com.tut5_1801040106.tut5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tut5_1801040106.tut5.models.Friend;
import com.tut5_1801040106.tut5.models.FriendMangament;

public class AddFriendActivity extends AppCompatActivity {

    FriendMangament friends = FriendMangament.getInstance();
    EditText inputName,inputEmail,inputPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);

        ImageButton plusBtn = findViewById(R.id.plusBtn);
        ImageButton cancelBtn = findViewById(R.id.cancelBtn);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusFriend(v);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
    }


    public void plusFriend(View view)
    {
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();
        Log.d("name", name);
        Friend friend = new Friend(name,email,phoneNumber);
        Log.d("string", friend.toString());

        confirmAddFriend(friend);

    }

    public void cancel(View view)
    {
        onBackPressed();

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

    public void confirmAddFriend(Friend friend)
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Add Friend")
                .setMessage("Are you sure add " + friend.getName() + " ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        friends.add(friend);
                        announceAddSuccessfully();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    public void announceAddSuccessfully()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Add Successfully!!!")
                .setMessage(" Do you want to continue")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputName.setText("");
                        inputEmail.setText("");
                        inputPhoneNumber.setText("");

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }


}