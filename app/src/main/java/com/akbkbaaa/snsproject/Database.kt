package com.akbkbaaa.snsproject

object Database {
    //회원가입 한 유저 정보
    private val userInfos: MutableList<UserInfo> = mutableListOf(
        UserInfo("1chanhue1", "김찬휘", "1234",R.drawable.img,"ISFP"),
        UserInfo("ggilggilmonster", "정용찬", "1234",R.drawable.img3,"INFJ"),
        UserInfo("kim4152", "김정호", "1234",R.drawable.img4,"ISFJ"),
        UserInfo("Dyaoss", "김대현", "1234",R.drawable.img2,"ISFP"),
    )
    fun getUserInfo(userId: String): UserInfo? {
        return userInfos.find { it.userId == userId } //결과값이 없으면 null 반환
    }
    fun addUserInfo(userInfo: UserInfo) {
        userInfos.add(userInfo)
    }


    //현재 로그인 한 사용자 아이디
    private var currentUserId: String = ""
    fun getCurrentUserId():String{
        return currentUserId
    }
    fun setCurrentUserId(currentUserId:String){
        this.currentUserId = currentUserId
    }

    //게시글 정보
    private val posts: List<Post> = listOf(
        Post("1chanhue1", listOf(R.drawable.kch_11, R.drawable.kch_12), "뉴진스 콘서트 다녀옴"),
        Post("1chanhue1", listOf(R.drawable.kch_21, R.drawable.kch_22), "맛집 추천"),

        Post("ggilggilmonster", listOf(R.drawable.ggilmon1, R.drawable.ggilmon2), "Posty 💥💥"),
        Post("ggilggilmonster", listOf(R.drawable.ggilmon3, R.drawable.ggilmon4), "Oh my Bruno😭😭"),

        Post("kim4152", listOf(R.drawable.kjh_11, R.drawable.kjh_12), "설악산!! 좋았지만 다시는 가지 않을거야"),
        Post("kim4152", listOf(R.drawable.kjh_11, R.drawable.kjh_12), "복붙"),

        Post("Dyaoss", listOf(R.drawable.kdh_11, R.drawable.kdh_12 ), "오늘도 야구장! 한화 화이팅"),
        Post("Dyaoss", listOf(R.drawable.kdh_21, R.drawable.kdh_22 ), "하와이 또가고 싶다."),

    )
    fun getAllPosts():List<Post>{
        return posts
    }
    fun getPosts(userId:String):List<Post>{
        return posts.filter { it.userId == userId } //결과값이 없으면 빈 리스트 반환: []
    }


}

data class UserInfo(val userId: String, val userName: String, val userPw: String, val userProfile: Int?, val userMbti: String)
data class Post(val userId: String, val photos: List<Int>, val content: String)

