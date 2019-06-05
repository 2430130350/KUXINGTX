package com.xl.kuxingtx;

import android.os.Environment;

import com.xl.kuxingtx.fragment.Around.TrendsBean;
import com.xl.kuxingtx.fragment.Note.NoteBean;
import com.xuexiang.xvideo.XVideo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    /**
     * 初始化xvideo的存放路径
     */
    public  void initVideo() {
        XVideo.setVideoCachePath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ id +"KXTXvideo/");
        // 初始化拍摄
        XVideo.initialize(false, null);
    }

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
        sortNote(noteBeans);
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

    private void sortNote(List<NoteBean> noteBeans){

        Collections.sort(noteBeans, new Comparator<NoteBean>(){
            @Override
            public int compare(NoteBean arg0, NoteBean arg1) {
                int mark = 1;
                Date date0 = arg0.getmTime();
                Date date1 = arg1.getmTime();
                if(date0.getTime() > date1.getTime()){
                    mark =  -1;
                }
                if(arg0.getmTime() == arg1.getmTime()){
                    mark =  0;
                }
                return mark;
            } //compare
        });

    }

}
