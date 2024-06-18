package modelo

data class dataClassTickets(

    val uuid: String,
    val numeroTicket: String,
    var titulo: String,
    var descripcion: String,
    var autor: String,
    var email: String,
    var estado: String
)
