package cn.cxy.wanandroid.network

import com.google.gson.annotations.SerializedName

class ChapterResp : BaseResponse<List<ChapterResp.Chapter>>() {
    data class Chapter(
        @SerializedName("children")
        var children: List<Any>,
        @SerializedName("courseId")
        var courseId: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("order")
        var order: Int,
        @SerializedName("parentChapterId")
        var parentChapterId: Int,
        @SerializedName("userControlSetTop")
        var userControlSetTop: Boolean,
        @SerializedName("visible")
        var visible: Int
    )
}

class ArticleResp : BaseResponse<ArticleResp.Articles>() {
    class Articles(
        @SerializedName("curPage")
        var curPage: Int,
        @SerializedName("datas")
        var datas: List<Article>,
        @SerializedName("offset")
        var offset: Int,
        @SerializedName("over")
        var over: Boolean,
        @SerializedName("pageCount")
        var pageCount: Int,
        @SerializedName("size")
        var size: Int,
        @SerializedName("total")
        var total: Int
    ) {
        data class Article(
            @SerializedName("apkLink")
            var apkLink: String,
            @SerializedName("audit")
            var audit: Int,
            @SerializedName("author")
            var author: String,
            @SerializedName("canEdit")
            var canEdit: Boolean,
            @SerializedName("chapterId")
            var chapterId: Int,
            @SerializedName("chapterName")
            var chapterName: String,
            @SerializedName("collect")
            var collect: Boolean,
            @SerializedName("courseId")
            var courseId: Int,
            @SerializedName("desc")
            var desc: String,
            @SerializedName("descMd")
            var descMd: String,
            @SerializedName("envelopePic")
            var envelopePic: String,
            @SerializedName("fresh")
            var fresh: Boolean,
            @SerializedName("id")
            var id: Int,
            @SerializedName("link")
            var link: String,
            @SerializedName("niceDate")
            var niceDate: String,
            @SerializedName("niceShareDate")
            var niceShareDate: String,
            @SerializedName("origin")
            var origin: String,
            @SerializedName("prefix")
            var prefix: String,
            @SerializedName("projectLink")
            var projectLink: String,
            @SerializedName("publishTime")
            var publishTime: Long,
            @SerializedName("realSuperChapterId")
            var realSuperChapterId: Int,
            @SerializedName("selfVisible")
            var selfVisible: Int,
            @SerializedName("shareDate")
            var shareDate: Long,
            @SerializedName("shareUser")
            var shareUser: String,
            @SerializedName("superChapterId")
            var superChapterId: Int,
            @SerializedName("superChapterName")
            var superChapterName: String,
            @SerializedName("tags")
            var tags: List<Tag>,
            @SerializedName("title")
            var title: String,
            @SerializedName("type")
            var type: Int,
            @SerializedName("userId")
            var userId: Int,
            @SerializedName("visible")
            var visible: Int,
            @SerializedName("zan")
            var zan: Int
        ) {
            data class Tag(
                @SerializedName("name")
                var name: String,
                @SerializedName("url")
                var url: String
            )
        }
    }
}

open class BaseResponse<T> {
    @SerializedName("data")
    var `data`: T? = null

    @SerializedName("errorCode")
    var errorCode: Int = 0

    @SerializedName("errorMsg")
    var errorMsg: String = ""
    fun isSuccess() = errorCode == 0
}