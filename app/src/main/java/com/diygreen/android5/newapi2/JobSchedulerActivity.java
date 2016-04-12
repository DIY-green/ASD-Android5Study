package com.diygreen.android5.newapi2;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.diygreen.android5.R;

public class JobSchedulerActivity extends AppCompatActivity {

    public static final int MSG_UNCOLOUR_START = 0;
    public static final int MSG_UNCOLOUR_STOP = 1;
    public static final int MSG_SERVICE_OBJ = 2;

    private JobScheduler mJobScheduler;

    // UI fields.
    int defaultColor;
    int startJobColor;
    int stopJobColor;

    private TextView mShowStartView;
    private TextView mShowStopView;
    private TextView mParamsTextView;
    private EditText mDelayEditText;
    private EditText mDeadlineEditText;
    private RadioButton mWiFiConnectivityRadioButton;
    private RadioButton mAnyConnectivityRadioButton;
    private CheckBox mRequiresChargingCheckBox;
    private CheckBox mRequiresIdleCheckbox;

    ComponentName mServiceComponent;
    /** Service object to interact scheduled jobs. */
    JobSchedulerService mTestService;

    private static int kJobId = 0;

    Handler mHandler = new Handler(/* default looper */) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UNCOLOUR_START:
                    mShowStartView.setBackgroundColor(defaultColor);
                    break;
                case MSG_UNCOLOUR_STOP:
                    mShowStopView.setBackgroundColor(defaultColor);
                    break;
                case MSG_SERVICE_OBJ:
                    mTestService = (JobSchedulerService) msg.obj;
                    mTestService.setUiCallback(JobSchedulerActivity.this);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobscheduler);

        initView();
    }

    public void onClick(View v) {
        startSchedule();
    }

    private void startSchedule() {
        // 获取 JobScheduler 对象
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        // JobInfo.Builder接收两个参数，
        //      第一个参数是你要运行的任务的标识符，
        //      第二个是这个Service组件的类名。
        JobInfo.Builder builder = new JobInfo.Builder(
                1,
                new ComponentName(
                        getPackageName(),
                        JobSchedulerSimpleService.class.getName()));
        // builder允许你设置很多不同的选项来控制任务的执行
        // 这里设置的是让任务每隔三秒运行一次
        builder.setPeriodic(3000);
        mJobScheduler.schedule(builder.build());

        /*
        参考：http://www.android100.org/html/201506/26/158410.html
        其他设置方法 :
        setMinimumLatency(long minLatencyMillis):
            这个函数能让你设置任务的延迟执行时间(单位是毫秒),这个函数与setPeriodic(long time)方法不兼容，如果这两个方法同时调用了就会引起异常；
        setOverrideDeadline(long maxExecutionDelayMillis):
            这个方法让你可以设置任务最晚的延迟时间。如果到了规定的时间时其他条件还未满足，你的任务也会被启动。与setMinimumLatency(long time)一样，这个方法也会与setPeriodic(long time)，同时调用这两个方法会引发异常。
        setPersisted(boolean isPersisted):
            这个方法告诉系统当你的设备重启之后你的任务是否还要继续执行。
        setRequiredNetworkType(int networkType):
            这个方法让你这个任务只有在满足指定的网络条件时才会被执行。默认条件是JobInfo.NETWORK_TYPE_NONE，这意味着不管是否有网络这个任务都会被执行。另外两个可选类型，一种是JobInfo.NETWORK_TYPE_ANY，它表明需要任意一种网络才使得任务可以执行。另一种是JobInfo.NETWORK_TYPE_UNMETERED，它表示设备不是蜂窝网络( 比如在WIFI连接时 )时任务才会被执行。
        setRequiresCharging(boolean requiresCharging):
            这个方法告诉你的应用，只有当设备在充电时这个任务才会被执行。
        setRequiresDeviceIdle(boolean requiresDeviceIdle):
            这个方法告诉你的任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务。

        需要注意的是setRequiredNetworkType(int networkType), setRequiresCharging(boolean requireCharging) and setRequiresDeviceIdle(boolean requireIdle)者几个方法可能会使得你的任务无法执行，除非调用setOverrideDeadline(long time)设置了最大延迟时间，使得你的任务在为满足条件的情况下也会被执行。一旦你预置的条件被设置，你就可以构建一个JobInfo对象，然后通过如下所示的代码将它发送到你的JobScheduler中。
        if( mJobScheduler.schedule( builder.build() ) <= 0 ) {
            //If something goes wrong
        }

        你可能注意到了，这个schedule方法会返回一个整型。如果schedule方法失败了，它会返回一个小于0的错误码。否则它会我们在JobInfo.Builder中定义的标识id。
        如果你的应用想停止某个任务，你可以调用JobScheduler对象的cancel(int jobId)来实现；如果你想取消所有的任务，你可以调用JobScheduler对象的cancelAll()来实现。
        mJobScheduler.cancelAll();
        到了这里，你现在应该已经知道如何在你的应用中使用JobScheduler API来执行批量任务和后台操作了。
         */
    }

    private void initView() {
        Resources res = getResources();
        defaultColor = res.getColor(R.color.none_received);
        startJobColor = res.getColor(R.color.start_received);
        stopJobColor = res.getColor(R.color.stop_received);

        // Set up UI.
        mShowStartView = (TextView) findViewById(R.id.onstart_textview);
        mShowStopView = (TextView) findViewById(R.id.onstop_textview);
        mParamsTextView = (TextView) findViewById(R.id.task_params);
        mDelayEditText = (EditText) findViewById(R.id.delay_time);
        mDeadlineEditText = (EditText) findViewById(R.id.deadline_time);
        mWiFiConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_unmetered);
        mAnyConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_any);
        mRequiresChargingCheckBox = (CheckBox) findViewById(R.id.checkbox_charging);
        mRequiresIdleCheckbox = (CheckBox) findViewById(R.id.checkbox_idle);
        mServiceComponent = new ComponentName(this, JobSchedulerService.class);
        // Start service and provide it a way to communicate with us.
        Intent startServiceIntent = new Intent(this, JobSchedulerService.class);
        startServiceIntent.putExtra("messenger", new Messenger(mHandler));
        startService(startServiceIntent);
    }

    private boolean ensureTestService() {
        if (mTestService == null) {
            Toast.makeText(JobSchedulerActivity.this, "Service null, never got callback?",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * UI onclick listener to schedule a job. What this job is is defined in
     * TestJobService#scheduleJob().
     */
    public void scheduleJob(View v) {
        if (!ensureTestService()) {
            return;
        }

        JobInfo.Builder builder = new JobInfo.Builder(kJobId++, mServiceComponent);

        String delay = mDelayEditText.getText().toString();
        if (delay != null && !TextUtils.isEmpty(delay)) {
            builder.setMinimumLatency(Long.valueOf(delay) * 1000);
        }
        String deadline = mDeadlineEditText.getText().toString();
        if (deadline != null && !TextUtils.isEmpty(deadline)) {
            builder.setOverrideDeadline(Long.valueOf(deadline) * 1000);
        }
        boolean requiresUnmetered = mWiFiConnectivityRadioButton.isChecked();
        boolean requiresAnyConnectivity = mAnyConnectivityRadioButton.isChecked();
        if (requiresUnmetered) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        } else if (requiresAnyConnectivity) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        }
        builder.setRequiresDeviceIdle(mRequiresIdleCheckbox.isChecked());
        builder.setRequiresCharging(mRequiresChargingCheckBox.isChecked());

        mTestService.scheduleJob(builder.build());

    }

    public void cancelAllJobs(View v) {
        JobScheduler tm =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }

    /**
     * UI onclick listener to call jobFinished() in our service.
     */
    public void finishJob(View v) {
        if (!ensureTestService()) {
            return;
        }
        mTestService.callJobFinished();
        mParamsTextView.setText("");
    }

    /**
     * Receives callback from the service when a job has landed
     * on the app. Colours the UI and post a message to
     * uncolour it after a second.
     */
    public void onReceivedStartJob(JobParameters params) {
        mShowStartView.setBackgroundColor(startJobColor);
        Message m = Message.obtain(mHandler, MSG_UNCOLOUR_START);
        mHandler.sendMessageDelayed(m, 1000L); // uncolour in 1 second.
        mParamsTextView.setText("Executing: " + params.getJobId() + " " + params.getExtras());
    }

    /**
     * Receives callback from the service when a job that
     * previously landed on the app must stop executing.
     * Colours the UI and post a message to uncolour it after a
     * second.
     */
    public void onReceivedStopJob() {
        mShowStopView.setBackgroundColor(stopJobColor);
        Message m = Message.obtain(mHandler, MSG_UNCOLOUR_STOP);
        mHandler.sendMessageDelayed(m, 2000L); // uncolour in 1 second.
        mParamsTextView.setText("");
    }

}
