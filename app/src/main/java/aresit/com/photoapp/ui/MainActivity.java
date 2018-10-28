package aresit.com.photoapp.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import aresit.com.photoapp.R;
import aresit.com.photoapp.data.ImageResult;
import aresit.com.photoapp.utilities.InjectorUtils;

public class MainActivity extends AppCompatActivity implements ImageResultsAdapter.ImageResultAdapterOnItemClickHandler{

    private ImageResultsAdapter mImageAdapter;
    private ImageView mImageView;
    private TextView mIdView;
    private TextView mLabelView;
    private TextView mProbabilityView;
    private TextView mClassifierProviderView;
    private int mPosition = RecyclerView.NO_POSITION;
    private RecyclerView mRecyclerView;
    private Button mButton;
    private FloatingActionButton cameraAction;
    private ProgressBar mProgressBar;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private MainActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_results);
        findViewsByID();

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mImageAdapter = new ImageResultsAdapter(this, this);
        mRecyclerView.setAdapter(mImageAdapter);
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        getImageResults();

        /**The ForecastAdapter requires an
         * Android Context (which all Activities are) as well as an onClickHandler. Since our
         * MainActivity implements the ForecastAdapter ForecastOnClickHandler interface, "this"
                * is also an instance of that type of handler.
         */


        cameraAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                //getImageResults();
                mViewModel.getImageResults();
                //TODO update recycler view!
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.v("Bitmap_Output", imageBitmap.toString());
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void findViewsByID() {
        mButton = findViewById(R.id.classify_button);
        mLabelView = findViewById(R.id.label_view);
        mProbabilityView = findViewById(R.id.probability_view);
        mClassifierProviderView = findViewById(R.id.classifier_view);
        mImageView = findViewById(R.id.mgView);
        cameraAction = findViewById(R.id.camFloatingActionButton);
        mProgressBar = findViewById(R.id.progressBar_loading);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    /** Observe Image results repository
     *
     */
    private void getImageResults() {
        mViewModel.getImageResults().observe(this, imageResults -> {
            mImageAdapter.swapForecast(imageResults);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            mRecyclerView.smoothScrollToPosition(mPosition);
            // Show the image result list or the loading screen based on whether the image result data exists
            // and is loaded
            if (imageResults != null && imageResults.size() != 0) showImageResultView();
            else showLoading();
        });
    }


    /**
     * This method is for responding to clicks from our list.
     * So far, there is nothing to do so we "swallow" the click
     *
     */
    @Override
    public void onItemClick() {
        Log.v("Clicked", "I intend to take action");
    }

    /**
     * This method will make the View for the image result data visible and hide the error message and
     * loading indicator.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private void showImageResultView() {
        // First, hide the loading indicator
        mProgressBar.setVisibility(View.INVISIBLE);
        // Finally, make sure the weather data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the loading indicator visible and hide the image result View and error
     * message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private void showLoading() {
        // Then, hide the weather data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Finally, show the loading indicator
        mProgressBar.setVisibility(View.VISIBLE);
    }

}
