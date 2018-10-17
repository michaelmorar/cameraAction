package aresit.com.photoapp.data.database;


/**
 * Simplified {@link ImageResult} which only contains the details needed for the image result list in
 * the {@link aresit.com.photoapp.ui.ImageResultAdapter}
 */
public class ListImageResult {

    private String imageId;
    private String label;

    public ListImageResult(String imageId) {
        this.imageId = imageId;
    }

    public String getimageId() {
        return imageId;
    }

    public String getLabel() {
        return label;
    }
}

