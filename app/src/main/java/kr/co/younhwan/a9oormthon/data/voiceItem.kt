package kr.co.younhwan.a9oormthon.data

data class voiceItem(
    var id: Int?,
    var name: String,
    var selected : Boolean,
    var audioFile: String,
    var type : Int
)
