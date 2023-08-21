package com.example.ecg002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecg002.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root) // 뷰 바인딩을 통해 레이아웃 설정


        if (intent.hasExtra("WMI")){
            binding.txtMypage.text = "${intent.getStringExtra("WMI")}님 환영합니다"
        }
    }
}