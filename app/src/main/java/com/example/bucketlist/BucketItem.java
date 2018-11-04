package com.example.bucketlist;

public class BucketItem {

    private String itemTitle;
    private Boolean isChecked;
    private static final boolean DEFAULT_CHECK = false;

    public BucketItem(String itemTitle) {
        this.itemTitle = itemTitle;
        this.isChecked = DEFAULT_CHECK;
    }

    public BucketItem(String itemTitle, Boolean isChecked) {
        this.itemTitle = itemTitle;
        this.isChecked = isChecked;

    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}
