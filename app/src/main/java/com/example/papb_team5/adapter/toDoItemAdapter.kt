package com.example.papb_team5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.MainActivity
import com.example.papb_team5.R
import com.example.papb_team5.data_entity.Task
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.todo_view.view.*

class toDoItemAdapter(
    private val context: Context,
    private val tasks: ArrayList<Task>,
    private val listener: OnAdapterListener
    ): RecyclerView.Adapter<toDoItemAdapter.ItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = tasks[position]
        holder.view.textView15.text = item.taskTitle
        holder.itemView.setOnClickListener{
            listener.onClick(item)
        }

        /*
        holder.bind(item.taskTitle, item.taskDescription)

        holder.titleTextView.text = context.resources.getString(item.id)
        holder.descTextView?.text = context.resources.getString(item.id)


        holder.itemView.setOnClickListener{
            val context = holder.view.context

            val intent = Intent(context, detailTugas::class.java)
            intent.putExtra(detailTugas.TITLE, holder.titleTextView.text.toString())
            intent.putExtra(detailTugas.DESC, holder.descTextView?.text.toString())
            context.startActivity(intent)
        }*/

    }
    override fun getItemCount() =  tasks.size


    /*class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val titleTextView: TextView = view.findViewById(R.id.textView15)
        val descTextView: TextView? = view.findViewById(R.id.txt_infoDetail)

        fun bind(title: String?, desc: String?) {
            titleTextView.text = title
            descTextView?.text = desc

        }
    }*/
    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Task>){
        tasks.clear()
        tasks.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(task: Task)
    }




}