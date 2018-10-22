package aresit.com.photoapp.data.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import aresit.com.photoapp.AppExecutors;

/**
 * Provides a common API for image classification services
 *
 */

public class ImageResultNetworkDataSource {

    private static final String LOG_TAG = ImageResultNetworkDataSource.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ImageResultNetworkDataSource sInstance;
    private final Context mContext;
    private final AppExecutors mExecutors;

    private ImageResultNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
    }

    // TODO - finish this class

    public void scheduleRecurringFetchWeatherSync() {

    }
    /**
     * Get the singleton for this class
     */
    public static ImageResultNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ImageResultNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

}