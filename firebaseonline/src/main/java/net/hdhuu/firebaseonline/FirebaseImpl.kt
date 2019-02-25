package net.hdhuu.firebaseonline

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import io.reactivex.Observable
import io.reactivex.functions.Function
import net.hdhuu.datasource.post.repository.FirebaseContract
import rxfirebase2.auth.RxFirebaseAuth
import rxfirebase2.database.RxFirebaseDatabase

/**
 * Created by Huu Hoang on 2/24/19.
 */
class FirebaseImpl(val ref: DatabaseReference, val authen: FirebaseAuth) : FirebaseContract {
    override fun submitUserInfo(userName: String, phone: String, password: String) {
//        RxFirebaseDatabase.setValue()
    }

    override fun login(userName: String, phone: String, password: String): Observable<Boolean> {
        if (authen.currentUser == null) {
            Log.e("null or not null?", "null")
        } else {
            Log.e("null or not null?", "not null")

        }

        return RxFirebaseDatabase.setValue(ref.child(phone), userName).toObservable()

    }

    @SuppressLint("LongLogTag")
    override fun register(userName: String, phone: String, password: String): Observable<Boolean> {
        ref.setValue("huune")
        return Observable.create { emitter ->
            authen.createUserWithEmailAndPassword(userName, password).addOnFailureListener { exception ->
                emitter.onError(exception)
            }.addOnCompleteListener {
                emitter.onComplete()
            }.addOnSuccessListener { emitter.onNext(true) }
        }
    }


}