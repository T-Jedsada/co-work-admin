package com.android.cowork.admin.comment

import com.android.cowork.admin.R
import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.model.CommentData
import com.android.cowork.admin.model.CommentList
import com.android.cowork.admin.model.JudgeResponse
import com.android.cowork.admin.model.JudgeResponseBody
import com.android.cowork.admin.request.Request
import com.android.cowork.admin.ui.comment.CommentContact
import com.android.cowork.admin.ui.comment.CommentPresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApproveUnitTest {

    private val api = mock(BaseService::class.java)
    @Inject
    private var approvePresenter: CommentPresenter

    @InjectMocks
    private val view = mock(CommentContact.View::class.java)
    private val request = Request(api)

    private val mockCoWorkId = "12"

    init {
        MockitoAnnotations.initMocks(this)
        approvePresenter = CommentPresenter(request)
        approvePresenter.attachView(view)
    }

    @Before
    fun setUp() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun showListCommentWithSuccessResponseCodeShouldGoSuccessMethod() {
        val response = CommentList("true", arrayListOf(CommentData("1","1","1","1","1")))
        Mockito.`when`(api.requestCommentList(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(response)))
        approvePresenter.callCommentApi(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onCallCommentListSuccess(response)
    }

    @Test
    fun showListCoWorkWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCommentList(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.callCommentApi(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun showListCoWorkWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCommentList(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.callCommentApi(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun showListCoWorkWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCommentList(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.callCommentApi(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_500)
    }

    @Test
    fun deleteCommentSuccessShouldBeCallMethodIsJudgeSuccess() {
        val judgementCommentData = JudgeResponseBody("true", "complete")
        val judgementCoWork = JudgeResponse("true", judgementCommentData)

        Mockito.`when`(api.sendJudgementComment(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(judgementCoWork)))
        approvePresenter.isDeleteComment(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onDeleteSuccess(judgementCommentData.message)
    }

    @Test
    fun deleteCommentFailedShouldBeCallMethodIsJudgeSuccess() {
        val judgementCommentData = JudgeResponseBody("failed", "you can't do this")
        val judgementCoWork = JudgeResponse("false", judgementCommentData)

        Mockito.`when`(api.sendJudgementComment(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(judgementCoWork)))
        approvePresenter.isDeleteComment(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.txt_api_error)
    }
}