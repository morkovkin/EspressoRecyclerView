package morkovkin.espresso.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import example.ivi.ru.espressorecyclerview.R;
import morkovkin.espresso.example.adapter.AnimalsAdapter;
import morkovkin.espresso.example.model.Animal;
import morkovkin.espresso.example.utils.Utils;


public class MainActivity extends AppCompatActivity  implements AnimalsAdapter.OnAnimalClickListener{
	private RecyclerView rvAnimals;
	private AnimalsAdapter mAnimalsAdapter;
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rvAnimals = findViewById(R.id.rvAnimals);
		final List<Animal> animalList = loadAnimals();
		setDataToList(animalList);
	}
	
	private List<Animal> loadAnimals() {
		final String json = Utils
			.inputStreamToString(getResources().openRawResource(R.raw.animals));
		
		final Type listType = new TypeToken<List<Animal>>() {
		
		}.getType();
		return new Gson().fromJson(json, listType);
		
	}
	
	public void setDataToList(final List<Animal> data) {
		final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		rvAnimals.setLayoutManager(layoutManager);
		mAnimalsAdapter = new AnimalsAdapter(data, this);
		
		rvAnimals.setAdapter(mAnimalsAdapter);
	}
	
	@Override
	public void onRemove(final int position) {
		mAnimalsAdapter.removeImte(position);
	}
}
