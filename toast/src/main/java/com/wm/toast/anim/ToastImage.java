package com.wm.toast.anim;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ToastImage extends AppCompatImageView implements IAnim {

    public ToastImage(Context context) {
        super(context);
    }

    public ToastImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ToastImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setDuration(int duration) {

    }

    @Override
    public void setColor(int color) {

    }
}
