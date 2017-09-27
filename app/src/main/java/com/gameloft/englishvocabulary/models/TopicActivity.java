package com.gameloft.englishvocabulary.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.gameloft.englishvocabulary.R;
import com.gameloft.englishvocabulary.controllers.TopicManager;

import java.util.ArrayList;

public class TopicActivity extends AppCompatActivity {
    private GridView gridViewTopic;
    public static final String KEY_PASS_DATA = "key_pass_data";
    private ArrayAdapter<String> adapter;
    private String [] mTopicName;
    final String[] topic = {"Topic 1", "Topic 2", "Topic 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        gridViewTopic = (GridView) findViewById(R.id.gridviewTopic);
        mTopicName = TopicManager.getInstance().getTopicName();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, topic);

        gridViewTopic.setAdapter(adapter);

        gridViewTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Topic> topicArrayList = TopicManager.getInstance().getTopicList();
                Topic topic = topicArrayList.get(i);
                Intent intent = new Intent(TopicActivity.this, LearnActivity.class);
                intent.putExtra(KEY_PASS_DATA, topic);
                startActivity(intent);
            }
        });

    }
}
