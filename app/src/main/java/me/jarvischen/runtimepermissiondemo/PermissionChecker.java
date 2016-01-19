package me.jarvischen.runtimepermissiondemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by chenfuduo on 2016/1/19.
 */
public class PermissionChecker {

    private Context context;

    public PermissionChecker(Context context) {
        this.context = context;
    }

    public boolean lacksPermissions(String ...permissions){
        for (String permission:permissions){
            if (lacksPermission(permission)){
                return true;
            }
        }
        return false;
    }

    private boolean lacksPermission(String permission){
        return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_DENIED;
    }

}
