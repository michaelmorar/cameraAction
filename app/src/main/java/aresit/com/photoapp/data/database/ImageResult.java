package aresit.com.photoapp.data.database;

public class ImageResult {

    private String label;
    private double probability;

    public ImageResult(String label, double probability) {
        this.label = label;
        this.probability = probability;
    }

}
