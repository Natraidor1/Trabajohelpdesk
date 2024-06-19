package francisco.nataren.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNumero = findViewById<EditText>(R.id.txtNumero)
        val txtTitulo = findViewById<EditText>(R.id.txtTitulo)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val txtAutor = findViewById<EditText>(R.id.txtAutor)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtEstado = findViewById<EditText>(R.id.txtEstado)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnVerRegistro = findViewById<Button>(R.id.btnVerTickets)

        fun eliminarlosCampos() {
            txtNumero.text.clear()
            txtTitulo.text.clear()
            txtDescripcion.text.clear()
            txtAutor.text.clear()
            txtEmail.text.clear()
            txtEstado.text.clear()
        }

        btnRegistrar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()

                val addTicket = objConexion?.prepareStatement("INSERT INTO Tickets (uuid, numeroTicket, tituloTicket, descripcionTicket, autorTicket, emailAutor, estadoTicket) VALUES (?, ?, ?, ?, ?, ?, ?)")!!

                addTicket.setString(1, UUID.randomUUID().toString())
                addTicket.setString(2, txtNumero.text.toString())
                addTicket.setString(3, txtTitulo.text.toString())
                addTicket.setString(4, txtDescripcion.text.toString())
                addTicket.setString(5, txtAutor.text.toString())
                addTicket.setString(6, txtEmail.text.toString())
                addTicket.setString(7, txtEstado.text.toString())

                addTicket.executeUpdate()
                eliminarlosCampos()
            }


        }

        btnVerRegistro.setOnClickListener {
            val pantallaRegistro = Intent(this, registro::class.java)
            startActivity(pantallaRegistro)
        }
    }
}
