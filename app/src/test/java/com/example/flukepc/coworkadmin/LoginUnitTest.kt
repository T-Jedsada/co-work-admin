package com.example.flukepc.coworkadmin

 import com.example.flukepc.coworkadmin.base.BaseService
import com.example.flukepc.coworkadmin.request.RequestLogin
import com.example.flukepc.coworkadmin.ui.main.MainContractor
import com.example.flukepc.coworkadmin.ui.main.MainPresenter
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginUnitTest {
    private val api = mock(BaseService::class.java)
    private var loginPresenter: MainPresenter
    @InjectMocks
    private val view = mock(MainContractor.View::class.java)
    private val request = RequestLogin(api)
    private var mockEmail = "aoi@sora.miyabi"
    private var mockPassword = "missrolol"

    init {
        MockitoAnnotations.initMocks(this)
        loginPresenter = MainPresenter(request)
        loginPresenter.attachView(view)
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
    fun longerPasswordShouldGoErrorFunction() {
        mockPassword = "pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.password_longer)
    }

    @Test
    fun emptyPasswordShouldGoErrorFunction() {
        mockPassword = ""
        loginPresenter.validateForm(mockEmail, mockPassword)
        verify(view, times(1))?.onError(R.string.password_invalid)
    }
}