package com.akbkbaaa.snsproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MyPageActivity : AppCompatActivity() {
    private lateinit var profile: ImageView
    private lateinit var name: EditText
    private lateinit var postCount: EditText
    private lateinit var photoCount: EditText

    private lateinit var imageViews: List<ImageView>
    private lateinit var myPageImage1: ImageView
    private lateinit var myPageImage2: ImageView
    private lateinit var myPageImage3: ImageView
    private lateinit var myPageImage4: ImageView

    private lateinit var logout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_page)

        val userId = Intent().getStringExtra("userId")!!
        init()
        setImage(userId)
        setProfile(userId)

        logout.setOnClickListener {
            Database.setCurrentUserId("")
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun init() {
        profile = findViewById(R.id.myPage_profile)
        name = findViewById(R.id.myPage_profile_name)
        postCount = findViewById(R.id.myPage_profile_post_count)
        photoCount = findViewById(R.id.myPage_profile_photo_count)

        myPageImage1 = findViewById(R.id.my_page_img1)
        myPageImage2 = findViewById(R.id.my_page_img2)
        myPageImage3 = findViewById(R.id.my_page_img3)
        myPageImage4 = findViewById(R.id.my_page_img4)

        imageViews = listOf(myPageImage1,myPageImage2,myPageImage3,myPageImage4)

        logout = findViewById(R.id.myPage_logout)
    }

    private fun setImage(userId:String){

        val posts = Database.getPosts(userId)

        val photos = mutableListOf<Int>()

        posts.forEach {post ->
            post.photos.forEach { photo->
                photos.add(photo)
            }
        }

        photos.forEachIndexed { index, i ->
            imageViews[index].setImageResource(i)
        }

        postCount.setText("게시물 ${posts.size}")
        photoCount.setText("사진 ${photos.size}")
    }
    private fun setProfile(userId:String){
        val userInfo = Database.getUserInfo(userId)!!
        if(userInfo.userProfile!=null) profile.setImageResource(userInfo.userProfile)
        name.setText( userInfo.userName)
    }
}