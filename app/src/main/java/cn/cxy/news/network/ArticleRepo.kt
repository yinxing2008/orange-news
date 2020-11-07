package cn.cxy.news.network

class ArticleRepo : BaseRepository() {
    suspend fun getChapters() = apiService.getChapters()
}