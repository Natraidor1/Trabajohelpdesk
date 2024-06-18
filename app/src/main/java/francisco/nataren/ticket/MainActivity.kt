package francisco.nataren.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val txtUsuario = findViewById<EditText>(R.id.txtUsuario)

        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaa)


        val btnLogin = findViewById<Button>(R.id.btnIniciarSesion)


        btnLogin.setOnClickListener {
            val pantallaPrincipal = Intent(this, tickets::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()


                val checkUser = objConexion?.prepareStatement("SELECT * FROM Usuarios WHERE nombre_usuario = ? AND contraseña_usuario = ?")!!
                checkUser.setString(1, txtUsuario.text.toString())
                checkUser.setString(2, txtContrasena.text.toString())

                val resultado = checkUser.executeQuery()

                if(resultado.next()) {
                    startActivity(pantallaPrincipal)
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Usuario o contraseña fueron escritos de manera incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }
}