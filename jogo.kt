XML
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/rootLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF">

    <TextView
        android:id="@+id/scoreText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Pontos: 0"
        android:textSize="24sp"
        android:layout_gravity="top|center_horizontal"
        android:layout_marginTop="24dp"
        android:textColor="#000000"/>

    <View
        android:id="@+id/circle"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:background="@drawable/circulo"
        android:layout_gravity="top|left"/>
</FrameLayout>

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
xml CIRCULO DRAWMBLE
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="#FF0000"/>
</shape>

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
kotlin main



import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sign
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //Propriedades Elementos de UI
    private lateinit var circle: View
    private lateinit var scoreText: TextView
    private lateinit var rootLayout: FrameLayout

    //Propriedades Matemáticas
    private var score = 0
    private val handler = Handler(Looper.getMainLooper())
    private val updateDelay: Long = 16L // ~60 FPS
    private var dx = 4f // velocidade inicial horizontal
    private var dy = 4f // velocidade inicial vertical
    private val speedIncrement = 1f // quanto aumenta a cada toque
    private val moveRunnable = object : Runnable {
        override fun run() {
            moveCircle()
            handler.postDelayed(this, updateDelay)
        }
    }

    //Métod0 onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Elementos de interface
        circle = findViewById(R.id.circle)
        scoreText = findViewById(R.id.scoreText)
        rootLayout = findViewById(R.id.rootLayout)

        //Toque no círculo
        circle.setOnClickListener {
            score++
            scoreText.text = "Pontos: $score"
            increaseSpeed()
        }

        //Randomizando direção do Circulo
        rootLayout.post {
            randomizeDirection()
            handler.post(moveRunnable)
        }
    }

    //Métod0 para a movimentação do Círculo
    private fun moveCircle() {

        //Difinição de Direções
        val layoutParams = circle.layoutParams as FrameLayout.LayoutParams
        var newX = layoutParams.leftMargin + dx.toInt()
        var newY = layoutParams.topMargin + dy.toInt()
        val maxX = rootLayout.width - circle.width
        val maxY = rootLayout.height - circle.height

        // Rebater horizontalmente
        if (newX <= 0) {
            newX = 0
            dx = -dx
        } else if (newX >= maxX) {
            newX = maxX
            dx = -dx
        }

        // Rebater verticalmente
        if (newY <= 0) {
            newY = 0
            dy = -dy
        } else if (newY >= maxY) {
            newY = maxY
            dy = -dy
        }

        //Captando os limites do Layout
        layoutParams.leftMargin = newX
        layoutParams.topMargin = newY
        circle.layoutParams = layoutParams
    }

    //Métod0 para aumentar a velocidade
    private fun increaseSpeed() {
        // Mantém o sinal e aumenta o valor absoluto da velocidade
        dx += speedIncrement * dx.sign
        dy += speedIncrement * dy.sign
    }

    //Métod0 para randomizar a direção da rebatida
    private fun randomizeDirection() {
        dx = if (Random.nextBoolean()) 4f else -4f
        dy = if (Random.nextBoolean()) 4f else -4f
    }

    //Métod0 onDestroy
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(moveRunnable)
    }
}

