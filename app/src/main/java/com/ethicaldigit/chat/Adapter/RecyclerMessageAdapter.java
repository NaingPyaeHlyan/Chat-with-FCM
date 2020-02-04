package com.ethicaldigit.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ethicaldigit.chat.Model.MessageModel;
import com.ethicaldigit.chat.R;
import com.ethicaldigit.chat.Utils.Helpers;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerMessageAdapter extends RecyclerView.Adapter<RecyclerMessageAdapter.ViewHolder> {
    Context             context;
    List<MessageModel>  messageModelList;

    public RecyclerMessageAdapter(Context context, List<MessageModel> messageModelList) {
        this.context            = context;
        this.messageModelList   = messageModelList;
    }

    @NonNull
    @Override
    public RecyclerMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_message_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMessageAdapter.ViewHolder holder, int position) {
        final MessageModel messageModel = messageModelList.get(position);

        holder.txtMsgTitle.setText(messageModel.getText());
        holder.txtUserName.setText(messageModel.getName());
        // TODO Glide
        Helpers.setGlideImage(context, messageModel.getPhotoUrl(), holder.profileCircleImage);
        Helpers.setGlideImage(context, messageModel.getImageUrl(), holder.imgMsg);
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView  profileCircleImage;
        TextView         txtMsgTitle, txtUserName;
        ImageView        imgMsg;
        View             root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;

            profileCircleImage   = itemView.findViewById(R.id.circleFriendImage);
            txtMsgTitle          = itemView.findViewById(R.id.txtMessageTitle);
            txtUserName          = itemView.findViewById(R.id.txtMessageName);
            imgMsg               = itemView.findViewById(R.id.imgViewMessage);
        }
    }
}
