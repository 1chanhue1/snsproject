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
        val tvUsername = findViewById<TextView>(R.id.tv_user_name)
        val tvUserMbti = findViewById<TextView>(R.id.tv_user_mbti)
        val tvUserSpec = findViewById<TextView>(R.id.tv_user_spec)
        val ivUserPhoto1 = findViewById<ImageView>(R.id.iv_post1)
        val ivUserPhoto2 = findViewById<ImageView>(R.id.iv_post2)
        val tvUserPost1 = findViewById<TextView>(R.id.tv_post1)
        val tvUserPost2 = findViewById<TextView>(R.id.tv_post2)

        var userPost = Database.getPosts("1chanhue1")
        var userInfo = Database.getUserInfo("1chanhue1")

        var userPhoto = mutableListOf<Int>()
        var userContent = mutableListOf<String>()

        userPost.forEach { post -> userContent.add(post.content)}

        tvUserPost1.setText("1chanhue1  ${userContent[0]}")
        tvUserPost2.setText("1chanhue1  ${userContent[1]}")

        userPost.forEach {post ->
            post.photos.forEach { photo->
                userPhoto.add(photo)
            }
        }
        ivUserPhoto1.setImageResource(userPhoto[0])
        ivUserPhoto2.setImageResource(userPhoto[2])

        var userId = userInfo?.userId
        var userName = userInfo?.userName
        var userMbti = userInfo?.userMbti
        var userSpec = userInfo?.userProfile

        tvUserId.setText("$userId")
        tvUsername.setText("$userName")
        tvUserMbti.setText("$userMbti")
        tvUserSpec.setText("$userSpec")

        //val userId=intent.getStringExtra("userId")


    }
}