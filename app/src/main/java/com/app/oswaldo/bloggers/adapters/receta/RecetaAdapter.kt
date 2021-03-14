package com.app.oswaldo.bloggers.adapters.receta

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.oswaldo.bloggers.databinding.ItemRecetaListBinding
import com.app.oswaldo.bloggers.model.Receta
import com.bumptech.glide.Glide

@SuppressLint("SetTextI18n")
class RecetaAdapter : RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {

    private var items: List<Receta> = ArrayList()
    private lateinit var context: Context
    private lateinit var iCardReceta: ICardReceta

    fun recyclerAdapter(items: List<Receta>, context: Context, iCardReceta: ICardReceta) {
        this.items = items
        this.context = context
        this.iCardReceta = iCardReceta
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, context, iCardReceta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecetaListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val itemBinding: ItemRecetaListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Receta, context: Context, iCardReceta: ICardReceta) {
            Glide.with(context).load(item.image).into(itemBinding.imgReceta);
            itemBinding.txtTitle.text = item.name
            itemBinding.card.setOnClickListener {
                iCardReceta.onItemTouch(item)
            }
        }

    }
}