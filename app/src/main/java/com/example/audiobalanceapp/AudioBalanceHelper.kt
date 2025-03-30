package com.example.audiobalanceapp

import android.content.Context
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Build
import androidx.annotation.RequiresApi

class AudioBalanceHelper(private val context: Context) {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    @RequiresApi(Build.VERSION_CODES.P) // API 28+ (Android 9+)
    fun setBalance(balance: Float) {
        // El balance debe estar en el rango -1.0 (izquierda) a 1.0 (derecha)
        val clampedBalance = balance.coerceIn(-1.0f, 1.0f)

        val sessionId = AudioManager.AUDIO_SESSION_ID_GENERATE
        val sampleRate = 44100
        val bufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize,
            AudioTrack.MODE_STREAM,
            sessionId
        )

        // Configurar balance de audio (Izquierda -1.0, Centro 0.0, Derecha 1.0)
        audioTrack.setStereoVolume(
            if (clampedBalance < 0) 1.0f + clampedBalance else 1.0f, // Volumen izquierdo
            if (clampedBalance > 0) 1.0f - clampedBalance else 1.0f  // Volumen derecho
        )

        audioTrack.play()
    }
}
