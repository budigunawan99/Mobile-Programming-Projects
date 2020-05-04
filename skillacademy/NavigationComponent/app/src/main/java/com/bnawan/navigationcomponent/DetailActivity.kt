package com.bnawan.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnawan.navigationcomponent.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar2)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        initialModel()
    }

    private fun initialModel() {
        val model = intent.getParcelableExtra<User>(User::class.java.simpleName)

        val name = "My name is ${model?.name}"
        tv_name.text = name

        val age = "I am ${model?.age} years old"
        tv_age.text = age
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
