package com.example.sungh.okhttppettie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        new RunWrok().start();
    }

    class RunWrok extends Thread
    {
        // 可以連看看人跡煞怎麼包的 Json
        String path_json ="http://android0620-1348.appspot.com/query";
        String result_json = null;

        /* This program downloads a URL and print its contents as a string.*/
        OkHttpClient client = new OkHttpClient();
        // get 方法 參考 http://square.github.io/okhttp/
        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        Runnable task = new Runnable()
        {
            @Override
            public void run() {
                //使用 gson 解析 json 資料
                Gson gson = new Gson();
                Book[] books = gson.fromJson(result_json,Book[].class);

                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                for(Book book :books){
                    sb
                            .append("書名:").append(book.getTitle()).append(" ")
                            .append("作者:").append(book.getAuthor()).append(" ")
                            .append("價錢:").append(book.getPrice()).append("\n\n");
                }
                textView.setText(sb);

                for(Book book :books){
                    sb2
                            .append("Key:").append(book.getKey()).append("\n");
                }
                textView2.setText(sb2);

            }
        };

        @Override
        public void run()
        {
            try {
                //1.抓資料
                result_json = run(path_json);
                //2.改變畫面內容只能用主執行緒(Android機制)
                runOnUiThread(task);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
