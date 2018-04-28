package com.xgw.mybaselib;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by weinai351353 on 2017/8/6.
 * activity管理栈
 */

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }

        if (activityStack == null) {
            activityStack = new Stack<>();
        }

        return instance;
    }

    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null) {
            Iterator var1 = activityStack.iterator();

            while (var1.hasNext()) {
                Activity activity = (Activity) var1.next();
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }

        return null;
    }

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = (Activity) activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = (Activity) activityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }

    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }

    }

    public void finishActivity(Class<?> cls) {
        Iterator var2 = activityStack.iterator();

        while (var2.hasNext()) {
            Activity activity = (Activity) var2.next();
            if (activity.getClass().equals(cls)) {
                this.finishActivity(activity);
                break;
            }
        }

    }

    public void finishAllActivity() {
        int i = 0;

        for (int size = activityStack.size(); i < size; ++i) {
            if (null != activityStack.get(i)) {
                this.finishActivity((Activity) activityStack.get(i));
            }
        }

        activityStack.clear();
    }

    public boolean isStackNotEmpty() {
        return activityStack != null && activityStack.size() > 0;
    }

    public void AppExit() {
        try {
            this.finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
