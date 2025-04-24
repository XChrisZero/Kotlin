package br.com.tads.appalertas

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciando os elementos da interface
        val btnModo = findViewById<Button>(R.id.btnModo)



        // Referenciando a tela E mudando a cor de fundo
        val tela = findViewById<View>(R.id.tela)
        tela.setBackgroundColor(Color.DKGRAY)


        //Criando uma caixa de alerta
        val caixa = AlertDialog.Builder(this)


        //Definindo o titulo desta caixa
        caixa.setTitle("Disparanto um alerta!")

        //Definindo a mensagem
        caixa.setMessage("toque em um botão para obter uma resposta.")

        //definindo o botão com ação positiva
        caixa.setPositiveButton("Claro") {_,_ ->
            Toast.makeText(this, "Modo Claro.", Toast.LENGTH_LONG).show()
            tela.setBackgroundColor(Color.WHITE)
        }



        //definindo o botão com ação negativa
        caixa.setNegativeButton("Escuro") {_,_ ->
            Toast.makeText(this, "Modo Escuro.", Toast.LENGTH_LONG).show()
            tela.setBackgroundColor(Color.DKGRAY)
        }


        //definindo o botão com a  ação neutra
        caixa.setNeutralButton("Cancelar") {_,_ ->
            Toast.makeText(this, "botão Cancelar foi utilizado.", Toast.LENGTH_LONG).show()

        }


        // definindo o clique do botão btnModo
        btnModo.setOnClickListener {

            //Exebindo o Alerta
            caixa.show()
        }



    }
}












------------------------------------------------------------------------------------------------
  <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/tela"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnModo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="16dp"
        android:background="#801C1C"
        android:text="Modo"
        app:icon="@drawable/ic_launcher_foreground"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageButton
        android:id="@+id/imgbtn"
        android:layout_width="74dp"
        android:layout_height="74dp"
        android:backgroundTint="#0FAAED"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.5"
        app:srcCompat="@drawable/ic_launcher_foreground"
        tools:srcCompat="@drawable/ic_launcher_foreground"
        tools:visibility="visible" />
</androidx.constraintlayout.widget.ConstraintLayout>
