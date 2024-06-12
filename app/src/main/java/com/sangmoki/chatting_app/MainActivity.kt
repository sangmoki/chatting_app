package com.sangmoki.chatting_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // firebase 라이브러리 객체 생성
    private lateinit var auth: FirebaseAuth

    // 디버깅 위한 TAG 생성
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // firebase 인증 객체 생성
        auth = Firebase.auth

        // 현재 로그인 상태 확인
        val currentUser = auth.currentUser

        // 로그인 버튼 객체 생성
        val login_button = findViewById<Button>(R.id.login_button)

        // 회원가입 버튼 객체 생성
        val join_button = findViewById<Button>(R.id.join_button)


        // 이메일 비밀번호 입력 값
        var email = ""
        var password = ""

        email = findViewById<EditText>(R.id.email_area).text.toString()
        password = findViewById<EditText>(R.id.password_area).text.toString()

        // 로그인 버튼 클릭 이벤트
        login_button.setOnClickListener {

            // 로그인 이벤트 -> 성공 시 로그인 성공 실패 시 로그인 실패
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "로그인 성공")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "로그인 실패", task.exception)
                        Toast.makeText(
                            baseContext,
                            "로그인 실패",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }

        // 회원가입 버튼 클릭 이벤트
        join_button.setOnClickListener {

            // 신규 회원일 경우 회원가입 성공, 이미 있을 경우 실패
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "회원가입 성공")
                    } else {
                        Log.d(TAG, "실패")
                    }
                }

        }




    }
}