package com.example.semina_3st

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.semina_3st.data.RequestRegister
import com.example.semina_3st.extension.showToast
import com.example.semina_3st.network.RequestToServer
import com.example.semina_3st.network.customEnqueue
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val requestToServer = RequestToServer
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register.setOnClickListener {
            if(et_id.text.isNullOrBlank()||et_pwd.text.isNullOrBlank()
                ||et_email.text.isNullOrBlank() ||et_name.text.isNullOrBlank()
                ||et_phone.text.isNullOrBlank()) {
                showToast("정보를 모두 입력하세요")
            }
            else if(!et_pwd.text.toString().equals(et_checkpwd.text.toString())){
                showToast("비밀번호가 일치하지 않습니다")
            }
            else{
                requestToServer.service.requestRegister(
                    RequestRegister(
                        id = et_id.text.toString(),
                        password = et_pwd.text.toString() ,
                        name = et_name.text.toString() ,
                        email = et_email.text.toString(),
                        phone = et_phone.text.toString()
                    )
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다")},
                    onSuccess = {
                        if(it.success) {
                            Log.d("RegisterActivity", et_id.text.toString())
                            intent.putExtra("email", et_id.text.toString())
                            intent.putExtra("pwd", et_pwd.text.toString())
                            setResult(Activity.RESULT_OK, intent)
                            showToast(it.message)
                            finish()
                        }
                        else{
                            showToast(it.message)
                            //질문이있습니다..
                            // status == 400, 필요한 값이 없습니다라는 메세지도 여기서 판별해서 출력하고 싶은데 어떻게 하면 좋을까요?
                            // status == 400, 필요한 값이 없습니다라는 메세지는 어떤 값이 넘어가야 나오는 걸까요..?
                            //requestRegister클래스 변수를 null이 가능하게 바꾸고 변수에 값을 대입할때 et_email.text?.toString()으로 했는데 안되더라구요

                        }
                    }
                )
            }
        }
    }
}
