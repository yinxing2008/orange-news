package cn.cxy.news.db.repository

import cn.cxy.news.db.AppDatabase

open class BaseRepository {
    val dbInstance = AppDatabase.getInstance()
}