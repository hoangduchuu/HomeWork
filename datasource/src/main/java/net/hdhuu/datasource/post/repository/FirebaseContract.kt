package net.hdhuu.datasource.post.repository

import io.reactivex.Observable

/**
 * Created by Huu Hoang on 2/24/19.
 */
interface FirebaseContract {
    fun login(userName:String, phone:String, password:String ): Observable<Boolean>
    fun register(userName:String, phone:String, password:String ): Observable<Boolean>
    fun submitUserInfo(userName: String,phone: String,password: String)
}