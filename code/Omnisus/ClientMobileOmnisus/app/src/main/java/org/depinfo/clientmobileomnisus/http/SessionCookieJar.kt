package org.depinfo.clientmobileomnisus.http

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.ArrayList

object SessionCookieJar : CookieJar {

    private var cookies: MutableList<Cookie> = mutableListOf()
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie?>) {
        this.cookies = ArrayList<Cookie>(cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie?> {
        return cookies
    }
}