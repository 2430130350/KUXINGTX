package com.xl.kuxingtx.fragment.Note;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FNoteMvp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class NoteModel implements FNoteMvp.Model {
    private FNoteMvp.Presenter notePresenter;
    public NoteModel(FNoteMvp.Presenter notePresenter){
        this.notePresenter = notePresenter;
    }

    @Override
    public void delNoteBeanToFile(int position) {
        UserInfo userInfo = UserInfo.getUserInfo();
        userInfo.delNoteBean(position);
        ObjectOutputStream fos=null;

        String path = userInfo.getPath();
        List<NoteBean> noteBeans = userInfo.getNoteBeans();

        try {

            //如果文件不存在就创建文件
            File file=new File(path);
            //file.createNewFile();
            //获取输出流
            //这里如果文件不存在会创建文件，这是写文件和读文件不同的地方
            fos = new ObjectOutputStream(new FileOutputStream(file));

            //这里不能再用普通的write的方法了
            //要使用writeObject
            fos.writeObject(noteBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
            }

        }

        notePresenter.delNoteBeanSuccess();
    }
}
