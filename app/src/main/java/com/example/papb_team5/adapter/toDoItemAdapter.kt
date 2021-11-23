package com.example.papb_team5.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.MainActivity
import com.example.papb_team5.R
import com.example.papb_team5.detailTugas
import com.example.papb_team5.fragment_home
import com.example.papb_team5.model.Affirmation

class toDoItemAdapter(
    private val context: MainActivity,
    private val dataset: List<Affirmation>,
    private val onClick: ((titleName: String) -> Unit)? = null
    ): RecyclerView.Adapter<toDoItemAdapter.ItemViewHolder>()
{

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView15)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_view, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        /*
        holder.itemView.setOnClickListener{
            when(position){
                0 -> {
                    onClick?.invoke(holder.textView.text as String)
                }
                else -> return@setOnClickListener
            }*/
        holder.itemView.setOnClickListener{
            val context = holder.view.context

            val intent = Intent(context, detailTugas::class.java)
            intent.putExtra(detailTugas.LETTER, holder.textView.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount() =  dataset.size
}