package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
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
        val tvUsername = findViewById<TextView>(R.id.tv_user_name)
        val tvUserMbti = findViewById<TextView>(R.id.tv_user_mbti)

        val userGetId = intent.getStringExtra("userId")

        val userInfo = Database.getUserInfo(userGetId.toString())


        var userId = userInfo?.userId
        var userName = userInfo?.userName
        var userMbti = userInfo?.userMbti

        tvUserId.setText("$userId")
        tvUsername.setText("$userName")
        tvUserMbti.setText("$userMbti")

        val btnBack = findViewById<ImageView>(R.id.iv_dv_logo)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


// 추가 코드
        val postContainer = findViewById<LinearLayout>(R.id.postContainer)
        val posts = Database.getPosts(userGetId.toString()) //아이디 가져오기

        for (post in posts) {
            val postView = LayoutInflater.from(this).inflate(R.layout.item_feed, postContainer, false)

            val userNameView = postView.findViewById<TextView>(R.id.username)
            val postImageView = postView.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPager)
            val postContentView = postView.findViewById<TextView>(R.id.postContent)
            val dotsLayout = postView.findViewById<LinearLayout>(R.id.dotsLayout)




            userNameView.text = post.userId
            postContentView.text = post.content

            val adapter = ViewPager(this, post.photos)
            postImageView.adapter = adapter

            // 인디케이터 추가 및 초기화
            addDots(dotsLayout, adapter.count)
            updateDotsOnPageChange(postImageView, dotsLayout)

            postContainer.addView(postView)
        }
    }
    private fun addDots(dotsLayout: LinearLayout, count: Int) {
        val dots = arrayOfNulls<ImageView>(count)

        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]?.setImageResource(R.drawable.dot_inactive)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dots[i], params)
        }

        dots[0]?.setImageResource(R.drawable.dot_active)
    }
    private fun updateDotsOnPageChange(viewPager: androidx.viewpager.widget.ViewPager, dotsLayout: LinearLayout) {
        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateDots(dotsLayout, position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
    private fun updateDots(dotsLayout: LinearLayout, currentPage: Int) {
        val childCount = dotsLayout.childCount
        for (i in 0 until childCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == currentPage) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }

}