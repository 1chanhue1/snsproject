package com.akbkbaaa.snsproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MyPageActivity : AppCompatActivity() {
    private lateinit var myPageLogo: ImageView

    private lateinit var profile: ImageView
    private lateinit var name: TextView
    private lateinit var postCount: TextView

    private lateinit var imageViews: List<ImageView>
    private lateinit var myPageImage1: ImageView
    private lateinit var myPageImage2: ImageView
    private lateinit var myPageImage3: ImageView
    private lateinit var myPageImage4: ImageView

    private lateinit var logout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_page)

        val userId = intent.getStringExtra("userId")!!
        init()
        setImage(userId)
        setProfile(userId)

        myPageLogo.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        logout.setOnClickListener {
            Database.setCurrentUserId("")
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }


        //
    }

    private fun init() {
        myPageLogo = findViewById(R.id.myPage_logo)

        profile = findViewById(R.id.myPage_profile)
        name = findViewById(R.id.myPage_profile_name)
        postCount = findViewById(R.id.myPage_profile_post_count)

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
            Glide.with(this)
                .load(i)
                .centerCrop()
                .into(imageViews[index])
        }


        postCount.setText("${getString(R.string.myhPage_photo)} ${photos.size}")
    }
    private fun setProfile(userId:String){
        val userInfo = Database.getUserInfo(userId)!!
        if(userInfo.userProfile!=null) profile.setImageResource(userInfo.userProfile)
        name.text = userInfo.userName
        userInfo.userProfile?.let {
            Glide.with(this)
                .load(it)
                .centerCrop()
                .into(profile)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}