package edu.project.LetsGO;

/**
 * Created by Anirudh on 2/22/2017.
 */

public class Business {

    private String name;
    private String address;
    private String category;
    private String imageUrl;
    private String ratingurl;
    private String mobileurl;

    public Business(String name, String address, String category, String imageUrl, String ratingurl, String mobileurl) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.imageUrl = imageUrl;
        this.ratingurl = ratingurl;
        this.mobileurl = mobileurl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRatingurl() {
        return ratingurl;
    }

    public String getMobileurl() {
        return mobileurl;
    }
    //    public static Business fromJson(JSONObject jsonObject) {
//        Business b = new Business();
//        // Deserialize json into object fields
//        try {
//            b.id = jsonObject.getString("id");
//            b.name = jsonObject.getString("name");
//            b.phone = jsonObject.getString("display_phone");
//            b.imageUrl = jsonObject.getString("image_url");
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//        // Return new object
//        return b;
//    }
//
//    public static ArrayList<Business> fromJson(JSONArray jsonArray) {
//        JSONObject businessJson;
//        ArrayList<Business> businesses = new ArrayList<Business>(jsonArray.length());
//        // Process each result in json array, decode and convert to business object
//        for (int i=0; i < jsonArray.length(); i++) {
//            try {
//                businessJson = jsonArray.getJSONObject(i);
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//
//            Business business = Business.fromJson(businessJson);
//            if (business != null) {
//                businesses.add(business);
//            }
//        }
//
//        return businesses;
//    }
}
