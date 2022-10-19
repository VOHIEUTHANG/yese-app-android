package com.example.group3_project.Router.Note;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.R;
import com.example.group3_project.Router.Note.Activities.Note_update_vocap;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VocabAdapter extends ArrayAdapter {

    Context context;
    int layoutItem;
    List<Vocab> vocabs;

    public VocabAdapter(@NonNull Context context, int resource, @NonNull List<Vocab> vocabs) {
        super(context, resource, vocabs);
        this.context = context;
        this.layoutItem = resource;
        this.vocabs = vocabs;
    }

    public void setData(List<Vocab> vocabs){
        this.vocabs = vocabs;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        TextView tvVocab = convertView.findViewById(R.id.tvWord);
        TextView tvWordType = convertView.findViewById(R.id.tvWordType);
        TextView tvMeans = convertView.findViewById(R.id.tvMeans);
        TextView tvSynonymWords = convertView.findViewById(R.id.tvSynonymWords);
        TextView tvExample = convertView.findViewById(R.id.tvExample);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        LinearLayout llUpdate = convertView.findViewById(R.id.llUpdate);
        LinearLayout llDelete = convertView.findViewById(R.id.llDelete);

        Vocab vocab = vocabs.get(position);

        if (vocab != null) {
            tvVocab.setText(renderNullValue(vocab.getWords()));
            tvWordType.setText(renderNullValue(vocab.getType()));
            tvMeans.setText(renderNullValue(vocab.getMeans()));
            tvSynonymWords.setText(renderNullValue(vocab.getSynonymWords()));
            tvExample.setText(renderNullValue(vocab.getExample()));

            btnUpdate.setOnClickListener(item -> {
                Intent intent = new Intent(context, Note_update_vocap.class);
                Bundle extras = new Bundle();
                extras.putSerializable("vocab", vocab);
                intent.putExtras(extras);
                context.startActivity(intent);
            });

            btnDelete.setOnClickListener(item -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete " + vocab.getWords());
                builder.setMessage("confirm permanent deletion of this word !")
                        .setPositiveButton("Delete", (dialogInterface, i) -> {
                            AppDatabase.getInstance(context).vocabDao().deleteOneVocab(vocab);
                            vocabs.remove(vocab);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
            });

            if (position % 2 == 1) {
                tvVocab.setBackgroundColor(Color.rgb(225, 240, 250));
                tvWordType.setBackgroundColor(Color.rgb(225, 240, 250));
                tvMeans.setBackgroundColor(Color.rgb(225, 240, 250));
                tvSynonymWords.setBackgroundColor(Color.rgb(225, 240, 250));
                tvExample.setBackgroundColor(Color.rgb(225, 240, 250));
                llUpdate.setBackgroundColor(Color.rgb(225, 240, 250));
                llDelete.setBackgroundColor(Color.rgb(225, 240, 250));
            }

        }

        return convertView;
    }

    public String renderNullValue(String value) {
        return value == null ? "..." : value;
    }

}
