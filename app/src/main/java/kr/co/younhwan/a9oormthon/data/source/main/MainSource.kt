package kr.co.younhwan.a9oormthon.data.source.main

interface MainSource {
    // key
    fun readKey(
        uuid: String,
        readKeyCallback: ReadKeyCallback?
    )

    interface ReadKeyCallback {
        fun onReadKey(token: String)
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