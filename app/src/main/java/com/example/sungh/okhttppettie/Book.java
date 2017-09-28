package com.example.sungh.okhttppettie;

/**
 * Created by sungh on 2017/9/28.
 */

public class Book {
    private long key;
    private String title;
    private String author;
    private String price;
    private long time;
    // 這邊要做set get 有快速鍵 alt + insert
    // set 部分就是傳回去，目前沒做因為我們只有人家傳回來的值
    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
