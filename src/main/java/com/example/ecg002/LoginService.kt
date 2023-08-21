package com.example.ecg002
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//인풋을 만든다
interface LoginService {

    @FormUrlEncoded
    @POST("/app.login/")//어떤 통신인지 설명 : post 통신 사용
    fun requestLogin(
        //인풋을 정의하는 부분
        //필드의 이름이 서버에서 POST로 받는 이름과 같아야한다!
        @Field("userid") userid:String,
        @Field("userpw") userpw:String
    ) : Call<Login>//아웃풋을 정의하는 부분

    @FormUrlEncoded
    @POST("/app.Signup/")
    fun requestSignUp(
        @Field("registid") registid: String,
        @Field("registpw") registpw: String
    ) : Call<Signup>
}
