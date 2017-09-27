package com.gameloft.englishvocabulary.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gameloft.englishvocabulary.R;
import com.gameloft.englishvocabulary.adapter.LearnArrayAdapter;
import com.gameloft.englishvocabulary.controllers.TopicManager;

import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {

    private ListView listViewVocabulary;
    private LearnArrayAdapter mAdapter;
    private ArrayList<Vocabulary> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Intent intent = getIntent();
        Topic topic = (Topic) intent.getSerializableExtra(TopicActivity.KEY_PASS_DATA);
        mList = TopicManager.getInstance().getVocabularyList(topic);

        listViewVocabulary = (ListView) findViewById(R.id.lvVocabulary);
        mAdapter = new LearnArrayAdapter(this, R.layout.item_vocabulary_learn, mList);
        listViewVocabulary.setAdapter(mAdapter);

    }
}

