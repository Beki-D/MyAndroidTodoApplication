package com.example.mytodoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        recyclerVwTodoItems.adapter = todoAdapter
        recyclerVwTodoItems.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val todoText = inputField.text.toString()
            if (todoText.isNotEmpty()) {//item.isEmpty() checks only the length of the the string. item.isBlank() checks the length and that all the chars are whitespaces.
                val todo = Todo(todoText)
                todoAdapter.addTodo(todo)
                inputField.text.clear()
            }
        }

        todoAdapter.onItemClick(object : OnClickListener {
            override fun openItem(position: Int) {
                Toast.makeText(applicationContext, "" + position, Toast.LENGTH_SHORT).show()
            }
        })
    }
}