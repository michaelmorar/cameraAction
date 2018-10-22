package aresit.com.photoapp.ui;

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

import aresit.com.photoapp.R;
import aresit.com.photoapp.utilities.InjectorUtils;

public class MainActivity extends AppCompatActivity {

    private ImageResultsAdapter mImageAdapter;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private Button mButton;
    private FloatingActionButton cameraAction;
    private ProgressBar mProgressBar;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private MainActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("1t", "CP1");

        setContentView(R.layout.activity_main);
        Log.v("2t", "CP2");
        findViewsbyID();

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

        mImageAdapter = new ImageResultsAdapter(this, this);



        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mImageAdapter);
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

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

    private void findViewsbyID() {
        Log.v("3t", "CP3");
        mButton = (Button) findViewById(R.id.classify_button);
        mRecyclerView = findViewById(R.id.recyclerView_results);
        mImageView = (ImageView) findViewById(R.id.mgView);
        cameraAction = (FloatingActionButton) findViewById(R.id.camFloatingActionButton);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_loading);
        Log.v("4t", "CP4");
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);

    }
}
