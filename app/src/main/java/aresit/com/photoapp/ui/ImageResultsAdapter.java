package aresit.com.photoapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import aresit.com.photoapp.R;
import aresit.com.photoapp.data.ImageResult;

public class ImageResultsAdapter extends RecyclerView.Adapter<ImageResultsAdapter.ImageResultsAdapterViewHolder> {
    private List<ImageResult> mImageResult;


    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mImageResult) return 0;
        return mImageResult.size();
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (like ours does) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public ImageResultsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        int layoutId = android.R.layout.activity_list_item;
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        view.setFocusable(true);
        return new ImageResultsAdapterViewHolder(view);
    }


    // The context we use to utility methods, app resources and layout inflaters
    private final Context mContext;

    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter.
     */

    private final ImageResultAdapterOnItemClickHandler mClickHandler;

    /**
     * Creates an ImageResultsAdapter.
     *
     * @param context      Used to talk to the UI and app resources
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    ImageResultsAdapter(@NonNull Context context, ImageResultAdapterOnItemClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
        //mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param imageResultsAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ImageResultsAdapterViewHolder imageResultsAdapterViewHolder, int position) {
        ImageResult imageResult = mImageResult.get(position);


        /****************
         * ImageId *
         ****************/

        /* Display Image Id */
        imageResultsAdapterViewHolder.idView.setText(imageResult.getImageId());

        /***********************
         * Label *
         ***********************/
        imageResultsAdapterViewHolder.labelView.setContentDescription(imageResult.getLabel());

        /**************************
         * Probability *
         **************************/
        imageResultsAdapterViewHolder.probabilityView.setText(Double.toString(imageResult.getProbability()));


        /*************************
         * Provider *
         *************************/
        imageResultsAdapterViewHolder.classifierProviderView.setContentDescription(imageResult.getClassifierProvider().toString());
    }



    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class ImageResultsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView idView;
        final TextView labelView;
        final TextView probabilityView;
        final TextView classifierProviderView;

        ImageResultsAdapterViewHolder(View view) {
            super(view);

            idView = view.findViewById(R.id.id_view);
            labelView = view.findViewById(R.id.label_view);
            probabilityView = view.findViewById(R.id.probability_view);
            classifierProviderView = view.findViewById(R.id.classifier_provider_view);

            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         * We call the onItemClick handler registered with this adapter
         *
         * @param v the View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onItemClick();
        }
    }

    /**
     * The interface that receives onItemClick messages.
     */
    public interface ImageResultAdapterOnItemClickHandler {
        void onItemClick();
    }
}
