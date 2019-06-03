package com.xl.kuxingtx.activity.readNote;

import android.Manifest;
import android.app.Activity;
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

import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.xl.kuxingtx.R;
import com.xl.kuxingtx.utils.FileUtilcll;

import com.xl.kuxingtx.utils.RealPathFromUriUtils;

import com.zzhoujay.richtext.RichText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;



import java.io.File;


import jp.wasabeef.richeditor.RichEditor;


/**
 * 打开键盘会遮挡界面、需要处理、
 * */
@ContentView(R.layout.activity_read_note)
public class ReadNoteActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.content_editor)
    private RichEditor content_editor;

    String[] mPermissionList = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

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
        String note_content_str = intent.getStringExtra("note_content_str");
        //updateRichText(trends_content_str);

        //初始化富文本编辑框、
        content_editor.setEditorHeight(300);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
        }

        content_editor.setHtml(note_content_str);
    }




    private void choosePhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }

    //这个api不能使用、
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
                    if (data != null) {
                        String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                        //可以获得图片地址、
                    } else {
                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RC_TAKE_PHOTO:
                    //设置文件保存路径这里放在跟目录下
                    File picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
                    startPhotoZoom(Uri.fromFile(picture));
                    break;
                case RC_PHOTORESOULT:
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        //图片路径
                        String urlpath = FileUtilcll.saveFile(this, "temphead.jpg", photo);

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
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RC_PHOTORESOULT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_img:
                choosePhoto();
                break;
        }
    }

   /* public void updateRichText(final String content){
        note_content.post(new Runnable() {
            @Override
            public void run() {
                showEditData("<h3>测试富文本、</h3><img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559473454823&di=aeb63602b58f630754528c005b78f133&imgtype=0&src=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz_png%2F7CrlBDLFl4YLrIVhtEZZu2mVefODWXMibq4bUrZV22aGZy6aU3OxQWX6UvicMX2CShG6WicdIK9BEeMj4gibtfyc2w%2F640%3Fwx_fmt%3Dpng\" />" +
                        "<img src=\"http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg\"/>中国官方星期六宣布，国家有关部门决定对美国联邦快递涉嫌损害我国用户合法权益的问题立案调查。据华为日前向媒体披露，联邦快递最近将该公司从日本发往中国的两个邮件送到了美国，另外两个从越南发往香港和新加坡的邮件被在中途滞留，终极地也被联邦快递改为了美国。\n" +
                        "\n" +
                        "　　尽管联邦快递随后声称这些都是偶然的错误，但是舆论的大量分析质疑联邦快递这样做是受到了美国政府的指使。因为上述四件包裹在几天时间里先后被错误地改为发往美国，实在太蹊跷了。这样的出错率与联邦快递通常的服务水平大相径庭。\n" +
                        "\n" +
                        "　　更重要的是，美国政府正动用国家力量对华为开展致力于让其关门的全面打压，而四件包裹错投的终极地都是美国，这加重了人们对联邦快递这样做是受到美国政府操纵的怀疑。");
            }
        });
    }

    protected void showEditData(String content) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）

        note_content.clearAllLayout();
        List<String> textList = StringUtils.cutStringByImgTag(content);
        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            if (text.contains("<img")) {
                String imagePath = StringUtils.getImgSrc(text);

                note_content.measure(0,0);
                Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, width, height);
                if (bitmap != null){
                    note_content.addImageViewAtIndex(note_content.getLastIndex(), bitmap, imagePath);
                } else {
                    note_content.addEditTextAtIndex(note_content.getLastIndex(), text);
                }
                note_content.addEditTextAtIndex(note_content.getLastIndex(), text);
            }
        }

    }*/
}
