package net.hdhuu.splee.scheduler



import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import net.hdhuu.domain.scheduler.PostExecutionThread

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
class UIThread : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
