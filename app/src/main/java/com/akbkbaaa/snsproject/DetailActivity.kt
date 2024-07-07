package com.akbkbaaa.snsproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val tvUserId = findViewById<TextView>(R.id.tv_user_id)
        val tvUsername = findViewById<TextView>(R.id.tv_user_name)
        val tvUserMbti = findViewById<TextView>(R.id.tv_user_mbti)

        val ivUserProfilePhoto = findViewById<ImageView>(R.id.iv_user_profile_photo)


        val userGetId = intent.getStringExtra("userId")
        val userInfo = Database.getUserInfo(userGetId.toString())

        // 데이터 베이스에 저장된 프로필 사진 가져오기
        val userProfilePhoto = Database.getUserInfo(userGetId.toString())?.userProfile
        if (userProfilePhoto != null) {
            ivUserProfilePhoto.setImageResource(userProfilePhoto)
        }else{
            ivUserProfilePhoto.setImageResource(R.drawable.sample_image)
        }

        val userId = userInfo?.userId
        val userName = userInfo?.userName
        val userMbti = userInfo?.userMbti

        tvUserId.setText("$userId")
        tvUsername.setText("$userName")
        tvUserMbti.setText("$userMbti")


        val btnBack = findViewById<ImageView>(R.id.iv_dv_logo)

        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }


// 추가 코드
        val postContainer = findViewById<LinearLayout>(R.id.postContainer)
        val posts = Database.getPosts(userGetId.toString()) //아이디 가져오기

        for (post in posts) {
            val postView =
                LayoutInflater.from(this).inflate(R.layout.item_feed, postContainer, false)

            val userNameView = postView.findViewById<TextView>(R.id.username)
            val postImageView =
                postView.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPager)
            val postContentView = postView.findViewById<TextView>(R.id.postContent)
            val dotsLayout = postView.findViewById<LinearLayout>(R.id.dotsLayout)


            val userImageView = postView.findViewById<ImageView>(R.id.userImage) // userImage 추가
// 사용자 프로필 이미지 설정
            val userInfo = Database.getUserInfo(post.userId)
            userInfo?.userProfile?.let {
                userImageView.setImageResource(it)
            } ?: userImageView.setImageResource(R.drawable.sample_image) // 없으면 기본이미지로

            userNameView.text = post.userId
            postContentView.text = "${userNameView.text}: ${post.content}"


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

    private fun updateDotsOnPageChange(
        viewPager: androidx.viewpager.widget.ViewPager,
        dotsLayout: LinearLayout
    ) {
        viewPager.addOnPageChangeListener(object :
            androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

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
