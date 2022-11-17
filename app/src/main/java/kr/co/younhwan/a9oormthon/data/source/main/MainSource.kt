package kr.co.younhwan.a9oormthon.data.source.main

import kr.co.younhwan.a9oormthon.data.voiceItem

interface MainSource {
    // key
    fun readKey(
        uuid: String,
        readKeyCallback: ReadKeyCallback?
    )

    interface ReadKeyCallback {
        fun onReadKey(token: String)
    }

    // voice
    fun readVoice(
        token: String,
        readVoiceCallback: ReadVoiceCallback?
    )

    interface ReadVoiceCallback {
        fun onReadVoice(list: ArrayList<voiceItem>)
    }

    // read
    fun read(
        readCallback: ReadCallback?
    )

    interface ReadCallback {
        fun onRead()
    }

    // readVoice

    // create

    // update

    // delete
}