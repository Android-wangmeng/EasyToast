package com.wm.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.wm.toast.anim.ErrorAnim;
import com.wm.toast.anim.InfoAnim;
import com.wm.toast.anim.SuccessAnim;
import com.wm.toast.anim.TextFadeAnim;
import com.wm.toast.anim.ToastImage;
import com.wm.toast.anim.ToastText;
import com.wm.toast.anim.WarningAnim;


/**
 * Created by zia on 2018/5/13.
 */
public class EasyToast {

    @ColorInt
    public static int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    public static int ERROR_COLOR = Color.parseColor("#D50000");
    @ColorInt
    public static int INFO_COLOR = Color.parseColor("#3F51B5");
    @ColorInt
    public static int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    public static int WARNING_COLOR = Color.parseColor("#FFA900");
    @ColorInt
    public static int NORMAL_COLOR = Color.parseColor("#353A3E");

    public static int NO_COLOR = 0;

    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    public static int textSize = 16; // in SP

    private static boolean tintIcon = true;
    private static boolean useAnim = true;

    private EasyToast() {
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message) {
        return normal(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return normal(context, message, duration, null);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration, ToastImage toastImage) {
        return custom(context, message, duration, NO_COLOR, toastImage);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence message) {
        return info(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence message, int duration) {
        ToastImage toastImage = new InfoAnim(context);
        return info(context, message, duration, toastImage);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence message, int duration, ToastImage toastImage) {
        return custom(context, message, duration, INFO_COLOR, toastImage);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message) {
        return warning(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration) {
        ToastImage toastImage = new WarningAnim(context);
        return warning(context, message, duration, toastImage);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration, ToastImage toastImage) {
        return custom(context, message, duration, WARNING_COLOR, toastImage);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message) {
        return error(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return error(context, message, duration, new ErrorAnim(context));
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration, ToastImage toastImage) {
        return custom(context, message, duration, ERROR_COLOR, toastImage);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message) {
        return success(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return success(context, message, duration, new SuccessAnim(context));
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration, ToastImage toastImage) {
        return custom(context, message, duration, SUCCESS_COLOR, toastImage);
    }

    @CheckResult
    @SuppressLint("ShowToast")
    public static Toast custom(@NonNull Context context, @NonNull CharSequence charSequence, int duration, @ColorInt int bgColor, ToastImage toastImage) {
        //init TextView
        final TextFadeAnim toastTextView = new TextFadeAnim(context);
        toastTextView.setGravity(Gravity.CENTER);
        toastTextView.setText(charSequence);
        toastTextView.setTextColor(DEFAULT_TEXT_COLOR);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return custom(context, toastTextView, duration, bgColor, toastImage);
    }

    @CheckResult
    @SuppressLint("ShowToast")
    public static Toast custom(@NonNull Context context, @NonNull ToastText text, int duration, @ColorInt int bgColor, ToastImage toastImage) {
        final Toast toast = Toast.makeText(context, "", duration);

        //init LinearLayout
        final LinearLayout toastLayout = new LinearLayout(context);
        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
        toastLayout.setGravity(Gravity.CENTER);
        toastLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        Drawable drawableFrame;
        if (bgColor != NO_COLOR && toastImage != null)
            drawableFrame = EasyToastUtils.tint9PatchDrawableFrame(context, bgColor);
        else
            drawableFrame = EasyToastUtils.getDrawable(context, R.drawable.toast_frame);
        EasyToastUtils.setBackground(toastLayout, drawableFrame);
        //set anim time
        int animTime = 0;
        if (useAnim) {
            if (duration == Toast.LENGTH_SHORT)
                animTime = 500;
            else
                animTime = 1000;
        }
        //load image into linearLayout if exist
        if (tintIcon && toastImage != null) {
            //set imageLayout
            toastImage.setDuration(animTime);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(dp2px(context, 22f)
                    , dp2px(context, 22f));
            imageParams.setMargins(0, dp2px(context, 1f), dp2px(context, 5f), 0);
            //config image
            toastImage.setColor(DEFAULT_TEXT_COLOR);
            toastLayout.addView(toastImage, imageParams);
        }
        //add text
        text.setColor(DEFAULT_TEXT_COLOR);
        text.setDuration(animTime);
        toastLayout.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT));
        toast.setView(toastLayout);
        return toast;
    }

    private static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static class Config {
        @ColorInt
        private int DEFAULT_TEXT_COLOR = EasyToast.DEFAULT_TEXT_COLOR;
        @ColorInt
        private int ERROR_COLOR = EasyToast.ERROR_COLOR;
        @ColorInt
        private int INFO_COLOR = EasyToast.INFO_COLOR;
        @ColorInt
        private int SUCCESS_COLOR = EasyToast.SUCCESS_COLOR;
        @ColorInt
        private int WARNING_COLOR = EasyToast.WARNING_COLOR;

        private Typeface typeface = EasyToast.currentTypeface;
        private int textSize = EasyToast.textSize;

        private boolean tintIcon = EasyToast.tintIcon;
        private boolean useAnim = EasyToast.useAnim;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            EasyToast.DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
            EasyToast.ERROR_COLOR = Color.parseColor("#D50000");
            EasyToast.INFO_COLOR = Color.parseColor("#3F51B5");
            EasyToast.SUCCESS_COLOR = Color.parseColor("#388E3C");
            EasyToast.WARNING_COLOR = Color.parseColor("#FFA900");
            EasyToast.currentTypeface = LOADED_TOAST_TYPEFACE;
            EasyToast.textSize = 16;
            EasyToast.tintIcon = true;
            EasyToast.useAnim = true;
        }

        @CheckResult
        public Config setTextColor(@ColorInt int textColor) {
            DEFAULT_TEXT_COLOR = textColor;
            return this;
        }

        @CheckResult
        public Config setErrorColor(@ColorInt int errorColor) {
            ERROR_COLOR = errorColor;
            return this;
        }

        @CheckResult
        public Config setInfoColor(@ColorInt int infoColor) {
            INFO_COLOR = infoColor;
            return this;
        }

        @CheckResult
        public Config setSuccessColor(@ColorInt int successColor) {
            SUCCESS_COLOR = successColor;
            return this;
        }

        @CheckResult
        public Config setWarningColor(@ColorInt int warningColor) {
            WARNING_COLOR = warningColor;
            return this;
        }

        @CheckResult
        public Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public Config setTextSize(int sizeInSp) {
            this.textSize = sizeInSp;
            return this;
        }

        @CheckResult
        public Config tintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;
            return this;
        }

        @CheckResult
        public Config setUseAnim( boolean useAnim) {
            this.useAnim = useAnim;
            return this;
        }

        public void apply() {
            EasyToast.DEFAULT_TEXT_COLOR = DEFAULT_TEXT_COLOR;
            EasyToast.ERROR_COLOR = ERROR_COLOR;
            EasyToast.INFO_COLOR = INFO_COLOR;
            EasyToast.SUCCESS_COLOR = SUCCESS_COLOR;
            EasyToast.WARNING_COLOR = WARNING_COLOR;
            EasyToast.currentTypeface = typeface;
            EasyToast.textSize = textSize;
            EasyToast.tintIcon = tintIcon;
            EasyToast.useAnim = useAnim;
        }
    }
}
