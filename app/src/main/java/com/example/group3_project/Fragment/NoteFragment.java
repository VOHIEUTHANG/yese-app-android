package com.example.group3_project.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Router.Note.Activities.Note_add_new_vocab;
import com.example.group3_project.Router.Note.VocabAdapter;
import com.example.group3_project.SessionManagement;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    Button btnAddVocab;
    ListView lvVocabList;
    List<Vocab> vocabList = new ArrayList<>();
    VocabAdapter vocabAdapter;
    String username;
    EditText edSearch;

    String[] sortTypeList = {"Default", "A-Z", "Z->A", "Create time desc", "Create time asc"};
    List<String> tagList = new ArrayList<>();
    AutoCompleteTextView autoCompleteSortType, actvFilterSelect;
    ArrayAdapter<String> adapterSortTypeList, adapterTagList;

    public NoteFragment() {
    }

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getCurrentUserLogin();
//        initTagList();
        renderListView();

        btnAddVocab.setOnClickListener(item -> {
            Intent intent = new Intent(requireContext(), Note_add_new_vocab.class);
            startActivity(intent);
        });

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchVal = String.valueOf(editable).trim();
                if (searchVal.length() > 0) {
                    getVocabBySearchCondition(searchVal);
                } else {
                    getAllVocabByUsername();
                }
                vocabAdapter.notifyDataSetChanged();
            }
        });


//       render select sort type
//        adapterSortTypeList = new ArrayAdapter<String>(requireContext(), R.layout.note_layout_option_item, sortTypeList);
//        adapterTagList = new ArrayAdapter<String>(requireContext(), R.layout.note_layout_option_item, tagList);
//
//        autoCompleteSortType.setAdapter(adapterSortTypeList);
//        actvFilterSelect.setAdapter(adapterTagList);
//
//        autoCompleteSortType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(requireContext(), "You select item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        actvFilterSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(requireContext(), "You select item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void getVocabBySearchCondition(String searchVal) {
        try {
            if (username != null) {
                vocabList.clear();
                vocabList.addAll(AppDatabase.getInstance(requireContext()).vocabDao().filterWordBySubString(username,searchVal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void renderListView() {
        getAllVocabByUsername();
        vocabAdapter = new VocabAdapter(requireContext(), R.layout.note_layout_word_item, vocabList);
        lvVocabList.setAdapter(vocabAdapter);
    }

    public void initTagList() {
        tagList.add("all");
        tagList.add("none");
        List<String> tags = AppDatabase.getInstance(requireContext()).vocabDao().getTagListByUsername(username);
        tagList.addAll(tags);
    }

    public void getAllVocabByUsername() {
        try {
            if (username != null) {
                vocabList.clear();
                vocabList.addAll(AppDatabase.getInstance(requireContext()).vocabDao().getAllVocabByUsername(username));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Get vocabs list by username failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void setControl() {
        btnAddVocab = getView().findViewById(R.id.btnAddWord);
        lvVocabList = getView().findViewById(R.id.lvVocabList);
//        autoCompleteSortType = getView().findViewById(R.id.actvSortSelect);
//        actvFilterSelect = getView().findViewById(R.id.actvFilterSelect);
        edSearch = getView().findViewById(R.id.edSearch);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllVocabByUsername();
        vocabAdapter.notifyDataSetChanged();
    }

    public void getCurrentUserLogin() {
        username = Utils.getUsername(requireContext());
    }
}