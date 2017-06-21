package org.adroidtown.practiceapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends BaseActivity {
    RealmListFragment realmListFragment;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainTop)
    View mainTop;
    BroadcastReceiver receiver;
    Context mContext;
    PostImageFragment postImageFragment;
    static int REQUEST_PICTURE = 100;
    static int REQUEST_PHOTO_ALBUM = 200;
    static String SAMPLEIMG = "picture.png";
    Boolean isFromAlbum;
    Bitmap image;
    String imagePath;
    Uri uriAlbum;
    String key;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        Log.d("Realm", "MainActivity - Realm = " + realm);
//        Intent intent = new Intent(MainActivity.this, TimeAlertService.class);
//        startService(intent);
        setAlarm();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main_new;
    }

    @Override
    public void butterKnifeInject() {
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mContext = this;

        setSupportActionBar(toolbar);
        realmListFragment = new RealmListFragment();
        postImageFragment = new PostImageFragment();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        setupListener();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, realmListFragment).commit();
    }

    @Override
    public void setupListener() {

        realmListFragment.setOnPostListener(new RealmListFragment.OnPostListener() {
            @Override
            public void onClick() {
                postFragmentTransaction();
            }
        });

        postImageFragment.setOnImageClickListener(new PostImageFragment.OnImageClickListener() { //이미지 선택 혹은 카메라 찍기,카메라는 임시저장 사용해야 함 , 이미지선택은 경로를 가져와서
            @Override
            public void onClick() {
                final CharSequence[] items = {"앨범에서 불러오기", "카메라로 촬영하기"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //앨범에서 불러오기
                            dialog.dismiss();
                            loadPicture();
                        } else {
                            //카메라로 촬영하기
                            dialog.dismiss();
                            takePicture();
                        }
                    }
                });
                builder.create().show();

            }
        });


        postImageFragment.setOnPostBtnListener(new PostImageFragment.OnPostBtnListener() { //다이얼로그 알림 처리를 MainActivity에게 부탁
            @Override
            public void onClick() {

                String content;
                String path;
                Intent intent = new Intent("action1");
                intent.setPackage("org.adroidtown.practiceapp");
                try {
                    if (isFromAlbum == true) {
                        intent.putExtra("path", uriAlbum);
                        content = postImageFragment.editText.getText().toString();
                        intent.putExtra("editText", content);
                        startService(intent);
                        Log.d("Service123", "startService() 앨범 ");
                        path = uriAlbum.toString();
                    } else {
                        intent.putExtra("image", imagePath);
                        content = postImageFragment.editText.getText().toString();
                        intent.putExtra("editText", content);
                        startService(intent);
                        Log.d("Service123", "startService() 촬영 ");
                        path = imagePath.toString();
                    }
                    writeNewPost(content, path);
                } catch (Exception e) {
                    Toast.makeText(mContext, "사진을 선택해주세요", Toast.LENGTH_LONG).show();
                }


                Log.d("Service123", "writeNewPost");

                getSupportFragmentManager().beginTransaction().replace(R.id.container, realmListFragment).commit();

                int ids[] = AppWidgetManager.getInstance(mContext).getAppWidgetIds(new ComponentName(getApplication(), CustomWidget.class));

                Intent intentW = new Intent(mContext, CustomWidget.class);
                intentW.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                intentW.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intentW);

            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CustomDialog customDialog = new CustomDialog(mContext, intent);
        customDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //인텐트 초기화

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PHOTO_ALBUM) {
//                postImageFragment.imageView.setImageURI(data.getData());
                uriAlbum = data.getData();
                isFromAlbum = true;
            } else if (requestCode == REQUEST_PICTURE) {
//                postImageFragment.imageView.setImageBitmap(loadPictureToImageView());
//                intentResult.putExtra("image", intentResult.getExtras().getParcelable(MediaStore.EXTRA_OUTPUT));
//
//                Log.d("kk9991", "절대경로 : onActicityResult " + imagePath);
//                data.putExtra("image", imagePath);
                isFromAlbum = false;

            }
        }
    }

    private void postFragmentTransaction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, postImageFragment).commit();
    }

    private void listFragmentTransaction() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, realmListFragment).commit();
    }

    private void loadPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, REQUEST_PHOTO_ALBUM);
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), key + ".png");
        Uri path = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, REQUEST_PICTURE);
    }

    private Bitmap loadPictureToImageView() {
        File file = new File(Environment.getExternalStorageDirectory(), key + ".png");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        image = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        Log.d("kk9991", "절대경로 : " + file.getAbsolutePath());
        imagePath = file.getAbsolutePath();
        return image;
    }


    @Override
    protected void onStart() {
        super.onStart();
        this.receiver = new Receiver();
        IntentFilter item = new IntentFilter("action1");
        registerReceiver(this.receiver, item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.receiver);
    }

    private void writeNewPost(String content, String path) {
        final String rContent = content;
        final String rPath = path;

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                Item item = realm.createObject(Item.class);
                item.setContent(rContent);
                item.setPath(rPath);
                realm.insertOrUpdate(item);
            }
        });
    }

    public void setAlarm() {
        Log.d("bomee","setAlarm확인");
        Intent intent = new Intent(mContext, TimeAlertService.class);
        intent.setAction("timetime");
        PendingIntent sender = PendingIntent.getService(mContext, 555, intent, 0);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.SECOND,0);
//        cal.set(Calendar.MINUTE,cal.getTime().getMinutes());
        cal.set(Calendar.HOUR_OF_DAY,cal.getTime().getHours());
        switch (cal.get(Calendar.MINUTE) % 5) {
            case 0:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                break;
            case 1:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 4);
                break;
            case 2:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 3);
                break;
            case 3:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 2);
                break;
            case 4:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 1);
                break;
        }
        Log.d("bomee","setAlarm확인 : "+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND));
        am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),60*1000,sender);
        Log.d("bomee","setAlarm확인 : "+am);
        Log.d("bomee","sender 확인 : "+sender);

    }
}

