package com.example.flukepc.coworkadmin.ui.comment

import com.example.flukepc.coworkadmin.base.BaseContractor
import com.example.flukepc.coworkadmin.model.CommentList

class CommentContact {
    interface Presenter : BaseContractor.Presenter<View> {
        fun callCommentApi(coWorkingId : String)
    }

    interface View : BaseContractor.View {
        fun onCallCommentListSuccess(listComment : CommentList?)
    }
}