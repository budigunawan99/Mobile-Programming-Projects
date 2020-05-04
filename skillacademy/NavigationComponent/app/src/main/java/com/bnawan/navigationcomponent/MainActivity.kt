package com.bnawan.navigationcomponent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnawan.navigationcomponent.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val user = User(name="Budi Gunawan", age=32)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_open.setOnClickListener { toDetail() }
    }

    private fun toDetail() {
        startActivity(
            Intent(this,DetailActivity::class.java)
                .apply { putExtra(User::class.java.simpleName, user) }
        )
    }
}
