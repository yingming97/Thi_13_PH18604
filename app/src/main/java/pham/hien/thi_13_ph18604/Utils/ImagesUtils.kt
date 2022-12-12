package pham.hien.thi_13_ph18604.Utils

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.provider.MediaStore
import android.widget.ImageView
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import gun0912.tedimagepicker.builder.TedImagePicker
import pham.hien.thi_13_ph18604.R
import java.io.ByteArrayOutputStream
import java.io.IOException

class ImagesUtils {

    private val storage = Firebase.storage

    fun uploadImage(
        imv: ImageView,
        tableName: String,
        id: String,
        callback: ((String) -> Unit)? = null
    ) {

        val storageRef = storage.reference
        val mountainsRef = storageRef.child("$tableName/$id")
        imv.isDrawingCacheEnabled = true
        imv.buildDrawingCache()
        val bitmap = (imv.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = mountainsRef.putBytes(data)

        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener {

        }
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            mountainsRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                callback?.invoke(downloadUri)
            }
        }
    }
    fun checkPermissionChonAnh(context: Context, imv: ImageView) {
        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                openImagesPicker(context, imv)
            }

            override fun onPermissionDenied(deniedPermissions: List<String?>) {

            }
        }
        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("Bạn cần cấp quyền để chọn ảnh/ chụp ảnh từ thiết bị.\n\nHãy cấp quyền cho ứng dụng [Setting] > [Permission]")
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
            .check();
    }

    private fun openImagesPicker(context: Context, imv: ImageView) {
        TedImagePicker.with(context)
            .start { uri ->
                try {
                    val pathFile = URIPathHelper().getPath(context, uri)
                    val bitmap1 = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)

                    val bitmap: Bitmap? = pathFile?.let { compressImageFromPath(bitmap1, it) }
                    Glide.with(context).load(bitmap)
                        .placeholder(R.drawable.img_default)
                        .into(imv)
                } catch (e: Exception) {
                }
            }
    }

    private fun compressImageFromPath(bitmap: Bitmap, filePath: String): Bitmap? {
        var scaledBitmap: Bitmap? = null
        val exif: ExifInterface

        try {
            exif = ExifInterface(filePath)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, 0)
            val matrix = Matrix()
            when (orientation) {
                6 -> {
                    matrix.postRotate(90f)
                }
                3 -> {
                    matrix.postRotate(180f)
                }
                8 -> {
                    matrix.postRotate(270f)
                }
            }
            scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.width, bitmap.height, matrix,
                true)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return scaledBitmap
    }
}
