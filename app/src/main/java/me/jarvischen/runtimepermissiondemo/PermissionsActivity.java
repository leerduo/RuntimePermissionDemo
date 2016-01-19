package me.jarvischen.runtimepermissiondemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PermissionsActivity extends AppCompatActivity {

    private PermissionChecker checker;
    private static final String EXTRA_PERMISSIONS = "me.jarvischen.runtimepermissiondemo.EXTRA_PERMISSIONS";
    public static final int PERMISSIONS_GRANTED = 0;
    public static final int PERMISSIONS_DENIED = 1;


    public static final void startActivityForResult(Activity activity,int requestCode,String... permissions){
        Intent intent = new Intent(activity,PermissionsActivity.class);
        intent.putExtra(EXTRA_PERMISSIONS,permissions);
        ActivityCompat.startActivityForResult(activity,intent,requestCode,null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checker = new PermissionChecker(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissions = getPermissions();
        if (checker.lacksPermissions(permissions)){
            requestPermissions(permissions);
        }else{
            allPermissionsGranted();
        }
    }

    private void allPermissionsGranted() {
        setResult(PERMISSIONS_GRANTED);
        finish();
    }

    private void requestPermissions(String... permissions) {

    }


    private String[] getPermissions(){
        return getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
    }

}
