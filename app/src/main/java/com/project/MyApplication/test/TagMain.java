package com.project.MyApplication.test;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TagMain implements CustomTabEntity {
    private String mTabTitle;
    private int mTabSelectedIcon;
    private int mTabUnselectedIcon;

    public TagMain(String mTabTitle, int mTabSelectedIcon, int mTabUnselectedIcon) {
        this.mTabTitle = mTabTitle;
        this.mTabSelectedIcon = mTabSelectedIcon;
        this.mTabUnselectedIcon = mTabUnselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return mTabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return mTabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return mTabUnselectedIcon;
    }
}
