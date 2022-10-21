package com.example.group3_project.Router.Game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;

public class UserRankAdapter extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<User> userList;
    String username;

    public UserRankAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> userList,String username) {
        super(context, resource, userList);
        this.context = context;
        this.layoutItem = resource;
        this.userList = userList;
        this.username = username;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutItem,null);

        TextView userDisplayName = convertView.findViewById(R.id.tvUserDisplayName);
        TextView userRank = convertView.findViewById(R.id.tvUserRank);
        TextView userDiamond = convertView.findViewById(R.id.tvUserDiamond);
        ImageView userAvatar = convertView.findViewById(R.id.ivUserAvatar);
        LinearLayout llUserRankItem = convertView.findViewById(R.id.llUserRankItem);
        CardView cvDiamondItemWrapper = convertView.findViewById(R.id.cvDiamondItemWrapper);

        User currentUser = userList.get(position);

//        if(username.equals(currentUser.getUsername())){
//           llUserRankItem.setBackgroundColor(Color.rgb(230,230,250));
//            Drawable layoutDrawable = llUserRankItem.getBackground();
//            layoutDrawable = DrawableCompat.wrap(layoutDrawable);
//            DrawableCompat.setTint(layoutDrawable, Color.WHITE);
//            llUserRankItem.setBackground(layoutDrawable);
//        }

        userDisplayName.setText(currentUser.getDisplayName());
        userRank.setText("Top: " + (position + 1));
        userAvatar.setImageResource(Utils.getDrawableResourceIdFromFileName(context, currentUser.getAvatar()));
        userDiamond.setText(String.valueOf(currentUser.getDiamond()));

        return convertView;
    }

}
