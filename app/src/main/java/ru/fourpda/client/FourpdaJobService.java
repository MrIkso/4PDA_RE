package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;

@SuppressLint({"NewApi"})
public class FourpdaJobService extends JobService {

    class C0155a implements Util.AbstractC0429j<Boolean, Object> {
        final JobParameters f636a;
        final Handler f637b;

        C0155a(JobParameters jobParameters, Handler handler) {
            this.f636a = jobParameters;
            this.f637b = handler;
        }

        public Boolean mo222a(Object obj) {
            DocumentManager.f2746C.m656b(this);
            FourpdaJobService.this.jobFinished(this.f636a, false);
            this.f637b.removeCallbacksAndMessages(null);
            return Boolean.FALSE;
        }
    }

    class RunnableC0156b implements Runnable {
        final Util.AbstractC0429j f639a;
        final JobParameters f640b;

        RunnableC0156b(Util.AbstractC0429j jVar, JobParameters jobParameters) {
            this.f639a = jVar;
            this.f640b = jobParameters;
        }

        @Override
        public void run() {
            if (2 == DocumentManager.getErrorStatusCode()) {
                DocumentManager.restartConnection(0);
                return;
            }
            DocumentManager.f2746C.m656b(this.f639a);
            FourpdaJobService.this.jobFinished(this.f640b, false);
        }
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        if (DocumentManager.isLoggined() && DocumentManager.getErrorStatusCode() == 0) {
            DocumentManager.restartConnection1(jobParameters.getJobId() + 1);
            if (DocumentManager.documentManager.isWebSocketConnected()) {
                Handler handler = new Handler();
                C0155a aVar = new C0155a(jobParameters, handler);
                DocumentManager.f2746C.m657a(aVar);
                handler.postDelayed(new RunnableC0156b(aVar, jobParameters), 10000);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (2 == DocumentManager.getErrorStatusCode()) {
            DocumentManager.restartConnection(0);
        }
        return false;
    }
}
