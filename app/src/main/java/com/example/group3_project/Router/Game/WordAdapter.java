package com.example.group3_project.Router.Game;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group3_project.R;
import com.example.group3_project.Router.Game.Words.Word;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder>{
    private ArrayList<Word> wordList;
    LinearLayoutManager linearLayoutManager;
    private int selectedPosition = -1;
    private Context context;
    OnWordButtonClickListener listener;

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_layout_word_button, parent,false);
        return new WordViewHolder(itemView);
    }

    public WordAdapter(LinearLayoutManager linearLayoutManager,Context context, OnWordButtonClickListener listener ){
        this.linearLayoutManager = linearLayoutManager;
        this.listener = listener;
        this.context = context;
    }

    public void setData(ArrayList<Word> wordList){
        this.wordList = wordList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = wordList.get(position);
        if(word == null ) return ;

        holder.btnWord.setText(word.getMean());
        holder.btnWord.setTextOff(word.getMean());
        holder.btnWord.setTextOn(word.getMean());
        holder.btnWord.setTag(word.getKey());


        holder.btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleButton myButton = (ToggleButton) view;

                if(selectedPosition != -1 && holder.getLayoutPosition() != selectedPosition){
                    ToggleButton checkedButton = (ToggleButton) linearLayoutManager.findViewByPosition(selectedPosition);
                    unCheckButton(checkedButton);
                }

                selectedPosition = holder.getLayoutPosition();
                listener.onButtonClick((int) holder.btnWord.getTag());

                if(myButton.isChecked()){
                    myButton.setTextColor(Color.rgb(255,255,255));
                }else{
                    myButton.setTextColor(Color.rgb(50,50,50));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList != null ? wordList.size() : 0;
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder{
        ToggleButton btnWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            btnWord = itemView.findViewById(R.id.btnWordItem);
        }
    }

    public void hiddenSelectedItem(){
        ToggleButton checkedButton = (ToggleButton) linearLayoutManager.findViewByPosition(selectedPosition);
        checkedButton.startAnimation(AnimationUtils.loadAnimation( context, R.anim.fade_down));
        checkedButton.setVisibility(View.INVISIBLE);
    }

    public void showWrongAnswer(){
        ToggleButton checkedButton = (ToggleButton) linearLayoutManager.findViewByPosition(selectedPosition);
        unCheckButton(checkedButton);
    }

    public void unCheckButton(ToggleButton btn){
        btn.setChecked(false);
        btn.setTextColor(Color.rgb(50,50,50));
    }

    public void showAllElements(){
        for(int i = 0;i<wordList.size();i++){
            ToggleButton checkedButton = (ToggleButton) linearLayoutManager.findViewByPosition(i);
            checkedButton.setVisibility(View.VISIBLE);
            unCheckButton(checkedButton);
        }

    }

}
