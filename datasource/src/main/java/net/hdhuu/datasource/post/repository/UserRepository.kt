package net.hdhuu.datasource.post.repository

import io.reactivex.Observable
import net.hdhuu.domain.repository.UserRepository

/**
 * Created by Huu Hoang on 2/24/19.
 */
class UserRepository(val firebaseContract: FirebaseContract) : UserRepository {
    override fun register(userName: String, phone: String, password: String): Observable<Boolean> {
        return firebaseContract.register(userName,phone,password)
    }
}