                                                                                                               XML:
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imvImagem"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_marginTop="70dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@drawable/interrog" />

    <Button
        android:id="@+id/btnTrocar"
        android:layout_width="300dp"
        android:layout_height="50dp"
        android:layout_marginTop="40dp"
        android:text="Trocar Imagem"
        app:icon="@drawable/logo"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imvImagem" />

    <androidx.constraintlayout.widget.Group
        android:id="@+id/group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Switch
        android:id="@+id/onOff"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Switch"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        tools:layout_editor_absoluteY="651dp" />

</androidx.constraintlayout.widget.ConstraintLayout>


--------------------------------------------------------------------------------------------------------------------------------
package br.com.tads.appimagem

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Atributo
    var flagTroca = true
    var onOff = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // trazendo os elementos de interface
        val imvImage = findViewById<ImageView>(R.id.imvImagem)
        val btnTrocar = findViewById<Button>(R.id.btnTrocar)
        //val onOff = findViewById<Switch>(R.id.onOff)

        // LOGICA para alternar as imagens ao tocar no botão
        btnTrocar.setOnClickListener{
            if (flagTroca)
            {
               imvImage.setImageResource(R.drawable.logo)
            }

            else
            {
                imvImage.setImageResource(R.drawable.interrog)
            }
            flagTroca = !flagTroca

        }


    }
}
