package com.akbkbaaa.snsproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signIn = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_signin)

        val idEditText = findViewById<EditText>(R.id.editId2)
        val pwEditText = findViewById<EditText>(R.id.editPw)

        signIn.setOnClickListener {

            val idInput = findViewById<EditText>(R.id.editId2)
            val strData = idInput.text.toString()

            if (idEditText.text.toString().trim().isEmpty() || pwEditText.text.toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_idpwerr), Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                Toast.makeText(this, getString(R.string.toast_signin_success), Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("dataFromFirstActivity", strData)
                startActivity(intent)
            }
        }

        val signUp = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_signup)

        signUp.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val ID = result.data?.getStringExtra("ID") ?: ""
                    val PW = result.data?.getStringExtra("PW") ?: ""

                    idEditText.setText(ID)
                    pwEditText.setText(PW)
                }
            }
    }
}