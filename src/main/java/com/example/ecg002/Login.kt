package com.example.ecg002

//아웃풋을 만든다
data class Login(
    var code : String,
    var msg : String
    //json에서의 키값과 같아야 데이터 넘어옴 !(참고)
)
//회원가입용 아웃풋
data class Signup(
    var code2 : String,
    var msg2 : String
)