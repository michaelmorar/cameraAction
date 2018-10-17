package aresit.com.photoapp.data.database;
/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

/**
 * {@link Dao} which provides an api for all data operations with the {@link ImageDatabase}
 */
@Dao
public interface ImageDao {
    /**
     * Selects all {@link ListImageResult} entries for a given imageId. The LiveData will
     * be kept in sync with the database, so that it will automatically notify observers when the
     * values in the table change.
     *
     * @param imageId A {@link String} for which to select Image results
     * @return {@link LiveData} list of all {@link ListImageResult} objects for that imageId
     */
    @Query("SELECT imageId, image_result FROM image_data WHERE imageId = :imageId")
    LiveData<List<ListImageResult>> getCurrentImageResults(String imageId);

    /**
     * Returns a count of database entries for a given imageId
     *
     * @param imageId The date to select after (inclusive)
     * @return Number of Image Results for that imageId
     */
    @Query("SELECT COUNT(*) FROM image_data WHERE imageId = :imageId")
    int countReultsforImage(String imageId);

    /**
     * Gets the results for a given single image
     *
     * @param imageId The image for which we want a result
     * @return {@link LiveData} with weather for a single day
     */
    @Query("SELECT * FROM image_data WHERE imageId = :imageId")
    LiveData<ImageResult> getImageResult(String imageId);

    /**
     * Inserts a list of {@link ImageResult} into the image_result table. If there is a conflicting id
     * or date the image result uses the {@link OnConflictStrategy} of replacing the image result
     * The required uniqueness of these values is defined in the {@link ImageResult}.
     *
     * @param imageResults A list of ImageResult to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(ImageResult... imageResults);

    /**
     * Deletes specified ImageResult
     *
     * @param imageId ImageResult to delete
     */
    @Query("DELETE FROM image_data WHERE imageId = :imageId")
    void deleteOldImageResults(String imageId);

}
