package aresit.com.photoapp.ui;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.media.Image;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.os.Handler;

import aresit.com.photoapp.data.ClassifierProvider;
import aresit.com.photoapp.data.ImageResult;
import aresit.com.photoapp.data.ImageResultRepository;

import static android.support.constraint.Constraints.TAG;
import static android.view.ViewGroup.getChildMeasureSpec;


public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<List<ImageResult>> mImageResult;
    private final ImageResultRepository mRepository;

    public MainActivityViewModel(ImageResultRepository imageResultRepository) {
        this.mRepository = imageResultRepository;
        LiveData<List<ImageResult>> imageResult = imageResultRepository.getImageResults();
        this.mImageResult = (MutableLiveData<List<ImageResult>>) imageResult;
    }

    public LiveData<List<ImageResult>> getImageResults() {
        return this.mRepository.getImageResults();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

}
