package com.xgw.mybaselib.widget.roundview.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;

/**
 * 字体帮助类
 */
public class FontHelper {
    private static final SimpleArrayMap<String, Typeface> fontMap = new SimpleArrayMap<>();

    public static Typeface get(Context c, String name) {
        synchronized (FontHelper.class) {
            if (!fontMap.containsKey(name)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), String.format("fonts/%s", name));
                    fontMap.put(name, t);
                    return t;
                } catch (RuntimeException e) {
                    return null;
                }
            }
            return fontMap.get(name);
        }
    }
}
