import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.audiobalanceapp.R

class MainActivity : AppCompatActivity() {

    private val REQUEST_PERMISSION_CODE = 100

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verificar y solicitar permiso si es necesario
        checkAudioPermission()

        val spinnerBalance: Spinner = findViewById(R.id.spinnerBalance)
        val btnAplicar: Button = findViewById(R.id.btnAplicar)

        // Opciones mejoradas del Spinner
        val opciones = arrayOf(
            "100% Izquierda",
            "50% Izquierda",
            "Centro (Balance normal)",
            "50% Derecha",
            "100% Derecha"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spinnerBalance.adapter = adapter

        val modificarBalance = ModificarBalance(this)

        btnAplicar.setOnClickListener {
            val seleccion = spinnerBalance.selectedItem.toString()

            val balance = when (seleccion) {
                "100% Izquierda" -> -1.0f
                "50% Izquierda" -> -0.5f
                "Centro (Balance normal)" -> 0.0f
                "50% Derecha" -> 0.5f
                "100% Derecha" -> 1.0f
                else -> 0.0f
            }

            modificarBalance.setBalance(balance)
        }
    }

    // Función para verificar y solicitar permisos
    private fun checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.MODIFY_AUDIO_SETTINGS),
                REQUEST_PERMISSION_CODE
            )
        }
    }

    // Manejar la respuesta del usuario sobre los permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permiso concedido
            } else {
                // Permiso denegado
            }
        }
    }
}
