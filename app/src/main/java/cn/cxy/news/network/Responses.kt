package cn.cxy.news.network

import com.google.gson.annotations.SerializedName

data class Chapters(
    @SerializedName("data")
    var dataList: List<Data>,
    @SerializedName("errorCode")
    var errorCode: Int,
    @SerializedName("errorMsg")
    var errorMsg: String
) {
    data class Data(
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

    fun isSuccess() = errorCode == 0
}