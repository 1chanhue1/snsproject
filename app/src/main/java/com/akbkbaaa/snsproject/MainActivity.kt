package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mypageButton = findViewById<ImageView>(R.id.my_page)
        mypageButton.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            val userId = Database.getCurrentUserId()
            intent.putExtra("userId", userId)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        val chanhueProfile = findViewById<ImageView>(R.id.chanhue)
        setProfileImage(chanhueProfile, "1chanhue1")
        chanhueProfile.setOnClickListener {
            startDetailActivity("1chanhue1")
        }

        val ggilggilmonsterProfile = findViewById<ImageView>(R.id.ggilggilmonster)
        setProfileImage(ggilggilmonsterProfile, "ggilggilmonster")
        ggilggilmonsterProfile.setOnClickListener {
            startDetailActivity("ggilggilmonster")
        }

        val kim4152Profile = findViewById<ImageView>(R.id.kim4152)
        setProfileImage(kim4152Profile, "kim4152")
        kim4152Profile.setOnClickListener {
            startDetailActivity("kim4152")
        }

        val dyaossProfile = findViewById<ImageView>(R.id.dyaoss)
        setProfileImage(dyaossProfile, "Dyaoss")
        dyaossProfile.setOnClickListener {
            startDetailActivity("Dyaoss")
        }

        val postContainer = findViewById<LinearLayout>(R.id.postContainer)
        val posts = Database.getAllPosts()

        for (post in posts) {
            val postView = LayoutInflater.from(this).inflate(R.layout.item_feed, postContainer, false)

            val userNameView = postView.findViewById<TextView>(R.id.username)
            val postImageView = postView.findViewById<ViewPager>(R.id.viewPager)
            val postContentView = postView.findViewById<TextView>(R.id.postContent)
            val dotsLayout = postView.findViewById<LinearLayout>(R.id.dotsLayout)

            val userImageView = postView.findViewById<ImageView>(R.id.userImage) // userImage 추가
            // 사용자 프로필 이미지 설정
            setProfileImage(userImageView, post.userId)

            userNameView.text = post.userId
            postContentView.text =  "${userNameView.text}: ${post.content}"


            val adapter = ViewPager(this, post.photos)
            postImageView.adapter = adapter

            // 인디케이터 추가 및 초기화
            addDots(dotsLayout, adapter.count)
            updateDotsOnPageChange(postImageView, dotsLayout)
            postContainer.addView(postView)
        }
    }

    private fun startDetailActivity(userId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }

    private fun setProfileImage(imageView: ImageView, userId: String) {
        val userInfo = Database.getUserInfo(userId)
        userInfo?.userProfile?.let {
            imageView.setImageResource(it)
        } ?: imageView.setImageResource(R.drawable.sample_image)
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

    private fun updateDotsOnPageChange(viewPager: ViewPager, dotsLayout: LinearLayout) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
