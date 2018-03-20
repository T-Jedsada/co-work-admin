package com.example.flukepc.coworkadmin.ui.confirm

import android.content.Intent
import android.widget.Toast
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.base.BaseActivity
import com.example.flukepc.coworkadmin.di.ApplicationComponent
import com.example.flukepc.coworkadmin.model.CommentList
import com.example.flukepc.coworkadmin.model.DataCoWorkJudgeComment
import com.example.flukepc.coworkadmin.ui.comment.CommentContact
import com.example.flukepc.coworkadmin.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_confirm.*

class ConfirmActivity : BaseActivity<ConfirmContact.View, ConfirmPresenter>(),ConfirmContact.View {
    override fun successApiRejectComment(data: DataCoWorkJudgeComment) {
        data.message?.let {
            Toast.makeText(this , it ,Toast.LENGTH_SHORT).show()
        }
        data.error?.let {
            Toast.makeText(this , it ,Toast.LENGTH_SHORT).show()
        }
        startActivity(Intent(this,HomeActivity::class.java))
    }

    override fun layoutContentView(): Int = R.layout.activity_confirm

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {

        confirm.setOnClickListener {
            val rejectComment = intent.extras?.getString("key")
            val rejectCoWork = intent.extras?.getString("reject-co")
            val approveCoWork = intent.extras?.getString("approve-co")

            rejectComment?.let {
                presenter.callApiRejectComment(it)
            }
            rejectCoWork?.let {
                presenter.callApiRejectCoWork(it)
            }
            approveCoWork?.let {
                presenter.callApiApproveCoWork(it)
            }
        }
        noConfirm.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

    }
}