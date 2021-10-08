package gal.linial.foodappproject.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class FoodInformation implements Serializable {

    @SerializedName("fdcId")
    @Expose
    private Integer fdcId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("publicationDate")
    @Expose
    private String publicationDate;
    @SerializedName("foodCode")
    @Expose
    private String foodCode;
    @SerializedName("foodNutrients")
    @Expose
    private List<FoodNutrient> foodNutrients = null;


    public FoodInformation() {
    }


    public Integer getFdcId() {
        return fdcId;
    }

    public void setFdcId(Integer fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public List<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FoodInformation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("fdcId");
        sb.append('=');
        sb.append(((this.fdcId == null)?"<null>":this.fdcId));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("dataType");
        sb.append('=');
        sb.append(((this.dataType == null)?"<null>":this.dataType));
        sb.append(',');
        sb.append("publicationDate");
        sb.append('=');
        sb.append(((this.publicationDate == null)?"<null>":this.publicationDate));
        sb.append(',');
        sb.append("foodCode");
        sb.append('=');
        sb.append(((this.foodCode == null)?"<null>":this.foodCode));
        sb.append(',');
        sb.append("foodNutrients");
        sb.append('=');
        sb.append(((this.foodNutrients == null)?"<null>":this.foodNutrients));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}