package com.xl.kuxingtx.activity.readNote;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import com.longsh.optionframelibrary.OptionBottomDialog;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.activity.myInfo.MyInfoActivity;
import com.xl.kuxingtx.inter.ReadNoteMvp;
import com.xl.kuxingtx.utils.FileUtilcll;

import com.xl.kuxingtx.utils.RealPathFromUriUtils;
import com.xl.kuxingtx.utils.CodeUtils;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xvideo.XVideo;
import com.xuexiang.xvideo.model.MediaRecorderConfig;
import com.zzhoujay.richtext.RichText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;



import java.io.File;
import java.util.ArrayList;
import java.util.List;


import jp.wasabeef.richeditor.RichEditor;



/**
 * 打开键盘会遮挡界面、需要处理、
 * */
@ContentView(R.layout.activity_read_note)
public class ReadNoteActivity extends AppCompatActivity implements View.OnClickListener, ReadNoteMvp.View {
    private ReadNoteMvp.Presenter readNotePresenter = new ReadNotePresenter(this);

    @ViewInject(R.id.titleBar)
    private TitleBar titleBar;
    @ViewInject(R.id.content_editor)
    private RichEditor content_editor;
    @ViewInject(R.id.add_img)
    private RadiusImageView add_img;
    @ViewInject(R.id.complete)
    private TextView complete;

    //用于临时保存content_editor中的text、
    private String content_text = "";

    //用于标识本次编辑是什么操作、
    private int code = -1;

    //用于标识当前编辑的Note是哪一篇、
    private int nowPosition = -1;

    //用于防止没有界面没有获得焦点、点击添加图片时、隐藏软键盘报错、
    private boolean isHavePoint = false;


    public static final int RC_CHOOSE_PHOTO = 1;
    public static final int RC_TAKE_PHOTO = 2;
    public static final int RC_PHOTORESOULT = 3;
    public static final String IMAGE_UNSPECIFIED = "image/*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_read_note);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        x.view().inject(this);

        //在第一次调用RichText之前先调用RichText.initCacheDir()方法设置缓存目录，不设置会报错
        RichText.initCacheDir(this);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", -1);

        switch (code){
            case CodeUtils.IS_UPDATE_NOTE:
                String note_content_str = intent.getStringExtra("note_content_str");
                this.content_text = note_content_str;
                nowPosition = intent.getIntExtra("position", -1);
                break;
            case CodeUtils.IS_NEW_NOTE:
                break;
            case CodeUtils.IS_NEW_TRENDS:
                break;
        }

        //updateRichText(trends_content_str);

        //初始化富文本编辑框、
        content_editor.setEditorHeight(300);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
        }

        content_editor.setHtml(content_text);
        initListener();
    }

    private void initListener(){
        add_img.setOnClickListener(this);
        complete.setOnClickListener(this);
        content_editor. setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                // Do Something
                content_text = text;
                isHavePoint = true;
            }
        });
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadNoteActivity.this.finish();
            }
        });
    }



    private void choosePhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }


    private void takePhoto() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
        startActivityForResult(intent2, RC_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RC_CHOOSE_PHOTO:
                    //这里选择了相册照片、并得到了地址、
                    if (data != null) {
                        String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                        //可以获得图片地址、
                        this.content_text += "<img src=\"" + realPathFromUri + "\" style=\" max-width:100%\"/>";
                        this.content_editor.setHtml(content_text);
                    } else {
                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RC_TAKE_PHOTO:
                    //这里拍摄了照片、下一步进行裁剪、
                    File picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
                    startPhotoZoom(Uri.fromFile(picture));
                    break;
                case RC_PHOTORESOULT:
                    //这里裁剪完毕、并得到了地址、
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        //图片路径
                        String realPathFromUri = FileUtilcll.saveFile(this, "temphead.jpg", photo);
                        this.content_text += "<img src=\"" + realPathFromUri + "\" style=\" max-width:100%\"/>";
                        this.content_editor.setHtml(content_text);
                    }
                    break;
            }
        }
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RC_PHOTORESOULT);
    }

    @Override
    public void onClick(View v) {
        List<String> stringList = new ArrayList<String>();
        switch (v.getId()){
            case R.id.add_img:

                //正确
                hideKeyboard(ReadNoteActivity.this);
                stringList.add("拍照");
                stringList.add("从相册选择");
                final OptionBottomDialog addImgDialog = new OptionBottomDialog(ReadNoteActivity.this, stringList);
                addImgDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                takePhoto();
                                break;
                            case 1:
                                choosePhoto();
                                break;
                        }
                        addImgDialog.dismiss();
                    }
                });
                break;
            case R.id.complete:
                hideKeyboard(ReadNoteActivity.this);
                stringList.add("发表为动态");
                stringList.add("保存为随笔");
                final OptionBottomDialog completeDialog = new OptionBottomDialog(ReadNoteActivity.this, stringList);
                completeDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                //发表为动态、
                                readNotePresenter.uploadTrends(content_text);
                                Toast.makeText(ReadNoteActivity.this, "上传中、请耐心等待、", Toast.LENGTH_SHORT);
                                break;
                            case 1:
                                //保存为随笔、
                                //判断是随笔的修改还是随笔的新建、
                                switch (ReadNoteActivity.this.code){
                                    case CodeUtils.IS_NEW_NOTE:
                                        readNotePresenter.saveNote(content_text);
                                        break;
                                    case CodeUtils.IS_UPDATE_NOTE:
                                        readNotePresenter.saveNote(content_text, nowPosition);
                                        break;
                                }

                                break;
                        }
                        completeDialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public void uploadTrendsSuccess() {
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void saveNoteSuccess() {
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    /**
     * 开始录制视频
     * @param requestCode 请求码
     */
    //@Permission({PermissionConsts.CAMERA, PermissionConsts.STORAGE})
    public void startVideoRecorder(int requestCode) {
        MediaRecorderConfig mediaRecorderConfig = MediaRecorderConfig.newInstance();
        XVideo.startVideoRecorder(this, mediaRecorderConfig, requestCode);
    }


    //判断软键盘情况、并隐藏、
    public void hideKeyboard(Activity activity) {
        //防止没有焦点、出现异常、
        if(!isHavePoint){
            return;
        }
        //隐藏软键盘逻辑、
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
