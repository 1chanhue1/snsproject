package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val akbkbaaa = findViewById<ImageView>(R.id.iv_logo1)

        akbkbaaa.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val signUp = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_up1)
        val check = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_check)

        val nameEditText = findViewById<TextInputEditText>(R.id.nameInputEditText)
        val idEditText = findViewById<TextInputEditText>(R.id.idInputEditText)
        val pwEditText = findViewById<TextInputEditText>(R.id.pwInputEditText)
        val mbtiEditText = findViewById<TextInputEditText>(R.id.mbtiInputEditText)

        check.setOnClickListener {

            val userId = idEditText.text.toString()

            if (Database.getUserInfo(userId) != null) {
                idEditText.error = "이미 존재하는 아이디입니다."

            } else {
                idEditText.error = null
                Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {

            if (nameEditText.text.toString().isNullOrBlank()) {
                nameEditText.error = "이름을 입력해주세요."
                return@setOnClickListener

            }

            if (idEditText.text.toString().isNullOrBlank()) {
                idEditText.error = "아이디를 입력해주세요."
                return@setOnClickListener

            }

            if (pwEditText.text.toString().isNullOrBlank()) {
                pwEditText.error = "비밀번호를 입력해주세요."
                return@setOnClickListener

            }

            if (mbtiEditText.text.toString().isNullOrBlank()) {
                pwEditText.error = "mbti를 입력해주세요."
                return@setOnClickListener

            }

            val userId = idEditText.text.toString()

            if (Database.getUserInfo(userId) != null) {
                idEditText.error = "이미 존재하는 아이디입니다."
                return@setOnClickListener
            }

            nameEditText.error = null
            idEditText.error = null
            pwEditText.error = null
            mbtiEditText.error = null

//            회원 가입 정보 넘겨주기?

//            val addUser = Database.addUserInfo(UserInfo(userId, userName = "", userPw = "", userProfile = null ,userMbti = ""))
//
//            Database.addUserInfo(addUser)

            Toast.makeText(this, getString(R.string.toast_signup_complete), Toast.LENGTH_SHORT).show()

            intent.putExtra("idEditText", idEditText.text.toString())
            intent.putExtra("pwEditText", pwEditText.text.toString())

            setResult(RESULT_OK, intent)

            if (!isFinishing) finish()
        }
    }
}