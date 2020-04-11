package id.ac.unsyiah.android.webservice.Model;


import com.google.gson.annotations.SerializedName;

public class MovieInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("originalTitle")
    private String originalTitle;
    @SerializedName("year")
    private String year;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("runtimeMinutes")
    private String runtimeMinutes;
    @SerializedName("isAdult")
    private String isAdult;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("description")
    private String description;
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(String runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(String isAdult) {
        this.isAdult = isAdult;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}