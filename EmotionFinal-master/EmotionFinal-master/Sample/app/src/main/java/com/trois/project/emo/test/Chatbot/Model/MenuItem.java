package com.trois.project.emo.test.Chatbot.Model;

import android.view.View;

public class MenuItem {
    private final String name;//메뉴 이름
    private final int resource;//메뉴 아이콘
    private View.OnClickListener onClickListener;//클릭 리스너

    public MenuItem(String name, int resource, View.OnClickListener onClickListener) {
        this.name = name;
        this.resource = resource;
        this.onClickListener = onClickListener;
    }

    public MenuItem(String name, int resource) {
        this.name = name;
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public int getResource() {
        return resource;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
