package com.example.identification20.mycomponent;

import android.provider.ContactsContract;
import android.widget.ImageView;

import java.util.List;

public class SpaceData {
    private String userName;
    private String userSay;
    private ContactsContract.Data time;
    private ImageView head;
    private List<Comments> friends;


}
class Comments{
    private String comName;
    private String comments;

    public Comments(String comName, String comments){
        this.comName = comName;
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public String getComName() {
        return comName;
    }
}
