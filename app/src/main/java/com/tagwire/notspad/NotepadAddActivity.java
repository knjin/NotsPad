package com.tagwire.notspad;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加新的记录的活动界面
 */
public class NotepadAddActivity extends Activity {
    private EditText contentEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_add);
        contentEt = (EditText) findViewById(R.id.contentEtId);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notepad_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.notepadSaveId){
            Log.d("TAG","添加记录");
            String content=contentEt.getText().toString();
            if(TextUtils.isEmpty(content)){
                contentEt.setError("请输入信息");
                return true;
            }
            NotePadDao dao=new NotePadDao(this);//context
            ContentValues values=new ContentValues();
            values.put("_content", content);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            values.put("_created", sdf.format(new Date()));
            long id=dao.insert(values);
            if(id==-1){
                Toast.makeText(this, "保存失败!", Toast.LENGTH_SHORT).show();
                return true;
            }
            Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
            //4.¹Ø±Õ´ËÒ³Ãæ
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
