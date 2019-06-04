package com.xl.kuxingtx;

import android.os.Environment;

import com.xl.kuxingtx.fragment.Note.NoteBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String userName = "";
    private String password = "";
    private int id = -1;
    private int records = 200;
    private int treature = 200;
    private boolean isLogined = false;
    private List<NoteBean> noteBeans = new ArrayList<NoteBean>();
    private String location = "";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<NoteBean> getNoteBeans() {
        //如果还没加载本地随笔数据、先调用loadNoteBeans()进行加载、
        if(this.noteBeans.size() == 0)
            loadNoteBeans();
        return noteBeans;
    }

    public void setNoteBeans(List<NoteBean> noteBeans) {
        this.noteBeans = noteBeans;
    }

    public List<NoteBean> addNoteBean(NoteBean noteBean){
        this.noteBeans.add(noteBean);
        return this.noteBeans;
    }

    public void updateNoteBean(String content, int position){
        this.noteBeans.get(position).setContent(content);
    }

    public void loadNoteBeans(){
        ObjectInputStream ois=null;
        UserInfo userInfo = UserInfo.getUserInfo();
        String path = userInfo.getPath();
        try {
            //获取输入流
            ois = new ObjectInputStream(new FileInputStream(new File(path)));
            //获取文件中的数据
            this.noteBeans = (List<NoteBean>) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (ois!=null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<NoteBean> delNoteBean(){

        return this.noteBeans;
    }

    public String getPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ id +"KXTXnote.txt";
    }

    private static UserInfo userInfo = new UserInfo();

    private UserInfo(){}

    public static UserInfo getUserInfo(){
        return userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id){ this.id = id; }

    public int getId(){ return id; }

    public void setRecords(int records){ this.records = records; }

    public int getRecords(){ return records; }

    public void setTreasure(int treature){ this.treature = treature; }

    public int getTreasure(){ return treature; }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

}
