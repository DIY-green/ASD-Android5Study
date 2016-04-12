package com.diygreen.android5.newapi2;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class JobSchedulerSimpleService extends JobService {

    private static final String TAG = "JobSchedulerService";

    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // 模拟用 Handlder 来执行在 JobSchedulerService 中定义的任务（耗时操作）
            Toast.makeText(getApplicationContext(), "JobSchedulerService task running", Toast.LENGTH_SHORT).show();
            /*
            当任务执行完毕之后，你需要调用jobFinished(JobParameters params, boolean needsRescheduled)
            来让系统知道这个任务已经结束，系统可以将下一个任务添加到队列中。如果
            你没有调用jobFinished(JobParameters params, boolean needsRescheduled)，
            你的任务只会执行一次，而应用中的其他任务就不会被执行。
             */
            /*
            params参数是从JobService的onStartJob(JobParameters params)的
            params传递过来的，needsRescheduled参数是让系统知道这个任务是否应该在
            最初的条件下被重复执行。这个boolean值很有用，因为它指明了你如何处理由于
            其他原因导致任务执行失败的情况，例如一个失败的网络请求调用。
             */
            jobFinished((JobParameters) msg.obj, false);
            return true;
        }
    });

    // 需要注意的是这个job service运行在主线程
    public JobSchedulerSimpleService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "JobSchedulerService created");
    }

    // 当任务开始时会执行onStartJob(JobParameters params)方法，
    // 因为这是系统用来触发已经被执行的任务
    @Override
    public boolean onStartJob(JobParameters params) {
        /*
        如果返回值是false,系统假设这个方法返回时任务已经执行完毕。
        如果返回值是true,那么系统假定这个任务正要被执行，
        执行任务的重担就落在了你的肩上。
         */
        /*
        当任务执行完毕时你需要调用
        jobFinished(JobParameters params, boolean needsRescheduled)
        来通知系统
         */
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params));
        // 返回true，表示让系统知道你会手动地调用jobFinished

        return true;
    }

    /*
    当系统接收到一个取消请求时，
    系统会调用onStopJob(JobParameters params)方法取消正在等待执行的任务。
    很重要的一点是如果onStartJob(JobParameters params)返回false,
    那么系统假定在接收到一个取消请求时已经没有正在运行的任务。
    换句话说，onStopJob(JobParameters params)在这种情况下不会被调用。
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        mJobHandler.removeMessages(1);
        // 可以试一下这里分别返回 true 与 false 的区别
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "JobSchedulerService destroyed");
    }

}
