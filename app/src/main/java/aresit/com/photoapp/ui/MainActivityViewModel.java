package aresit.com.photoapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import aresit.com.photoapp.data.PhotoRepository;
import aresit.com.photoapp.data.database.ImageResult;

public class MainActivityViewModel extends ViewModel {
    private final LiveData<List<ImageResult>> mImageResult;
    private final PhotoRepository mRepository;

    public MainActivityViewModel(PhotoRepository mRepository) {
        this.mRepository = mRepository;
        this.mImageResult = mRepository.getImageResults();

    }

    public LiveData<List<ImageResult>> getForecast() {
        return mImageResult;
    }

}
