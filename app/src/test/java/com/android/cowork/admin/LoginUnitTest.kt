package com.android.cowork.admin

import com.android.cowork.admin.base.network.BaseService
import com.android.cowork.admin.model.ResponseDataLogin
import com.android.cowork.admin.request.Request
import com.android.cowork.admin.ui.main.MainContractor
import com.android.cowork.admin.ui.main.MainPresenter
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
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class LoginUnitTest {

    private val api = mock(BaseService::class.java)
    @Inject
    private var loginPresenter: MainPresenter

    @InjectMocks
    private val view = mock(MainContractor.View::class.java)
    private val request = Request(api)
    private var mockEmail = "admin@admin.admin"
    private var mockPassword = "123456"

    init {
        MockitoAnnotations.initMocks(this)
        loginPresenter = MainPresenter(request)
        loginPresenter.attachView(view)
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
    fun testRequestApiWhenSomeValueWasWrongShouldGoToMethodShowErrorOnViewClass() {
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.success(ResponseDataLogin("false", null))))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).onError(R.string.txt_api_error)
    }

    @Test
    fun testRequestApiWhenSomeValueWasCorrectShouldGoToMethodShowSuccessOnViewClass() {
        val mockResponse = ResponseDataLogin("true", null)
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.success(mockResponse)))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).successLogin(mockResponse)
    }

    @Test
    fun testRequestApiWhenApiUnauthorizedShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.error(401, ResponseBody.create(MediaType.parse("text"),"Unauthorized" ))))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).onError(R.string.http_wrong_connect_401)
    }

    @Test
    fun testRequestApiWhenApiNotFoundShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.error(404, ResponseBody.create(MediaType.parse("text"),"NOT FOUND" ))))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).onError(R.string.http_wrong_connect_404)
    }

    @Test
    fun testRequestApiWhenApiInternalServerErrorShouldGoToMethodShowErrorOnViewClassForShowSomeMessage() {
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.error(500, ResponseBody.create(MediaType.parse("text"),"Internal Server Error" ))))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).onError(R.string.http_wrong_connect_500)
    }

    @Test
    fun testRequestApiWhenApiSuccessShouldGoSuccessMethod() {
        val mockResponse = ResponseDataLogin("true", null)
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.success(mockResponse)))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).successLogin(mockResponse)
    }
    @Test
    fun testEmptyListResponse() {
        `when`(api.verifyLogin(mockEmail, mockPassword))
                .thenReturn(Observable.just(Response.success(ResponseDataLogin("false", null))))
        loginPresenter.callApi(mockEmail, mockPassword)
        verify(view, times(1)).onError(R.string.txt_api_error)
    }

    @Test
    fun emptyEmailShouldGoErrorFunction() {
        mockEmail = ""
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.email_invalid)
    }

    @Test
    fun invalidFormatEmailShouldGoErrorFunction() {
        mockEmail = "pp"
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.email_format_invalid)
    }

    @Test
    fun validFormatEmailShouldGoSuccessFunction() {
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.successFormVerify(mockEmail, mockPassword)
    }

    @Test
    fun shorterPasswordShouldGoErrorFunction() {
        mockPassword = "a"
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.password_shorter)
    }

    @Test
    fun emptyPasswordShouldGoErrorFunction() {
        mockPassword = ""
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.password_invalid)
    }
}