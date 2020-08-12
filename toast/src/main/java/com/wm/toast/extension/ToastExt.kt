package com.wm.toast.extension

import androidx.annotation.StringRes
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.Utils
import com.wm.toast.EasyToast

/** 默认 */
private const val TOAST_TYPE_NORMAL:Int     = 1
/** 失败*/
private const val TOAST_TYPE_ERROR:Int      = 2
/** 警告 */
private const val TOAST_TYPE_WARNING:Int    = 3
/** 提示 */
private const val TOAST_TYPE_INFO:Int       = 4
/** 成功 */
private const val TOAST_TYPE_SUCCESS:Int    = 5

/*********************************Int 扩展函数 ****************************************/
/**
 * 默认Toast
 */
fun Int.showToast(){
    toast(TOAST_TYPE_NORMAL,this,false)
}

/**
 * 失败Toast
 */
fun Int.showErrorToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_ERROR,this,isShowAnim)
}

/**
 * 警告Toast
 */
fun Int.showWarningToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_WARNING,this,isShowAnim)
}

/**
 * 提示Toast
 */
fun Int.showInfoToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_INFO,this,isShowAnim)
}

/**
 * 成功Toast
 */
fun Int.showSuccessToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_SUCCESS,this,isShowAnim)
}

/*********************************String 扩展函数 ****************************************/
/**
 * 默认Toast
 */
fun String.showToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_NORMAL,this,isShowAnim)
}

/**
 * 失败Toast
 */
fun String.showErrorToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_ERROR,this,isShowAnim)
}

/**
 * 警告Toast
 */
fun String.showWarningToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_WARNING,this,isShowAnim)
}

/**
 * 提示Toast
 */
fun String.showInfoToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_INFO,this,isShowAnim)
}

/**
 * 成功Toast
 */
fun String.showSuccessToast(isShowAnim:Boolean = true){
    toast(TOAST_TYPE_SUCCESS,this,isShowAnim)
}

private fun toast(type:Int,msg:String,isShowAnim: Boolean = false){
    EasyToast.Config.getInstance().setUseAnim(isShowAnim).apply()
    when(type){
        TOAST_TYPE_ERROR -> EasyToast.error(Utils.getApp(), msg).show()
        TOAST_TYPE_INFO -> EasyToast.info(Utils.getApp(),msg).show()
        TOAST_TYPE_NORMAL -> EasyToast.normal(Utils.getApp(),msg).show()
        TOAST_TYPE_WARNING -> EasyToast.warning(Utils.getApp(),msg).show()
        TOAST_TYPE_SUCCESS -> EasyToast.success(Utils.getApp(),msg).show()
    }
}
private fun toast(type:Int,@StringRes msg:Int,isShowAnim: Boolean = false){
    toast(type,StringUtils.getString(msg),isShowAnim)
}