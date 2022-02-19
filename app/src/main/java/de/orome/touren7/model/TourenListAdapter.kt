package de.orome.touren7.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import de.orome.touren7.R
import de.orome.touren7.databinding.ItemTourenListBinding
import de.orome.touren7.model.database.entity.Tour

class TourenListAdapter(
    private val tourenListe: List<Tour>,
    private val clickListener: (Tour)-> Unit): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTourenListBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.item_touren_list,null,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tourenListe[position], clickListener)
    }

    override fun getItemCount(): Int {
        return tourenListe.size
    }
}

class MyViewHolder(val binding: ItemTourenListBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(tour: Tour, clickListener: (Tour) -> Unit){
        binding.apply {
            tvItemTourTourNummer.text = tour.tourNummer
            tvItemTourTourDatum.text = tour.tourDatum
            tvItemTourTourStatus.text = "in Bearbeitung"
            // TOD: Nach Erweiterung des Models Wert übergeben
            // Dazu muss das Layout eine ID erhalten!!
            listItemLayout.setOnClickListener {
                clickListener(tour)
            }
        }



    }
}