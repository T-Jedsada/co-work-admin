package com.example.flukepc.coworkadmin.ui.home

import com.example.flukepc.coworkadmin.base.BaseContractor

interface MainContractor: BaseContractor.View{
    interface Presenter : BaseContractor.Presenter<View> {
        fun validateForm(email: String, password: String)
        fun callApi(email : String , password :String)
    }

    interface View : BaseContractor.View{
        fun successFormVerify(email : String, password :String)
        fun successLogin(message : String)
        fun onError(message : String)
    }
}