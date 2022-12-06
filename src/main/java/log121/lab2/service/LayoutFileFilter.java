package log121.lab2.service;

public class LayoutFileFilter {

    boolean isDefault;

    String description;
    String extension;

    public LayoutFileFilter() {

    }

    /**
     * constructor method that initiates a new LayoutFileFilter with specified variables
     * @param description to be used
     * @param extension to be used
     * @param isDefault to be used
     */
    public LayoutFileFilter(String description, String extension,
                            boolean isDefault) {
        this.description = description;
        this.extension = extension;
        this.isDefault = isDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}