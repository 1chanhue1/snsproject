package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signIn =
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_in)
        val signUp =
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_up2)

        val idInputLayout = findViewById<TextInputLayout>(R.id.idInputLayout)
        val pwInputLayout = findViewById<TextInputLayout>(R.id.pwInputLayout)

        val idInputEditText = findViewById<EditText>(R.id.idInputEditText)
        val pwInputEditText = findViewById<EditText>(R.id.pwInputEditText)

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

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Database.setCurrentUserId(idInputText)
        }

//        로그인 회원 정보에 저장된 거만 예외처리로

        signUp.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
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