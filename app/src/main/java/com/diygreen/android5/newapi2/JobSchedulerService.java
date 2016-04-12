package com.diygreen.android5.newapi2;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.LinkedList;

public class JobSchedulerService extends JobService {

    private static final String TAG = "JobSchedulerService";

    JobSchedulerActivity mActivity;
    private final LinkedList<JobParameters> jobParamsMap = new LinkedList<JobParameters>();

    // 需要注意的是这个job service运行在主线程
    public JobSchedulerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger callback = intent.getParcelableExtra("messenger");
        Message m = Message.obtain();
        m.what = JobSchedulerActivity.MSG_SERVICE_OBJ;
        m.obj = this;
        try {
            callback.send(m);
        } catch (RemoteException e) {
            Log.e(TAG, "Error passing service object back to activity.");
        }
        return START_NOT_STICKY;
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
        // 返回true，表示让系统知道你会手动地调用jobFinished

        // We don't do any real 'work' in this sample app. All we'll
        // do is track which jobs have landed on our service, and
        // update the UI accordingly.
        jobParamsMap.add(params);
        if (mActivity != null) {
            mActivity.onReceivedStartJob(params);
        }
        Log.i(TAG, "on start job: " + params.getJobId());
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
        // Stop tracking these job parameters, as we've 'finished' executing.
        jobParamsMap.remove(params);
        if (mActivity != null) {
            mActivity.onReceivedStopJob();
        }
        Log.i(TAG, "on stop job: " + params.getJobId());
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroyed");
    }

    public void setUiCallback(JobSchedulerActivity activity) {
        mActivity = activity;
    }

    /** Send job to the JobScheduler. */
    public void scheduleJob(JobInfo t) {
        Log.d(TAG, "Scheduling job");
        JobScheduler tm =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(t);
    }

    /**
     * Not currently used, but as an exercise you can hook this
     * up to a button in the UI to finish a job that has landed
     * in onStartJob().
     */
    public boolean callJobFinished() {
        JobParameters params = jobParamsMap.poll();
        if (params == null) {
            return false;
        } else {
            jobFinished(params, false);
            return true;
        }
    }

}
