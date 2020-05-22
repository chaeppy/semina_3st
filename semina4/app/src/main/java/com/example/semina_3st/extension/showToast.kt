package com.example.semina_3st.extension

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg : String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}