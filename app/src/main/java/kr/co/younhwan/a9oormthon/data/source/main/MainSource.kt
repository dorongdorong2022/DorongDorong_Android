package kr.co.younhwan.a9oormthon.data.source.main

interface MainSource {
    // read
    fun read(
        readCallback: ReadCallback?
    )

    interface ReadCallback {
        fun onRead()
    }

    // create

    // update

    // delete
}