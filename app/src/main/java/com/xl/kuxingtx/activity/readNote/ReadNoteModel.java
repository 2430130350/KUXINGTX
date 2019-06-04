package com.xl.kuxingtx.activity.readNote;

import android.util.Log;

import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.TrendsBean;
import com.xl.kuxingtx.fragment.Note.NoteBean;
import com.xl.kuxingtx.inter.ReadNoteMvp;

import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadNoteModel implements ReadNoteMvp.Model {
    private ReadNoteMvp.Presenter readNotePresenter;
    public ReadNoteModel(ReadNoteMvp.Presenter readNotePresenter){
        this.readNotePresenter = readNotePresenter;
    }

    @Override
    public void uploadTrendsPost(TrendsBean trendsBean) {

/*        POST{
            "uid": String uid;
            "date": String date;
            "content": String html;
            "imgNumber": int numebr;
            "img1": 图片1;
            "img2": 图片2;
        }*/
/*        JSONObject json = new JSONObject();
        try {
            json.put("uid", trendsBean.getUid());
            json.put("date", trendsBean.getmTime());
            json.put("imgNumber", 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //构建RequestParams对象，传入请求的服务器地址URL
        RequestParams params = new RequestParams(MyApplication.webUri_upload_trends_post);

        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("uid", trendsBean.getUid()));
        list.add(new KeyValue("date", trendsBean.getDateString()));
        list.add(new KeyValue("content", trendsBean.getContent()));
        list.add(new KeyValue("imgNumber", trendsBean.getImgNumber()));
        for(int i = 0; i<trendsBean.getImgNumber(); i++){
            //将文章url对应图片、按顺序上传、
            list.add(new KeyValue("img" + i, new File(trendsBean.getImgUrl().get(i))));
        }
        //list.add(new KeyValue("img1", new File(filePah)));

        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                readNotePresenter.uploadTrendsSuccess();
            }

            @Override
            public void onFinished() {
                //上传完成

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //取消上传
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //上传失败

            }
        });
    }

    @Override
    public void saveNote() {
        ObjectOutputStream fos=null;
        UserInfo userInfo = UserInfo.getUserInfo();
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

        readNotePresenter.saveNoteSuccess();

    }
}
