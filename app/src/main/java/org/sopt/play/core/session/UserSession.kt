package org.sopt.play.core.session

// 앱이 켜져 있는 동안 로그인한 사용자 정보를 들고 있는 곳
object UserSession {
    // 회원가입 때 입력한 이름 → 게시글 author로 사용
    var name: String = ""

    // 로그인 성공 시 발급받는 인증 코드 → Authorization 헤더로 사용
    var authorizationCode: String? = null
}
