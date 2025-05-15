package br.com.tads.cerveja

import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class MainActivity : AppCompatActivity(), SensorEventListener {

    //Criando o Sensor
    private lateinit var gerenciadorSensor: SensorManager
    private var giroscopio: Sensor? = null

    //Criando o imageview vazio
    private lateinit var imageView: ImageView

    //Criando a rotação zero e seus limites
    private var rotacaoZ = 0f
    private var ultimoTimestamp: Long = 0L
    private val limiteRotacao = 20f

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //Travando a Orientação da Tela no caso sempre em retrato
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //Referenciando a ImageView
        imageView = findViewById(R.id.imageView)

        //Iniciando o Sensor (giroscopio)
        gerenciadorSensor = getSystemService(SENSOR_SERVICE) as SensorManager
        giroscopio = gerenciadorSensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        giroscopio?.let {
            gerenciadorSensor.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }


    }

    //Métod0 disparado sempre que o sensor muda de posição
    override fun onSensorChanged(evento: SensorEvent?) {
        if (evento?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val intervaloTempo = if (ultimoTimestamp != 0L)
                (evento.timestamp - ultimoTimestamp) / 1_000_000_000f
            else
                0f
            ultimoTimestamp = evento.timestamp

            val taxaRotacaoZ = evento.values[2]
            rotacaoZ += taxaRotacaoZ * intervaloTempo * 57.2958f // rad/s → graus

            // Aplica os limites de rotação
            rotacaoZ = rotacaoZ.coerceIn(-limiteRotacao, limiteRotacao)

            imageView.rotation = rotacaoZ
        }
    }


    //Métod0 obrigatório: Verifica se a precisão do senseor foi alterada
    override fun onAccuracyChanged(sensor: Sensor?, precisao: Int) {
        // Ignorado
    }


    //Desativando o sensor em segundo plano
    override fun onPause() {
        super.onPause()
        gerenciadorSensor.unregisterListener(this)
    }

    //Reativando o sensor em primeiro plano
    override fun onResume() {
        super.onResume()
        giroscopio?.also {

     
            gerenciadorSensor.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

}




--- XML


  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="800dp"
        android:layout_height="1000dp"
        android:scaleType="centerCrop"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/cerva" />
</androidx.constraintlayout.widget.ConstraintLayout>
