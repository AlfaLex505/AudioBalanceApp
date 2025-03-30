import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.audiobalanceapp.AudioBalanceHelper
import com.example.audiobalanceapp.R


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerBalance: Spinner = findViewById(R.id.spinnerBalance)
        val btnAplicar: Button = findViewById(R.id.btnAplicar)

        // Opciones del ComboBox (Spinner)
        val opciones = arrayOf(
            "50% de Balance a la Izquierda",
            "50% de Balance a la Derecha"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spinnerBalance.adapter = adapter

        val AudioBalanceHelper = AudioBalanceHelper(this)

        btnAplicar.setOnClickListener {
            val seleccion = spinnerBalance.selectedItem.toString()

            when (seleccion) {
                "50% de Balance a la Izquierda" -> AudioBalanceHelper.setBalance(-0.5f)
                "50% de Balance a la Derecha" -> AudioBalanceHelper.setBalance(0.5f)
            }
        }
    }
}
