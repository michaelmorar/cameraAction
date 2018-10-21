package aresit.com.photoapp.data;

public class ImageResult {

    private String imageId;
    private String label;
    private double probability;
    private ClassifierProvider classifierProvider;

    public ImageResult(String s, String l, double v, ClassifierProvider classifierProvider) {
        this.imageId = s;
        this.label = l;
        this.probability = v;
        this.classifierProvider = classifierProvider;
    }

    public String getImageId() { return this.imageId;}
    public String getLabel() {return this.label;}

    public double getProbability() {
        return probability;
    }

    public ClassifierProvider getClassifierProvider() {
        return this.classifierProvider;
    }

    public void setClassifierProvider(ClassifierProvider classifierProvider) {
        this.classifierProvider = classifierProvider;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
