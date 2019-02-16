package net.hdhuu.splee.di

import net.hdhuu.cache.post.PostCacheImpl
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.post.repository.PostCache
import net.hdhuu.datasource.post.repository.PostRemote
import net.hdhuu.datasource.post.PostDataStoreFactory
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor
import net.hdhuu.domain.usecase.GetPostUseCase
import net.hdhuu.remote.post.PostRemoteImpl
import net.hdhuu.splee.home.MainContract
import net.hdhuu.splee.home.MainPresenter
import net.hdhuu.splee.scheduler.JobExecutor
import net.hdhuu.splee.scheduler.UIThread
import org.koin.dsl.module.module


val applicationModule = module(override = true) {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }

}

val activitiesModule = module(override = true) {
    factory<MainContract.Presenter> { (cv: MainContract.View) ->
        MainPresenter( get(), cv)
    }

}

val postModule = module(override = true) {
    //mappers
    factory {PostMapper()}
    factory { PostEntityMapper() }
    factory { net.hdhuu.remote.mapper.PostEntityMapper() }

    factory<PostRemote> { PostRemoteImpl(get ()) }
    factory<PostCache> { PostCacheImpl(get()) }
    factory { PostDataStoreFactory(get(), get(), get()) }
    factory<PostRepository> { net.hdhuu.datasource.post.PostRepository(get()) }
    factory { GetPostUseCase(get(), get(), get()) }
}