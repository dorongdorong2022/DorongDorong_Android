package kr.co.younhwan.a9oormthon.data.source.main

import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.voiceItem
import java.util.UUID

object MainRepository : MainSource {
    private val maybeRemoteDataSource = MainRemoteDataSource

    override fun readKey(uuid: String, readKeyCallback: MainSource.ReadKeyCallback?) {
        maybeRemoteDataSource.readKey(
            uuid,
            object : MainSource.ReadKeyCallback {
                override fun onReadKey(token: String) {
                    readKeyCallback?.onReadKey(token)
                }
            }
        )
    }

    override fun readVoice(token: String, readVoiceCallback: MainSource.ReadVoiceCallback?) {
        maybeRemoteDataSource.readVoice(
            token,
            object : MainSource.ReadVoiceCallback {
                override fun onReadVoice(list: ArrayList<voiceItem>) {
                    readVoiceCallback?.onReadVoice(list)
                }
            }
        )
    }

    override fun readSound(token: String, readSoundCallback: MainSource.ReadSoundCallback?) {
        maybeRemoteDataSource.readSound(
            token,
            object : MainSource.ReadSoundCallback {
                override fun onReadSound(list: ArrayList<soundItem>) {
                    readSoundCallback?.onReadSound(list)
                }
            }
        )
    }

    override fun readTale(token: String, readTaleCallback: MainSource.ReadTaleCallback?) {

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