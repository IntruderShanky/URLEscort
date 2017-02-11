package com.islabs.linkpreviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.islabs.urlescort.LinkSourceContent;
import com.islabs.urlescort.LinkViewCallback;
import com.islabs.urlescort.TextCrawler;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LinkViewCallback {

    List<String> urls;
    ImageView imageView;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_view);
        TextCrawler textCrawler = new TextCrawler();
        textCrawler.makePreview(this, "https://github.com/IntruderShanky/Squint");
    }

    public void change(View v) {
        if (current >= urls.size())
            current = 0;
        Glide.with(MainActivity.this).load(urls.get(current)).centerCrop().into(imageView);
        current++;
    }

    @Override
    public void onBeforeLoading() {

    }

    @Override
    public void onAfterLoading(LinkSourceContent linkSourceContent, boolean isNull) {
        urls = linkSourceContent.getImages();
        System.out.println(linkSourceContent.getImages());
        if (urls.size() > 0)
            Glide.with(MainActivity.this).load(urls.get(0)).fitCenter().into(imageView);
    }

}
