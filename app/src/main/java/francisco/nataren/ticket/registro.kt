package francisco.nataren.ticket

import RecyclerViewHelpers.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.dataClassTickets

class registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val imgBack = findViewById<ImageView>(R.id.imgVolver)
        imgBack.setOnClickListener {
            val pantallaAtras = Intent(this, tickets::class.java)
            startActivity(pantallaAtras)
        }



        val rcvTickets = findViewById<RecyclerView>(R.id.rcvTickets)

        rcvTickets.layoutManager = LinearLayoutManager(this)

        fun mostrarDatos(): List<dataClassTickets> {

            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()

            val resultSet = statement?.executeQuery("SELECT * FROM tickets")!!

            val tickets = mutableListOf<dataClassTickets>()

            while(resultSet.next()) {
                val uuid = resultSet.getString("uuid")
                val numeroTicket = resultSet.getString("numeroTicket")
                val titulo = resultSet.getString("tituloTicket")
                val descripcion = resultSet.getString("descripcionTicket")
                val autor = resultSet.getString("autorTicket")
                val email = resultSet.getString("emailAutor")
                val estado = resultSet.getString("estadoTicket")

                val ticket = dataClassTickets(uuid, numeroTicket, titulo, descripcion, autor, email, estado)

                tickets.add(ticket)

            }

            return tickets
        }

        CoroutineScope(Dispatchers.IO).launch {

            val ticketDB = mostrarDatos()

            withContext(Dispatchers.Main) {
                val miAdaptador = Adaptador(ticketDB)
                rcvTickets.adapter = miAdaptador
            }
        }
    }



}
