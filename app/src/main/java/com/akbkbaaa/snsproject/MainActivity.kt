package com.akbkbaaa.snsproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var dotsLayout: LinearLayout
    private val numOfDots = 2 // 이미지 갯ㅍㅁ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dotsLayout = findViewById(R.id.dots_layout)

        addDots(numOfDots)

        val mypagebutton = findViewById<ImageView>(R.id.my_page)
        mypagebutton.setOnClickListener {

            val intent = Intent(this, MyPageActivity::class.java)
            val userId=Database.getCurrentUserId()
            intent.putExtra("userId", userId)
            startActivity(intent)


        }

        val sampleid= findViewById<ImageView>(R.id.sample_id)
        sampleid.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("userId", "Dyaoss")
            startActivity(intent)
        }




    }

    private fun addDots(count: Int) {
        for (i in 0 until count) {
            val dot = ImageView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)
            dot.layoutParams = params
            dot.setImageResource(if (i == 0) R.drawable.circle_selected else R.drawable.circle_unselected)
            dotsLayout.addView(dot)
        }
    }


}