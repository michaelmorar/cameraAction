package aresit.com.photoapp.data.database;

public class ImageResult {

    private String label;
    private double probability;
    private ClassifierProvider provider;

    public ImageResult(String label, double probability, ClassifierProvider provider) {
        this.label = label;
        this.probability = probability;
        this.provider = provider;
    }

    public String getLabel() {
        return this.label;
    }

    public double getProbability() {return this.probability};

    public ClassifierProvider getProvider() {
        return this.provider;
    }
}
