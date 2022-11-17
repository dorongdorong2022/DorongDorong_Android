package kr.co.younhwan.a9oormthon.data.source.main

import java.util.UUID

object MainRepository : MainSource {
    private val maybeRemoteDataSource = MainRemoteDataSource

    override fun readKey(uuid:String, readKeyCallback: MainSource.ReadKeyCallback?) {
        maybeRemoteDataSource.readKey(
            uuid,
            object : MainSource.ReadKeyCallback{
                override fun onReadKey(token: String) {
                    readKeyCallback?.onReadKey(token)
                }
            }
        )
    }

    override fun read(readCallback: MainSource.ReadCallback?) {
        maybeRemoteDataSource.read(
            object : MainSource.ReadCallback {
                override fun onRead() {
                    readCallback?.onRead()
                }
            }
        )
    }
}