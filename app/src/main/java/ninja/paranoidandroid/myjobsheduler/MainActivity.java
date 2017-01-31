package ninja.paranoidandroid.myjobsheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private JobScheduler mJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );
        JobInfo.Builder builder = new JobInfo.Builder( 1, new ComponentName( getPackageName(), JobShedulerService.class.getName() ) );
        builder.setPeriodic( 3000 );

        if( mJobScheduler.schedule( builder.build() ) <= 0 ) {
            //If something goes wrong
            Log.i("MainActivity", "in onCreate(), there is somekind of problem.");
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        mJobScheduler.cancelAll();
    }
}
