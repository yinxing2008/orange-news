package cn.cxy.wanandroid.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageUtil {
    private val tag = ImageUtil.javaClass.simpleName
//    fun saveBitmapWithPermission(context: Context, bitmap: Bitmap) {
//        SoulPermission.getInstance()
//            .checkAndRequestPermission(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                object : CheckRequestPermissionListener {
//                    override fun onPermissionOk(permission: Permission) {
//                        saveBitmap(context, bitmap)
//                    }
//
//                    override fun onPermissionDenied(permission: Permission) {
//                    }
//                })
//    }

    fun getNewFileName() = System.currentTimeMillis().toString() + ".png"
    fun saveBitmap(context: Context, bitmap: Bitmap): String {
        val file = File(context.filesDir, getNewFileName())
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.flush()
            out.close()
        } catch (e: IOException) {
            e.message?.let { Log.e(tag, it) }
        }
        return file.canonicalPath
    }

    fun deleteFile(path: String) {
        File(path).delete()
    }
}