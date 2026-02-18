package org.depinfo.clientmobileomnisus.api

data class SigninRequest(
    var username: String? = null,
    var password: String? = null
)

data class GradeResponse(
    var gradeId: Long? = null,
    var grade: Int? = null,
    var name: String? = null
)

data class UserDetailsResponse(
    var publicName: String? = null,
    var username: String? = null,
    var grades: List<GradeResponse> = mutableListOf()
)
