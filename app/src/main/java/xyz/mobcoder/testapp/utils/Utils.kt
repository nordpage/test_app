package xyz.mobcoder.testapp.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import xyz.mobcoder.testapp.api.models.ItemXX

object Utils {


    fun getBitmapSource(context: Context, imageUrl: String): Bitmap? {
        return try {
            Glide
                .with(context)
                .asBitmap()
                .load(imageUrl)
                .submit()
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getRoom(items : List<ItemXX>) : ItemXX {
        return items.first { itemXX -> itemXX.className.equals("Room") }
    }
}