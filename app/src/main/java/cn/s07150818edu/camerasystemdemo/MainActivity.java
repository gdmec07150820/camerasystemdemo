package cn.s07150818edu.camerasystemdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String imaPath;
    private ImageView imageView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"打开系统相机");
        menu.add(0,2,0,"打开系统项目，显示照相结果");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent intent=new Intent();//调用相机
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
                break;
            case  2:
                takePhoto();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void takePhoto(){
        imaPath= android.os.Environment.getExternalStorageDirectory().getPath();
        imageView= (ImageView) this.findViewById(R.id.imageview);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hhhjjj");
        Date curent=new Date(System.currentTimeMillis());
        String str=simpleDateFormat.format(curent);
        imaPath=imaPath+"/"+str+".jpg";
        file=new File(imaPath);
        Uri uri=Uri.fromFile(file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (file.exists()){
            imageView.setImageURI(Uri.fromFile(file));
        }
    }
}
