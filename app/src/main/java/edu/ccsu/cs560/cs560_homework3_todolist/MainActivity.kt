package edu.ccsu.cs560.cs560_homework3_todolist

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val todoListItems = ArrayList<String>()
    lateinit var todoListAdapter: ArrayAdapter<String>

    private val TAG = "MainActivity"
    private val REQUEST_CODE = 565
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gson = Gson()

        loadData()

        todoListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoListItems)

        todo_list.adapter = todoListAdapter


        todo_list.setOnItemLongClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position).toString()

            todoListItems.removeAt(position)

            todoListAdapter.notifyDataSetChanged()

            if (todoListAdapter.count == 0)
                Toast.makeText(this, "All tasks are completed!", Toast.LENGTH_SHORT).show()

            saveData()

            return@setOnItemLongClickListener true

        }
    }

    fun addTask(view: View) {
        var myIntent = Intent(this, AddToDoItem::class.java)

        startActivityForResult(myIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newItems = data?.getStringArrayListExtra("newItems")
            if (newItems != null) {
                todoListItems.addAll(newItems)
                todoListAdapter.notifyDataSetChanged()
                saveData()
            }

        }
    }

    /**
     * Loads existing data from a shared preferences file
     */
    fun loadData() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        var todoListJson = sharedPreferences.getString("existingItems", null)

        if (!todoListJson.isNullOrBlank()) {
            val stype = object : TypeToken<ArrayList<String>>() {}.type

            val existingItems = gson.fromJson<ArrayList<String>>(todoListJson, stype)

            todoListItems.addAll(existingItems)
        }

    }

    /**
     * Saves data into shared preferences file
     */
    fun saveData() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // deletes existing preferences
        editor.clear()

        // saves new list
        editor.putString("existingItems", gson.toJson(todoListItems))

        editor.apply()
    }
}
