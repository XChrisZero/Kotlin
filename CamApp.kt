//No Arquivo Manifest
    <uses-feature android:name="android.hardware.camera" android:required="false" />
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="28"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>




//Mantenha o seu package!!!

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // ImageView Vazio para abrigar imagens captadas
    private lateinit var imageView: ImageView

    // Endereço da imagem vazio
    private var photoUri: Uri? = null

    // Objeto de acesso à Galeria
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it)
        }
    }

    // Objeto de acesso à Câmera
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success && photoUri != null) {
            imageView.setImageURI(photoUri)
        } else {
            Toast.makeText(this, "Falha ao tirar foto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Elementos da interface
        imageView = findViewById<ImageView>(R.id.imgFoto)
        val btnGallery = findViewById<Button>(R.id.btnGaleria)
        val btnCamera = findViewById<Button>(R.id.btnCam)

        //Ação do botão acessando a galeria
        btnGallery.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        //Ação do botão acessando a camera
        btnCamera.setOnClickListener {
            if (checkCameraPermission()) {
                launchCamera()
            } else {
                requestCameraPermission()
            }
        }
    }

    //Métod0 que chama a câmera
    private fun launchCamera() {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "Nova Foto")
            put(MediaStore.Images.Media.DESCRIPTION, "Foto tirada pela câmera")
        }
        photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        takePictureLauncher.launch(photoUri!!)
    }

    //Métod0 que checa a permissão da câmera
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    //Métod0 que requisita a permissão da câmera
    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA), 100
        )
    }

    //Métod0 que configura a permissão da câmera
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            launchCamera()
        } else {
            Toast.makeText(this, "Permissão da câmera negada", Toast.LENGTH_SHORT).show()
        }
    }
}
