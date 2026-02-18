package org.depinfo.clientmobileomnisus.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object SessionCookieJar : CookieJar {

    private var cookies: MutableList<Cookie> = mutableListOf()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = ArrayList(cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}
