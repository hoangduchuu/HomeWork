package net.hdhuu.domain.repository

import io.reactivex.Observable


/**
 * Created by Huu Hoang on 2/24/19.
 */
interface UserRepository{
    fun register(userName :String, phone:String, password:String): Observable<Boolean>
}