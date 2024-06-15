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

        // 로그인 버튼 클릭 이벤트
        login_button.setOnClickListener {

            email = findViewById<EditText>(R.id.email_area).text.toString()
            password = findViewById<EditText>(R.id.password_area).text.toString()

            // 로그인 이벤트 -> 성공 시 로그인 성공 실패 시 로그인 실패
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    Log.d("이메일 ======", email)
                    Log.d("비밀번호 ======", password)

                    if (task.isSuccessful) {

                        Toast.makeText(
                            baseContext,
                            "로그인 성공",
                            Toast.LENGTH_SHORT,
                        ).show()

                        // 로그인이 성공하면 채팅 액티비티로 이동한다.
                        val intent = Intent(this, ChatListActivity::class.java)
                        // 새 탭으로 이동할 때 Flag를 지워주어야 한다.
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {
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

            email = findViewById<EditText>(R.id.email_area).text.toString()
            password = findViewById<EditText>(R.id.password_area).text.toString()

            // 신규 회원일 경우 회원가입 성공, 이미 있을 경우 실패
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "회원가입 성공",
                            Toast.LENGTH_SHORT,
                        ).show()

                        // 회원가입이 성공하면 채팅 액티비티로 이동한다.
                        val intent = Intent(this, ChatListActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            baseContext,
                            "회원가입 실패",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }




    }
}