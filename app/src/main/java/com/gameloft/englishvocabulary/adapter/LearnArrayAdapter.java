package com.gameloft.englishvocabulary.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gameloft.englishvocabulary.R;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;

import static com.gameloft.englishvocabulary.R.id.btnSound;
import static com.gameloft.englishvocabulary.R.id.tvMean;
import static com.gameloft.englishvocabulary.R.id.tvWord;

/**
 * Created by Admin on 27/9/2017.
 */

public class LearnArrayAdapter extends ArrayAdapter<Vocabulary> {

    //để khỏi tạo 1 arrayadapter cần context, layout va list
    private Context mContext;
    private int mLayout;
    private ArrayList<Vocabulary> mList;

    //tạo hàm dựng cho adapter

    public LearnArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Vocabulary> list) {
        super(context, resource, list);
        mContext = context;
        mLayout = resource;
        mList = list;
    }

    private class ViewHolder {
        private TextView tvWord;
        private TextView tvPro;
        private TextView tvMean;
        private Button btnSound;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            System.out.print("Create new view");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvWord = (TextView) convertView.findViewById(tvWord);
            viewHolder.tvPro = (TextView) convertView.findViewById(R.id.tvPronunciation);
            viewHolder.tvMean = (TextView) convertView.findViewById(tvMean);
            viewHolder.btnSound = (Button) convertView.findViewById(btnSound);

            convertView.setTag(viewHolder);

        } else {
            System.out.print("Reuse View");
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Vocabulary vocabulary = mList.get(position);
        viewHolder.tvWord.setText(vocabulary.getName());
        viewHolder.tvPro.setText(vocabulary.getPronunciation());
        viewHolder.tvMean.setText(vocabulary.getMean());

        viewHolder.btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }

//taị vị trí position , set convertview vao parent

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if(convertView == null){
//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            convertView = inflater.inflate(mLayout, parent,false);
//
//            TextView tvWord = (TextView) convertView.findViewById(R.id.tvWord);
//            TextView tvPro = (TextView) convertView.findViewById(R.id.tvPronunciation);
//            TextView tvMean = (TextView) convertView.findViewById(R.id.tvMean);
//            Button btnSound = (Button) convertView.findViewById(R.id.btnSound);
//
//
//            Vocabulary vocabulary = mList.get(position);
//            tvWord.setText(vocabulary.getName());
//            tvPro.setText(vocabulary.getPronunciation());
//            tvMean.setText(vocabulary.getMean());
//
//            btnSound.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
//
//        }
//        return convertView;
//    }

//CACH TÁI SỬ DỤNG VIEW


}
