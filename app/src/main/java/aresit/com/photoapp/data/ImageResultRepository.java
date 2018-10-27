package aresit.com.photoapp.data;

import android.arch.lifecycle.MutableLiveData;
import android.media.Image;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aresit.com.photoapp.AppExecutors;
import aresit.com.photoapp.data.network.ImageResultNetworkDataSource;

public class ImageResultRepository {

    private static final String LOG_TAG = ImageResultRepository.class.getSimpleName();

    //private final ImageDao mWeatherDao;
    private static final Object LOCK = new Object();
    private final ImageResultNetworkDataSource mImageResultNetworkDataSource;
    private final AppExecutors mExecutors;
    //private boolean mInitialised = false;
    public static ImageResultRepository sInstance;

    public ImageResultRepository(ImageResultNetworkDataSource mImageResultNetworkDataSource, AppExecutors executors) {
        this.mImageResultNetworkDataSource = mImageResultNetworkDataSource;
        this.mExecutors = executors;
    }

    public MutableLiveData<List<ImageResult>> getImageResults() {
        //TODO - create a dummy livedata object complete with observer - hopefully no need for dao yet
        Random random = new Random(System.nanoTime());
        List<ImageResult> imageResults = new ArrayList<ImageResult>();
        imageResults.add(new ImageResult("1", "weasel", random.nextDouble(), ClassifierProvider.EINSTEINAI));
        imageResults.add(new ImageResult("2", "rat", random.nextDouble(), ClassifierProvider.GOOGLEAPI));
        imageResults.add(new ImageResult("3", "field mouse", random.nextDouble(), ClassifierProvider.LOCALAPI));
        MutableLiveData<List<ImageResult>> mle = new MutableLiveData<List<ImageResult>>();
        mle.postValue(imageResults);
        return mle;
    }


    public synchronized static ImageResultRepository getInstance(
            ImageResultNetworkDataSource imageResultNetworkDataSource,
            AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ImageResultRepository(imageResultNetworkDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

}

    /** TODO -this method not needed in this case - simplified implementation uses REST pattern
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    /**private synchronized void initialiseData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialised) return;
        mInitialised = true;

        // This method call triggers the app to create its task to synchronize weather data
        // periodically.
        mImageResultNetworkDataSource.scheduleRecurringFetchWeatherSync();
        mExecutors.diskIO().execute(() -> {
            if (isFetchNeeded()) {
                startFetchWeatherService();
            }
        });
    }
    **/


    /**
     * Database related operations
     **/

/**    public LiveData<List<ListImageResult>> getCurrentImageResults() {
        initialiseData();
        //Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        return mWeatherDao.getCurrentWeatherForecasts(today);
    }

    public LiveData<WeatherEntry> getWeatherByDate(Date date) {
        initializeData();
        return mWeatherDao.getWeatherByDate(date);
    }
**/
    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
/**    private void deleteOldData() {

    }
**/
    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
/**    private boolean isFetchNeeded() {
        Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
        int count = mWeatherDao.countAllFutureWeather(today);
        return (count < WeatherNetworkDataSource.NUM_DAYS);
    }
/**
    /**
     * Network related operation
     */
/** not needed in this case
    private void startFetchWeatherService() {
        mWeatherNetworkDataSource.startFetchWeatherService();
    }
**/



//getCurrentImageResults