package aresit.com.photoapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

/**
 * {@link ImageResultDatabase} database for the application including a table for {@link ImageResult}
 * with the DAO {@link ImageDao}.
 */

// List of the entry classes and associated TypeConverters
@Database(entities = {ImageResult.class}, version = 1)


public abstract class ImageResultDatabase extends RoomDatabase {

    private static final String LOG_TAG = ImageResultDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "image_data";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ImageResultDatabase sInstance;

    public static ImageResultDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        ImageResultDatabase.class, ImageResultDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract ImageDao imageDao();
}
