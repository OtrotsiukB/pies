package AcademyPies.com

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RcForSimpleList     // super();
    (
    private val Users:Array<String>

) :
    RecyclerView.Adapter<RcForSimpleList.ViewHolder>() {
    private var listener: Listener? = null

    interface Listener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_simple_user_list, parent, false) as CardView
        return ViewHolder(cv)//rv_addPieActivity
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val userTextForRC = cardView.findViewById<TextView>(R.id.rv_forRC)
        userTextForRC.text=Users[position]

        //////////////////////////////
        /* cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if (listener != null) { listener.onClick(position);}});
    }
        /////////////////////////////// */
        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }


        /////////
    }

    override fun getItemCount(): Int { //сколько табличек
        return Users.size
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    class ViewHolder//cardView = (CardView)itemView;
    //Определить представление для каждого элемента данных
    //public ViewHolder(@NonNull View itemView) {
    //public ViewHolder(@NonNull CardView itemView) {
        (var cardView: CardView) : RecyclerView.ViewHolder(cardView)
}