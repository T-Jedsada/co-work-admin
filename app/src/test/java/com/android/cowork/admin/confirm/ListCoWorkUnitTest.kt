package com.android.cowork.admin.confirm

import com.android.cowork.admin.R
import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.model.JudgeResponse
import com.android.cowork.admin.model.JudgeResponseBody
import com.android.cowork.admin.model.ListCoWork
import com.android.cowork.admin.request.Request
import com.android.cowork.admin.ui.home.approve.ApproveContact
import com.android.cowork.admin.ui.home.approve.ApprovePresenter
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
    private var approvePresenter: ApprovePresenter

    @InjectMocks
    private val view = mock(ApproveContact.View::class.java)
    private val request = Request(api)

    private val mockCoWorkId = "12"

    init {
        MockitoAnnotations.initMocks(this)
        approvePresenter = ApprovePresenter(request)
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
    fun showListCoWorkWithSuccessResponseCodeShouldGoSuccessMethod() {
        val response = ListCoWork(true)
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.success(response)))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).successCallback(response)
    }

    @Test
    fun showListCoWorkWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun showListCoWorkWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"), "NOT FOUND"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun showListCoWorkWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.requestCoWorkList())
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"), "InternalServerError"))))
        approvePresenter.callListCoWorkApi()
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_500)
    }

    @Test
    fun approveSuccessShouldBeCallMethodIsJudgeSuccess() {
        val mockReturnModelData = JudgeResponseBody("done", null)
        val mockReturnModel = JudgeResponse("true", mockReturnModelData)
        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).isJudgeSuccess(mockReturnModelData.message)
    }

    @Test
    fun approveFailedShouldBeCallMethodOnError() {
        val mockReturnModelData = JudgeResponseBody("so bad man!!", null)
        val mockReturnModel = JudgeResponse("false", mockReturnModelData)
        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.txt_api_error)
    }

    @Test
    fun rejectSuccessShouldBeCallMethodIsJudgeSuccess() {
        val mockReturnModelData = JudgeResponseBody("He He He ~~~~ Complete", null)
        val mockReturnModel = JudgeResponse("true", mockReturnModelData)
        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).isJudgeSuccess(mockReturnModelData.message)
    }

    @Test
    fun rejectFailedShouldBeCallMethodOnError() {
        val mockReturnModelData = JudgeResponseBody("so bad man!!", null)
        val mockReturnModel = JudgeResponse("false", mockReturnModelData)
        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.txt_api_error)
    }

    @Test
    fun approveCoWorkWithSuccessResponseCodeShouldGoSuccessMethod() {
        val mockReturnModelData = JudgeResponseBody("He He He ~~~~ Complete", null)
        val mockReturnModel = JudgeResponse("true", mockReturnModelData)

        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).isJudgeSuccess(mockReturnModelData.message)
    }

    @Test
    fun approveCoWorkWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun approveCoWorkWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun approveCoWorkWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmApprove(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onApprove(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_500)
    }

    @Test
    fun rejectCoWorkWithSuccessResponseCodeShouldGoSuccessMethod() {
        val mockReturnModelData = JudgeResponseBody("He He He ~~~~ Complete", null)
        val mockReturnModel = JudgeResponse("true", mockReturnModelData)

        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.success(mockReturnModel)))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).isJudgeSuccess(mockReturnModelData.message)
    }

    @Test
    fun rejectCoWorkWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun rejectCoWorkWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun rejectCoWorkWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        Mockito.`when`(api.sendToConfirmReject(mockCoWorkId))
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"), "Unauthorized"))))
        approvePresenter.onReject(mockCoWorkId)
        Mockito.verify(view, Mockito.times(1)).onError(R.string.http_wrong_connect_500)
    }
}