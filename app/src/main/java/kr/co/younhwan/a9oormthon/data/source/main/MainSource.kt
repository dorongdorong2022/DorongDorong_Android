package kr.co.younhwan.a9oormthon.data.source.main

import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.taleItem
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

    // sound
    fun readSound(
        token: String,
        readSoundCallback: ReadSoundCallback?
    )

    interface ReadSoundCallback {
        fun onReadSound(list: ArrayList<soundItem>)
    }

    // tale
    fun readTale(
        token: String,
        readTaleCallback: ReadTaleCallback?
    )

    interface ReadTaleCallback {
        fun onReadTale(list: ArrayList<soundItem>)
    }

    // read
    fun read(
        token: String,
        readCallback: ReadCallback?
    )

    interface ReadCallback {
        fun onRead(list: ArrayList<voiceItem>)
    }

    // readVoice

    // create

    // update

    // delete
}