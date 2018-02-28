package com.custom.frame.ui.activitys;

import android.Manifest;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;

import com.custom.frame.R;
import com.custom.frame.base.BaseActivity;
import com.custom.frame.utils.CommonUtil;
import com.custom.frame.utils.FileStorageHelper;
import com.custom.frame.utils.LogUtil;
import com.custom.frame.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author zhangxin
 * @date 2018/2/27
 * @description 首页
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private static final int SAVE_PHOTO = 1;
    private static final int PRC_PHOTO_PREVIEW = 2;

    @BindView(R.id.photoLayout)
    BGANinePhotoLayout photoLayout;

    ArrayList<String> photoList = new ArrayList<>();

    private int[] photoNameList = new int[]{R.raw.lulu1, R.raw.lulu2, R.raw.lulu3, R.raw.lulu4,
            R.raw.lulu5, R.raw.lulu6, R.raw.lulu7, R.raw.lulu8,
            R.raw.lulu9, R.raw.lulu10, R.raw.lulu11, R.raw.lulu12,
            R.raw.lulu13, R.raw.lulu14, R.raw.lulu15, R.raw.lulu16,
            R.raw.lulu17, R.raw.lulu18, R.raw.lulu19, R.raw.lulu20,};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getTitleBar().setTitleText("爱笑的你");
        getTitleBar().hideLeftImage();
        initPhotoLayout();

    }

    @AfterPermissionGranted(SAVE_PHOTO)
    private void initPhotoLayout() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            try {
                for (int i = 0; i < photoNameList.length; i++) {

                    String path = FileStorageHelper.copyFilesFromRaw(this, photoNameList[i], "lulu" + (i + 1) + ".jpg", Environment.getExternalStorageDirectory() + "/" + "MineLuLu");
                    photoList.add(path);
                }
                photoLayout.setDelegate(new BGANinePhotoLayout.Delegate() {
                    @Override
                    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
                        photoPreviewWrapper(models, position);
                    }
                });
                photoLayout.setData(photoList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.savePicturePermissions), SAVE_PHOTO, perms);
        }
    }


    /**
     * 图片预览，兼容6.0动态权限
     */
    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
    private void photoPreviewWrapper(List<String> modeList, int position) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            try {
                BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(this)
                        .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
                photoPreviewIntentBuilder.previewPhotos((ArrayList<String>) modeList)
                        .currentPosition(position); // 当前预览图片的索引
                startActivity(photoPreviewIntentBuilder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.previewPicturePermissions), PRC_PHOTO_PREVIEW, perms);
        }
    }

    @Override
    public boolean doubleExitAppEnable() {
        return true;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PREVIEW) {
            ToastUtil.showTextToast(R.string.error_picture_permissions);
        } else if (requestCode == SAVE_PHOTO) {
            ToastUtil.showTextToast(R.string.refusePicturePermissions);
        }
    }
}
