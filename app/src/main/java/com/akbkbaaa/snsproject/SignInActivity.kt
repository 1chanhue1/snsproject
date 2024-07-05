package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val signIn = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_in)
        val signUp = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_up2)

        val idInputLayout = findViewById<TextInputLayout>(R.id.idInputLayout)
        val pwInputLayout = findViewById<TextInputLayout>(R.id.pwInputLayout)

        val idInputEditText = findViewById<EditText>(R.id.idInputEditText)
        val pwInputEditText = findViewById<EditText>(R.id.pwInputEditText)

//        로그인 버튼 눌렀을 때 동작 부분

        signIn.setOnClickListener {

            val idInputText = idInputEditText.text.toString()
            val pwInputText = pwInputEditText.text.toString()

            if (idInputText.isBlank()) {
                idInputLayout.error = "아이디를 입력해주세요."
                return@setOnClickListener

            }

            if (pwInputText.isBlank()) {
                pwInputLayout.error = "비밀번호를 입력해주세요."
                return@setOnClickListener

            }

            idInputLayout.error = null
            pwInputLayout.error = null

            val userInfo = Database.getUserInfo(idInputText)

            if (userInfo != null && userInfo.userPw == pwInputText) {
                Database.setCurrentUserId(idInputText)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_up_ggilmon, R.anim.slide_none_ggilmon)
                finish()

            } else {
                pwInputLayout.error = "아이디 또는 비밀번호가 올바르지 않습니다."
            }
        }

//        회원 가입 버튼 눌렀을 때 동작 부분

        signUp.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            overridePendingTransition(R.anim.slide_up_ggilmon, R.anim.slide_none_ggilmon)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val id = result.data?.getStringExtra("idEditText") ?: ""
                    val pw = result.data?.getStringExtra("pwEditText") ?: ""

                    idInputEditText.setText(id)
                    pwInputEditText.setText(pw)
                }
            }
    }
}