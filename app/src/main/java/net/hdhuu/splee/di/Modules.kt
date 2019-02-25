package net.hdhuu.splee.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import net.hdhuu.cache.post.PostCacheImpl
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.room.TweetDatabase
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.post.repository.PostCache
import net.hdhuu.datasource.post.repository.PostRemote
import net.hdhuu.datasource.post.PostDataStoreFactory
import net.hdhuu.datasource.post.repository.FirebaseContract
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.repository.UserRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor
import net.hdhuu.domain.usecase.DeleteMessageUseCase
import net.hdhuu.domain.usecase.GetPostUseCase
import net.hdhuu.domain.usecase.PostMessageUseCase
import net.hdhuu.domain.usecase.PostMultipleMessageUseCase
import net.hdhuu.domain.usecase.account.RegisterUseCase
import net.hdhuu.firebaseonline.FirebaseImpl
import net.hdhuu.remote.post.PostRemoteImpl
import net.hdhuu.splee.SplitMessage
import net.hdhuu.splee.home.*
import net.hdhuu.splee.home.model.MainViewModel
import net.hdhuu.splee.postscreen.PostScreenViewModel
import net.hdhuu.splee.scheduler.JobExecutor
import net.hdhuu.splee.scheduler.UIThread
import net.hdhuu.splee.utils.TImeHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import org.koin.androidx.viewmodel.ext.koin.viewModel
import com.google.firebase.database.DatabaseReference
import net.hdhuu.splee.accounting.register.RegisterViewModel


val applicationModule = module(override = true) {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }

    single { Room.databaseBuilder(androidContext(),
        TweetDatabase::class.java, "posts.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build() }
    factory { get<TweetDatabase>().postDAO() }
}

val firebaseModule = module(override = true){
    factory {FirebaseDatabase.getInstance().reference }
    single { FirebaseAuth.getInstance() }
}
val activitiesModule = module(override = true) {

}

val postModule = module(override = true) {
    //mappers
    factory {PostMapper()}
    factory { PostEntityMapper() }
    factory { net.hdhuu.remote.mapper.PostEntityMapper() }

    factory<PostRemote> { PostRemoteImpl(get ()) }
    factory<PostCache> { PostCacheImpl(get(),get()) }
    factory { PostDataStoreFactory(get(), get(), get()) }
    factory<PostRepository> { net.hdhuu.datasource.post.PostRepository(get(),get(),get()) }
    factory { GetPostUseCase(get(), get(), get()) }
    factory { PostMessageUseCase(get(),get(),get()) }
    factory { DeleteMessageUseCase(get(),get(),get()) }

    viewModel{ MainViewModel(get(),get(),get()) }
    factory { TImeHelper() }
    factory { MainPostAdapter(get()) }
    factory { PostMultipleMessageUseCase(get(),get(),get()) }

    viewModel { PostScreenViewModel(get(),get()) }

    factory { SplitMessage() }

    factory<FirebaseContract> { FirebaseImpl(get(),get()) }
    factory<UserRepository> { net.hdhuu.datasource.post.repository.UserRepository(get()) }
    factory { RegisterUseCase(get(),get(),get()) }

}

val registerModule = module (override = true){
    factory { RegisterUseCase(get(),get(),get()) }
    viewModel { RegisterViewModel(get()) }
}