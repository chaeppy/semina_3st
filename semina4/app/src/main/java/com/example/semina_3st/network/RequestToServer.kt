package com.example.semina_3st.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//object: 싱글톤 이용가능
//싱글톤? 모든 액티비티에서 이 하나의 객체를 갖다가 쓴다. 여러개 안만드니까 메모리 절약 가능
object RequestToServer{
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.209.144.115:3333") //건물의 주소.
        .addConverterFactory(GsonConverterFactory.create()) //retrofit으로 받아온 json데이터를 데이터 클래스로 변환하기 쉽게 해줌
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java) //retrofit.create호출해 interface클래스 타입을 넘겨 실제 구현체를 만들어준다.
}