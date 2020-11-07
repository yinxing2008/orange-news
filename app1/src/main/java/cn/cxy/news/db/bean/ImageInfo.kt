package cn.cxy.news.db.bean

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ImageInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var path: String, var url: String
) : Parcelable