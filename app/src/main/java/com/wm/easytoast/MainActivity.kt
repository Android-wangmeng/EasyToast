package com.wm.easytoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ClickUtils
import com.blankj.utilcode.util.ColorUtils
import com.wm.toast.EasyToast
import com.wm.toast.extension.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyDebouncingClickListener(toast_default,toast_success,toast_error,toast_info,toast_warning,
            toast_success_anim,toast_error_anim,toast_info_anim,toast_warning_anim)
    }

    private fun applyDebouncingClickListener(vararg views: View) {
        ClickUtils.applyGlobalDebouncing(views, this)
        for (v in views) {
            ClickUtils.applyPressedBgDark(v)
        }
    }

    override fun onClick(v: View?) {
        when(v){
            toast_default ->R.string.toast_default.showToast()
            toast_success -> R.string.toast_success.showSuccessToast(false)
            toast_error ->R.string.toast_error.showErrorToast(false)
            toast_info ->R.string.toast_info.showInfoToast(false)
            toast_warning ->R.string.toast_warning.showWarningToast(false)

            toast_success_anim -> R.string.toast_success.showSuccessToast()
            toast_error_anim ->R.string.toast_error.showErrorToast()
            toast_info_anim ->R.string.toast_info.showInfoToast()
            toast_warning_anim ->R.string.toast_warning.showWarningToast()
        }
    }
}