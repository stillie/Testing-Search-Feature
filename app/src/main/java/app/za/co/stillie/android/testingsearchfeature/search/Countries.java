package app.za.co.stillie.android.testingsearchfeature.search;

/**
 * Created by Ryan vdW on 2015/10/09.
 */
public class Countries {

    private String countryName;
    private String image;

    public Countries(String countryName, String imgId) {
        this.countryName = countryName;
        this.image = imgId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
