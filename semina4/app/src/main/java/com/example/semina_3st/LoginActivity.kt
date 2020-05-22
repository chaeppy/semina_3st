package com.example.semina_3st

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.semina_3st.data.RequestLogin
import com.example.semina_3st.extension.showToast
import com.example.semina_3st.extension.textChangeListener
import com.example.semina_3st.network.RequestToServer
import com.example.semina_3st.network.customEnqueue
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_pwd

class LoginActivity : AppCompatActivity() {
    val requestToServer = RequestToServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //회원가입 버튼 누를시
        txt_register.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivityForResult(intent,101)
        }

        //아이디가 빈칸인 경우
        et_id.textChangeListener {s-> //원하는 이름으로
            if(s.isNullOrBlank()){
                showToast("아이디가 빈칸이네요")
            }
        }

        //로그인 버튼을 누른 경우
        btn_login.setOnClickListener{
            //아이디나 비밀번호가 빈칸인 경우
            if(et_id.text.isNullOrBlank()||et_pwd.text.isNullOrBlank())
                showToast("아이디와 비밀번호를 확인하세요")
            //빈칸이 아닌 경우 통신함
            else{
                requestToServer.service.requestLogin( //이 함수의 반환 타입은 Call<ResponseLogin>
                    RequestLogin(
                        id = et_id.text.toString(),
                        password = et_pwd.text.toString()
                    ) //로그인 정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if(it.success){ //body()가 null이 아니고, success가 true -> 로그인
                            showToast("로그인 성공")
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{ //body()가 null이 아니지만 success가 아닌경우
                            showToast("아이디/비밀번호를 확인하세요!")
                        }
                    }
                )
            }



        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == Activity.RESULT_OK ){
            val email = data?.getStringExtra("email")
            val pwd = data?.getStringExtra("pwd")

            if(!email.isNullOrEmpty() && !pwd.isNullOrEmpty()){
                et_id.setText(email.toString())
                et_pwd.setText(pwd.toString())
            }
        }
    }
}
