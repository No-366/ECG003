package com.example.ecg002

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ecg002.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://172.33.1.34:8000")//서버 주소 입력
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var signupService = retrofit.create(LoginService::class.java)

        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root) // 뷰 바인딩을 통해 레이아웃 설정


        binding.btnRegister.setOnClickListener() {
            var textId = binding.initID.text.toString()
            var textPw = binding.initPw.text.toString()

            signupService.requestSignUp(textId, textPw).enqueue(object : Callback<Signup> {

                //웹통신 실패시
                override fun onFailure(call: Call<Signup>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@SignUpActivity)
                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패하였습니다.")
                    dialog.show()
                }


                //웹통신 성공, 응답값을 받아온다
                //아이디 중복시 회원가입 실패
                override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                    var login = response.body()//code, msg
                    if (response.isSuccessful) {
                        val message = response.body() // Success message from the server
                        // Handle success message
                    } else {
                        // Handle error response
                    }



                    var dialog = AlertDialog.Builder(this@SignUpActivity)
                    dialog.setTitle("알람!")
                    dialog.setMessage("code = " + login?.code2 + " msg = " + login?.msg2)//응답값이null일 수도 있으므로.. => 응답값을 띄워주는 창
                    dialog.show()
                }
            })
        }
    }
}