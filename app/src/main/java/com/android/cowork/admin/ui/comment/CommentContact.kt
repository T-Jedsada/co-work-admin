package com.android.cowork.admin.ui.comment

import com.android.cowork.admin.base.BaseContractor
import com.android.cowork.admin.model.CommentList

class CommentContact {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callCommentApi(coWorkingId : String)
        fun isDeleteComment(id: String?)
    }

    interface View : BaseContractor.View {
        fun onCallCommentListSuccess(listComment : CommentList?)
        fun onCommentDelete(id : String?)
        fun onDeleteSuccess(message : String?)
    }
}