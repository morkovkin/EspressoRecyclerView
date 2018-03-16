package morkovkin.espresso.example.model;

import com.google.gson.annotations.SerializedName;

public class Animal {
	
	@SerializedName("type")
	private AnimalType mType;
	@SerializedName("name")
	private String mName;
	@SerializedName("image_url")
	private String mImageUrl;
	
	
	public String getName() {
		return mName;
	}
	
	public AnimalType getType() {
		return mType;
	}
	
	public String getImageUrl() {
		return mImageUrl;
	}
}
