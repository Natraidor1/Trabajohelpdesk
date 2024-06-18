package francisco.nataren.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
import java.util.UUID

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val btnCrear = findViewById<Button>(R.id.btnCrearCuenta)

        val txtUsername = findViewById<EditText>(R.id.txtCrearUsuario)

        val txtPassword = findViewById<EditText>(R.id.txtContrasena)



        btnCrear.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()


                val crearUsuario = objConexion?.prepareStatement("INSERT INTO Usuarios(UUID, nombre_usuario, contraseña_usuario) VALUES (?, ?, ?)")!!

                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2, txtUsername.text.toString())
                crearUsuario.setString(3, txtPassword.text.toString())
                crearUsuario.executeUpdate()

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@Registrarse,
                        "Usuario creado exitosamente",
                        Toast.LENGTH_LONG
                    ).show()

                    txtUsername.text.clear()
                    txtPassword.text.clear()
                }
            }
        }
    }
}