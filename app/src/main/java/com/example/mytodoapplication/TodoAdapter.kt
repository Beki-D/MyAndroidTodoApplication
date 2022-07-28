package com.example.mytodoapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.titleTextView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View, listener: OnClickListener ): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                listener.openItem(adapterPosition)
            }
        }
    }

    private lateinit var onclicklistener:OnClickListener

    fun onItemClick(Listener: OnClickListener){
        onclicklistener = Listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            ),
            onclicklistener
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        var title = holder.itemView.titleTextView
        title.text = currentTodo.title
        holder.itemView.deleteButton.setOnClickListener {
            todos.removeAt(position)
            notifyDataSetChanged()
        }
        //Everything related to checkbox
        var checkBox = holder.itemView.checkboxDone
        checkBox.setOnCheckedChangeListener { _, _ ->
            if(checkBox.isChecked){
                title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        //Edit button
        var editBtn = holder.itemView.editButton
        editBtn.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}