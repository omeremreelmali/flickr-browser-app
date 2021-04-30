package net.fixedbugs.flickrbrowser.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.*

data class Photo(
    val title: String,
    val media: Media,
    val author: String

)

data class Media(
    val m :String
)
