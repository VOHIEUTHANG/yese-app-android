package com.example.group3_project.Router.Notify;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

public class NotiAdapter extends ArrayAdapter {
    Context context;
    List<Notify> notifyList;
    int layout_item;

    public NotiAdapter(@NonNull Context context, int resource, @NonNull List<Notify> notifyList) {
        super(context, resource, notifyList);
        this.context = context;
        this.notifyList = notifyList;
        this.layout_item = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layout_item, null);

        ImageView packageIcon = convertView.findViewById(R.id.ivPackageIcon);
        ImageView readStatusIcon = convertView.findViewById(R.id.tvNotifyIsReaded);
        TextView title = convertView.findViewById(R.id.tvNotifyTitle);
        TextView content = convertView.findViewById(R.id.tvNotifyContent);
        TextView dateCreate = convertView.findViewById(R.id.tvNotifyDateCreate);

        Notify notify = notifyList.get(position);

        if (notify != null) {
            String packageIconResourceName = getPackgeById(notify.getFk_packageID());
            packageIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(context, packageIconResourceName));
            title.setText(notify.getTitle());
            content.setText(notify.getContent());
            dateCreate.setText(notify.getCreateAt());

            if(notify.getIsReaded() == 1){
                title.setTextColor(Color.rgb(150,150,150));
                content.setTextColor(Color.rgb(200,200,200));
            }else{
                readStatusIcon.setImageResource(R.drawable.ic_visibility);
            }

        }

        return convertView;
    }

    public String getPackgeById(int id) {
        return AppDatabase.getInstance(context).packageDao().getPackageIconById(id);
    }

    public Notify getItem(int position){
        return notifyList.get(position);
    }


}
