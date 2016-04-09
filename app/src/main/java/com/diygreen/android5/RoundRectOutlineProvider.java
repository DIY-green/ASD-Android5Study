/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.diygreen.android5;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成
 * @version 1.0
 */

public class RoundRectOutlineProvider extends ViewOutlineProvider {
    @Override
    public void getOutline(View view, Outline outline) {
        int margin = 30;
        outline.setRoundRect(margin, margin, view.getWidth() - margin,
                view.getHeight() - margin, margin / 2);
    }
}
