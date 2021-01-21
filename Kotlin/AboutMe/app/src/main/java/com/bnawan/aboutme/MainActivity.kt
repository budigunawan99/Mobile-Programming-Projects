package com.bnawan.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bnawan.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val name: Name = Name("Budi Gunawan")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myname = name
//        findViewById<Button>(R.id.done_btn).setOnClickListener {
//            addNickName(it)
//        }
        binding.doneBtn.setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {
//        val editText:EditText = findViewById(R.id.nickname_edit)
//        val nickName = findViewById<TextView>(R.id.nickname_text)

//        nickName.text = editText.text
//        editText.visibility = View.GONE
//        view.visibility = View.GONE
//        nickName.visibility = View.VISIBLE

        binding.apply {
//            nicknameText.text = nicknameEdit.text
            myname?.nickname = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            //refresh all binding data
            invalidateAll()
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}