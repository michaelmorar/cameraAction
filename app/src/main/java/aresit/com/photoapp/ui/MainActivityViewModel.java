package aresit.com.photoapp.ui;

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


public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<ImageResult>> mImageResult;

    public MainActivityViewModel(ImageResultRepository imageResultRepository) {
    //    this.mRepository = mRepository;
        LiveData<List<ImageResult>> imageResult = imageResultRepository.getImageResults();
        this.mImageResult = (MutableLiveData<List<ImageResult>>) imageResult;
    }

    public LiveData<List<ImageResult>> getImageResult() {
        if (mImageResult == null) {
            mImageResult = new MutableLiveData<>();
            //TODO - implement
            loadImageResults();
        }
        return mImageResult;
    }

    private void loadImageResults() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {

        }, 5000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

}
