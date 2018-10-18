package aresit.com.photoapp.data;

import android.arch.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import aresit.com.photoapp.AppExecutors;
import aresit.com.photoapp.data.database.ImageDao;
import aresit.com.photoapp.data.database.ListImageResult;
import aresit.com.photoapp.data.network.ImageResultNetworkDataSource;

public class PhotoRepository {

    private final ImageDao mWeatherDao;
    private final ImageResultNetworkDataSource mImageResultNetworkDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialised = false;

    //TODO - add constructor

    /** TODO -this method not needed in this case - simplified implementation uses REST pattern
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    private synchronized void initialiseData() {

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

}

//getCurrentImageResults