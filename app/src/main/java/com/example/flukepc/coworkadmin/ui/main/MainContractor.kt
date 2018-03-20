package com.example.flukepc.coworkadmin.ui.main

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.DataLogin

interface MainContractor: BaseContractor.View{
    interface Presenter : BaseContractor.Presenter<View> {
        fun validateForm(email: String, password: String)
        fun callApi(email : String , password :String)
    }

    interface View : BaseContractor.View{
        fun successFormVerify(email : String, password :String)
        fun successLogin(data:DataLogin)
        fun onError(message : Int)
    }
}