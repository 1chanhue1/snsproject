package com.akbkbaaa.snsproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvUserId = findViewById<TextView>(R.id.tv_user_id)
        val ivUserPhoto1 = findViewById<ImageView>(R.id.iv_post1)
        val ivUserPhoto2 = findViewById<ImageView>(R.id.iv_post2)
        val tvUserPost1 = findViewById<TextView>(R.id.tv_post1)
        val tvUserPost2 = findViewById<TextView>(R.id.tv_post2)


        val userId=intent.getStringExtra("userId")




    }
}