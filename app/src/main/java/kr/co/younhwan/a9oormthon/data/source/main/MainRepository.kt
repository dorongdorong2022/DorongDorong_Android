package kr.co.younhwan.a9oormthon.data.source.main

object MainRepository : MainSource {
    private val maybeRemoteDataSource = MaybeRemoteDataSource

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