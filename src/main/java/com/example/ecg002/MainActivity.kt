package com.example.ecg002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.ecg002.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//xml 뷰와 연결

        //retrofit 객체 만들기
        var retrofit = Retrofit.Builder()
            .baseUrl("https://172.33.1.34:8000")//서버 주소 입력
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //retrofit 객체에 create를 통해 서비스를 올려준다 => 서비스 인터페이스 만들기
        var loginService = retrofit.create(LoginService::class.java)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogIn.setOnClickListener(){
            var textId = binding.tvId.text.toString()
            var textPw = binding.tvPw.text.toString()

            loginService.requestLogin(textId, textPw).enqueue(object: Callback<Login>{

                //웹통신 실패시
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패하였습니다.")
                    dialog.show()
                }


                //웹통신 성공, 응답값을 받아온다
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    var login = response.body()//code, msg

                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("알람!")
                    dialog.setMessage("code = " + login?.code + "msg = " + login?.msg)//응답값이null일 수도 있으므로.. => 응답값을 띄워주는 창
                    dialog.show()
                }
            })

            //val intent = Intent(this, MypageActivity::class.java) //화면전환

            //intent.putExtra("WMI", binding.tvId.text.toString())
            //startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener(){
            val intent2 = Intent(this, SignUpActivity::class.java) //화면전환
            startActivity(intent2)
        }


    }
}