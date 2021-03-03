package com.tut5_1801040106.tut5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tut5_1801040106.tut5.models.Friend;
import com.tut5_1801040106.tut5.models.FriendMangament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    List<Friend> friends = new ArrayList<>();

    public FriendAdapter(List<Friend> friends)
    {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_friend,parent,false);


        return new FriendHolder(itemView,context);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.bind(friend);

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendHolder extends  RecyclerView.ViewHolder{

        private TextView tvName;
        private ImageButton chatBtn,emailBtn,callBtn,editBtn,deleteBtn;
        private Context context;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvName = itemView.findViewById(R.id.tvName);


            chatBtn = itemView.findViewById(R.id.chatBtn);
            emailBtn = itemView.findViewById(R.id.emailBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);
            callBtn = itemView.findViewById(R.id.callBtn);

        }

        public void bind(Friend friend)
        {
            tvName.setText(friend.getName());

            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+ friend.getPhoneNumber()));
                    context.startActivity(intent);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmDelete(friend);

                }
            });

            emailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String uriText =
                            "mailto:" + friend.getEmail()+
                                    "?subject=" + Uri.encode("some subject text here") +
                                    "&body=" + Uri.encode("some text here");

                    Uri uri = Uri.parse(uriText);

                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                    sendIntent.setData(uri);
                    context.startActivity(Intent.createChooser(sendIntent, "Send email"));
                }
            });

            chatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("smsto:"+friend.getPhoneNumber());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "The SMS text");
                    context.startActivity(intent);
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,EditFriendActivity.class);
                    intent.putExtra("friend",  friend);

                    context.startActivity(intent);
                }
            });
        }


        public void confirmDelete(Friend friend)
        {
            new AlertDialog.Builder(context)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Delete Friend")
                    .setMessage("Are you sure delete friend " + friend.getName())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            friends.remove(friend);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }


}
