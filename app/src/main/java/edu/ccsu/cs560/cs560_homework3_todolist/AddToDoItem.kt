package edu.ccsu.cs560.cs560_homework3_todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_to_do_item.*

class AddToDoItem : AppCompatActivity() {
    val addedToDos = ArrayList<String>()
    private val TAG = "AddToDoItem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do_item)
    }

    fun save(view: View) {
        if ((inputIsNullorBlank())) {
            Toast.makeText(this, "Please enter something in the text box before trying to save", Toast.LENGTH_SHORT).show()
        } else {
            addedToDos.add(editText.text.toString())
            editText.text.clear()
        }
    }

    fun saveAndGoBack(view: View) {
        if ((inputIsNullorBlank())) {
            Toast.makeText(this, "Please enter something in the text box before trying to save", Toast.LENGTH_SHORT).show()
        } else {
            save(view)
            goBack(view)
        }
    }

    fun goBack(view: View) {
        val intent = Intent()

        intent.putExtra("newItems", addedToDos)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun inputIsNullorBlank(): Boolean {
        return editText.text.isNullOrBlank()
    }
}
