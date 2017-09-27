package com.gameloft.englishvocabulary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gameloft.englishvocabulary.controllers.TopicManager;
import com.gameloft.englishvocabulary.models.Topic;
import com.gameloft.englishvocabulary.models.TopicActivity;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    private boolean []selectTopic,selectTopicBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopicManager topicManager = TopicManager.getInstance();
        topicManager.load();
        selectTopic = new boolean[topicManager.getTopicName().length];
        selectTopicBackup = new boolean[selectTopic.length];
    }

    public void gotoTopics(View view){
        Intent intent = new Intent(MainActivity.this, TopicActivity.class);
        startActivity(intent);
    }

    private void showRating(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.rating);
        builder.setMessage("Do you like app?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton(R.string.not_now, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gotoTest(View view) {
        String[] testList = getResources().getStringArray(R.array.test_type);
        new AlertDialog.Builder(this)
                .setTitle(R.string.test)
                .setItems(testList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        showChooseTopic(i);
                    }

                })
                .create().show();
    }

    private void showChooseTopic(final int testType) {
        for (int i = 0; i < selectTopic.length; i++) {
            selectTopicBackup[i] = selectTopic[i];
        }
        TopicManager topicManager = TopicManager.getInstance();
        String[] topicNames = topicManager.getTopicName();
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.dialog_title_checkbox, null);
        TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle) ;
        final CheckBox cbAll = (CheckBox) layout.findViewById(R.id.cbSelectAll) ;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(layout);
        builder.setMultiChoiceItems(topicNames, selectTopic, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });
        builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTest(testType);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                for (int j = 0; j < selectTopic.length; j++) {
                    selectTopic[j] = selectTopicBackup[j];
                }
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = dialog.getListView();
                boolean isCheck = cbAll.isChecked();
                for(int i =0; i<listView.getCount();i++){
                    listView.setItemChecked(i, isCheck);
                    selectTopic[i] = isCheck;
                }
            }
        });
    }
    private void doTest(int testType){
        ArrayList<Vocabulary> list = new ArrayList<>();
        ArrayList<Topic> topicList = TopicManager.getInstance().getTopicList();
        for(int i = 0 ; i<selectTopic.length;i++){
            if(selectTopic[i]) {
                Topic topic = topicList.get(i);
                ArrayList<Vocabulary> vocabularies = TopicManager.getInstance().getVocabularyList(topic);
                list.addAll(vocabularies);
            }
        }

        if(list.size() == 0){
            makeText(this, "Please choose one topic!", Toast.LENGTH_SHORT).show();
        }
        else if(list.size() > 4){
            //set Toast namm on TOP
            Toast toast = Toast.makeText(this, "Please choose more topic!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0,100);
            toast.show();
        } else {

        }
    }
}