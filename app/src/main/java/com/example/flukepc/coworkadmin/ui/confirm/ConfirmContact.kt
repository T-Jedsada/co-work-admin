package com.example.flukepc.coworkadmin.ui.confirm

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.DataCoWorkJudgeComment

interface ConfirmContact{
    interface Presenter : BaseContractor.Presenter<View>{
        fun callApiRejectComment(id:String)
        fun callApiRejectCoWork(id: String)
        fun callApiApproveCoWork(id: String)
    }

    interface View : BaseContractor.View{
        fun successApiRegjectComment(data: DataCoWorkJudgeComment)
    }
}