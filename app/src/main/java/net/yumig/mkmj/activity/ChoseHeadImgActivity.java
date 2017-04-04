package net.yumig.mkmj.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.currency.library.controller.BaseApplication;
import com.currency.library.utils.ImageUtils;

import net.yumig.mkmj.R;
import net.yumig.mkmj.util.BitmapUtil;
import net.yumig.mkmj.util.DialogUtil;
import com.currency.library.widget.image.RoundImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChoseHeadImgActivity extends Activity {

    private RoundImageView mImage;
    private Button mAddImage;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    //    private static final int CROP_SMALL_PICTURE = 2;
    private String mFilePath;//头像文件路径

    private static final int REQUECT_CODE_SDCARD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_head_img);
        initUI();
        initListeners();
    }

    private void initUI() {
        mImage = (RoundImageView) findViewById(R.id.iv_image);
        mAddImage = (Button) findViewById(R.id.btn_add_image);
        mFilePath = BaseApplication.sdCardPath + File.separator + "head_icon.jpg";
        Bitmap bitmap = ImageUtils.getSmallBitmap(mFilePath, 320, 320);
//        Bitmap bitmap = getDiskBitmap("/sdcard/mHeadPhoto.png");
        if (bitmap != null) {
            mImage.setImageBitmap(bitmap);
            Log.d("!=null", "---------");
        } else {
            mImage.setImageResource(R.drawable.img_gerentouxiang);
            Log.d("null", "---------");
        }
    }

    private void initListeners() {
        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
    }

    /**
     * 显示修改图片的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChoseHeadImgActivity.this);
        builder.setTitle("添加图片");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
//                        Intent openAlbumIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT);
//                        openAlbumIntent.setType("image/*");
//                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
//                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
//                        Intent openCameraIntent = new Intent(
//                                MediaStore.ACTION_IMAGE_CAPTURE);
//                        tempUri = Uri.fromFile(new File(Environment
//                                .getExternalStorageDirectory(), "temp_image.jpg"));
//                        // 将拍照所得的相片保存到SD卡根目录
//                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = null;
            switch (requestCode) {
                case TAKE_PICTURE://相机
//                    cutImage(tempUri); // 对图片进行裁剪处理
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            bitmap = (Bitmap) bundle.get("data");
                            mImage.setImageBitmap(bitmap);
                            BitmapUtil.bitmapOutSdCard(bitmap, mFilePath);
                            System.out.println("--1--" + mFilePath);
//                            checkSha1(mFilePath);
                            return;
                        }
                    }
//                    ToastUtils.showToastShort(this, "照片获取失败！");
                    DialogUtil.showToast(this, "照片获取失败", 0);
                    break;
                case CHOOSE_PICTURE://图库
//                    cutImage(data.getData()); // 对图片进行裁剪处理
                    Uri selectedImage = data.getData();
                    String picturePath = ImageUtils.getImageAbsolutePath(ChoseHeadImgActivity.this, selectedImage);

//                    Logger.d(picturePath);
                    bitmap = ImageUtils.getSmallBitmap(picturePath, 320, 320);
//                    iv_header_icon.resize(320, 320).setDefaultImage(bitmap);
                    mImage.setImageBitmap(bitmap);
                    ImageUtils.bitmapOutSdCard(bitmap, mFilePath);
                    System.out.println("--2--" + mFilePath);
//                    checkSha1(mFilePath);
                    break;
//                case CROP_SMALL_PICTURE:
//                    if (data != null) {
//                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
//                    }
//                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
//    protected void cutImage(Uri uri) {
//        if (uri == null) {
//            Log.i("alanjet", "The uri is not exist.");
//        }
//        tempUri = uri;
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        //com.android.camera.action.CROP这个action是用来裁剪图片用的
//        intent.setDataAndType(uri, "image/*");
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP_SMALL_PICTURE);
//    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            if (mBitmap != null) {
                //这里图片是方形的，可以用一个工具类处理成圆形（很多头像都是圆形，这种工具类网上很多不再详述）
                mImage.setImageBitmap(mBitmap);//显示图片
                //在这个地方可以写上上传该图片到服务器的代码，后期将单独写一篇这方面的博客，敬请期待...
                saveMyBitmap("mHeadPhoto", mBitmap);
            }

        }
    }

    public static String saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("/sdcard/" + bitName + ".png");

        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (Exception e) {
            return "create_bitmap_error";
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/sdcard/" + bitName + ".png";
    }


    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return bitmap;
    }
}