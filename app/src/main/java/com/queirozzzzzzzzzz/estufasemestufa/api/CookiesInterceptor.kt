package com.queirozzzzzzzzzz.estufasemestufa.api

import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import okhttp3.Interceptor
import okhttp3.Response

class CookiesInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val cookies = Preferences.getAuthCookie()
        if (cookies != null) {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Cookie", cookies.toString())
                    .build()
            )
        } else {
            proceed(request())
        }
    }

}
