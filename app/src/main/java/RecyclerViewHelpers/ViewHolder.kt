package RecyclerViewHelpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import francisco.nataren.ticket.R

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val txtTitulos: TextView =view.findViewById(R.id.txtTickets)
    val txtEstados: TextView =view.findViewById(R.id.txtEstados)
    val imgEditar: TextView =view.findViewById(R.id.imgEditar)
    val imgEliminar: TextView =view.findViewById(R.id.imgEliminar)
}
