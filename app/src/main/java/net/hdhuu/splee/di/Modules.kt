package net.hdhuu.splee.di

import androidx.room.Room
import net.hdhuu.cache.post.PostCacheImpl
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.room.TweetDatabase
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.post.repository.PostCache
import net.hdhuu.datasource.post.repository.PostRemote
import net.hdhuu.datasource.post.PostDataStoreFactory
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor
import net.hdhuu.domain.usecase.GetPostUseCase
import net.hdhuu.domain.usecase.PostMessageUseCase
import net.hdhuu.remote.post.PostRemoteImpl
import net.hdhuu.splee.home.MainContract
import net.hdhuu.splee.home.MainPresenter
import net.hdhuu.splee.scheduler.JobExecutor
import net.hdhuu.splee.scheduler.UIThread
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val applicationModule = module(override = true) {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }

    single { Room.databaseBuilder(androidContext(),
        TweetDatabase::class.java, "bufferoos.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build() }
    factory { get<TweetDatabase>().postDAO() }
}

val activitiesModule = module(override = true) {
    factory<MainContract.Presenter> { (cv: MainContract.View) ->
        MainPresenter( get(),get(), cv)
    }

}

val postModule = module(override = true) {
    //mappers
    factory {PostMapper()}
    factory { PostEntityMapper() }
    factory { net.hdhuu.remote.mapper.PostEntityMapper() }

    factory<PostRemote> { PostRemoteImpl(get ()) }
    factory<PostCache> { PostCacheImpl(get(),get()) }
    factory { PostDataStoreFactory(get(), get(), get()) }
    factory<PostRepository> { net.hdhuu.datasource.post.PostRepository(get(),get()) }
    factory { GetPostUseCase(get(), get(), get()) }
    factory { PostMessageUseCase(get(),get(),get()) }
}