package com.example.papb_team5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.R
import com.example.papb_team5.data_entity.Schedule
import com.example.papb_team5.data_entity.Task
import kotlinx.android.synthetic.main.todo_view.view.*

class scheduleItemAdapter(
    private val context: Context,
    private val schedules: ArrayList<Schedule>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<scheduleItemAdapter.ItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val itemSchedule = schedules[position]
        holder.view.textView15.text = itemSchedule.taskTitle
        holder.itemView.setOnClickListener{
            listener.onClick(itemSchedule)
        }

        val button: View = holder.view.findViewById(R.id.todo_elipsis_layout)

        button.setOnClickListener{
            val popupMenu = PopupMenu(context, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.nav_edit ->{
                        //Toast.makeText(context, "Edit Button pressed", Toast.LENGTH_SHORT).show()
                        listener.onUpdate(itemTask)
                        true
                    }
                    R.id.nav_delete -> {
                        listener.onDelete(itemTask)
                        Toast.makeText(context, "Task deletion", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.todo_popup_menu)
            popupMenu.show()
        }

    }
    override fun getItemCount() =  tasks.size

    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Task>){
        tasks.clear()
        tasks.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(task: Task)
        fun onUpdate(task: Task)
        fun onDelete(task: Task)
    }
}