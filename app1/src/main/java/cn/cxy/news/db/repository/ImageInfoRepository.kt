package cn.cxy.news.db.repository

import cn.cxy.news.db.bean.ImageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ImageInfoRepository : BaseRepository() {
    private val imageInfoDao = dbInstance.imageInfoDao()
    suspend fun add(path: String, url: String) {
        withContext(Dispatchers.IO) {
            val imageInfo = ImageInfo(0, path, url)
            imageInfoDao.add(imageInfo)
        }
    }

    suspend fun list(): List<ImageInfo> {
        return withContext(Dispatchers.IO) {
            imageInfoDao.list()
        }
    }

    suspend fun queryByUrl(url: String): ImageInfo? {
        return withContext(Dispatchers.IO) {
            return@withContext imageInfoDao.queryByUrl(url)
        }
    }

    suspend fun exists(url: String): Boolean {
        return withContext(Dispatchers.IO) {
            val result = imageInfoDao.queryByUrl(url)
            return@withContext result != null
        }
    }

    suspend fun del(id: Int) {
        withContext(Dispatchers.IO) {
            val imageInfo = ImageInfo(id, "", "")
            imageInfoDao.del(imageInfo)
        }
    }

    suspend fun del(url: String) {
        withContext(Dispatchers.IO) {
            imageInfoDao.deleteByUrl(url)
        }
    }
}