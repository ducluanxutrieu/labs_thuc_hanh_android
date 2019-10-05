package com.uit.unit2_recyclerview.gridview_spinner;

import com.uit.unit2_recyclerview.R;

public enum Thumbnail {
    Thumbnail1("Thumbnail 1", R.mipmap.first_thumbnail),
    Thumbnail2("Thumbnail 2", R.mipmap.second_thumbnail),
    Thumbnail3("Thumbnail 3", R.mipmap.third_thumbnail),
    Thumbnail4("Thumbnail 4", R.mipmap.fourth_thumbnail);

    private String name;
    private int img;

    Thumbnail(String name, int img){
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
