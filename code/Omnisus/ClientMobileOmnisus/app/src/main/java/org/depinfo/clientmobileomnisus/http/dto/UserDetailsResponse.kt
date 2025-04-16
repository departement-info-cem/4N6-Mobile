package org.depinfo.clientmobileomnisus.http.dto


class UserDetailsResponse {
    var publicName: String? = null
    var username: String? = null
    var grades: List<GradeResponse> = mutableListOf()
}