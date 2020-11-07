package cn.cxy.news.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cn.cxy.news.db.bean.ImageInfo

@Dao
interface ImageInfoDao {
    @Query("SELECT * FROM ImageInfo")
    fun list(): List<ImageInfo>

    @Query("SELECT * FROM ImageInfo where url=:url")
    fun queryByUrl(url: String): ImageInfo?

    @Insert
    fun add(imageInfo: ImageInfo)

    @Insert
    fun add(imageInfos: List<ImageInfo>)

    @Delete
    fun del(imageInfo: ImageInfo)

    @Query("DELETE FROM ImageInfo where url=:url")
    fun deleteByUrl(url: String)
}