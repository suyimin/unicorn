package com.xdroid.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;


/**
 * 管理此应用的Activity
 */
public class AppActivityManager {


    private static Stack<IActivity> activityStack = new Stack<>();

    private AppActivityManager() {
    }

    private static class ManagerHolder {
        private static final AppActivityManager instance = new AppActivityManager();
    }

    public static AppActivityManager create() {
        return ManagerHolder.instance;
    }

    /**
     * 获取当前Activity栈中元素个数
     */
    public int getCount() {
        return activityStack.size();
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(IActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public static Stack<IActivity> getActivityStack() {
        return activityStack;
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public Activity topActivity() {
        if (activityStack == null) {
            throw new NullPointerException("Activity stack is Null,your Activity must extend BaseActivity");
        }
        if (activityStack.isEmpty()) {
            return null;
        }
        IActivity activity = activityStack.lastElement();
        return (Activity) activity;
    }

    /**
     * 获取制定的Activity 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        IActivity activity = null;
        for (IActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return (Activity) activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        IActivity activity = activityStack.lastElement();
        finishActivity((Activity) activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (IActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity((Activity) activity);
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     */
    public void finishOthersActivity(Class<?> cls) {

        for (IActivity activity : activityStack) {
            if (activity != null && !(activity.getClass().equals(cls))) {
                finishActivity((Activity) activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {

        for (IActivity activity : activityStack) {
            if (activity != null) {
                finishActivity((Activity) activity);
            }
        }
    }

    /**
     * 应用程序退出
     * <p>
     * <b>注意</b>You must hold the permission
     * {@link android.Manifest.permission#KILL_BACKGROUND_PROCESSES} to be able
     * to call this method.
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            Runtime.getRuntime().exit(-1);
        }
    }

}
