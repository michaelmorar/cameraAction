package aresit.com.photoapp.ui;

import android.arch.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public String getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(String imageLabel) {
        this.imageLabel = imageLabel;
    }

    private String imageLabel;
}
