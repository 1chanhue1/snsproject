package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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

//        중복 확인 부분
//        val check = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_check)

//        check.setOnClickListener {  }

        val signUp1 = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_sign_up1)

        signUp1.setOnClickListener {

            val nameEditText = findViewById<EditText>(R.id.editName)
            val idEditText = findViewById<EditText>(R.id.editId1)
            val pwEditText = findViewById<EditText>(R.id.editPw1)

            if (nameEditText.text.toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_signup_name), Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if (idEditText.text.toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_signup_id), Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if (pwEditText.text.toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_signup_pw), Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                Toast.makeText(this, getString(R.string.toast_signup_complete), Toast.LENGTH_SHORT).show()

                intent.putExtra("ID", idEditText.text.toString())
                intent.putExtra("PW", pwEditText.text.toString())

                setResult(RESULT_OK, intent)

                if (!isFinishing) finish()
            }
        }
    }
}