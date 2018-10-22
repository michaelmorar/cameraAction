package aresit.com.photoapp.utilities;

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


import android.content.Context;
import aresit.com.photoapp.AppExecutors;
import aresit.com.photoapp.data.ImageResultRepository;
import aresit.com.photoapp.data.network.ImageResultNetworkDataSource;
import aresit.com.photoapp.ui.MainViewModelFactory;


/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static ImageResultRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        ImageResultNetworkDataSource networkDataSource =
                ImageResultNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return ImageResultRepository.getInstance(networkDataSource, executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        ImageResultRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}