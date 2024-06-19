package francisco.nataren.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Informacion : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val tituloRecibido = intent.getStringExtra("titulo")
        val numeroRecibido = intent.getStringExtra("numeroTicket")
        val descripcionRecibida = intent.getStringExtra("descripcion")
        val autorRecibido = intent.getStringExtra("autor")
        val emailRecibido = intent.getStringExtra("email")
        val estadoRecibido = intent.getStringExtra("estado")

        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val tvNumero = findViewById<TextView>(R.id.tvNumero)
        val tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        val tvAutor = findViewById<TextView>(R.id.tvAutor)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvEstado = findViewById<TextView>(R.id.tvEstado)

        tvTitulo.text = tituloRecibido
        tvNumero.text = numeroRecibido
        tvDescripcion.text = descripcionRecibida
        tvAutor.text = autorRecibido
        tvEmail.text = emailRecibido
        tvEstado.text = estadoRecibido

        val imgVolverAtras = findViewById<ImageView>(R.id.imgVolverAtras)
        imgVolverAtras.setOnClickListener {
            val volver = Intent(this, registro::class.java)
            startActivity(volver)
        }


    }
}