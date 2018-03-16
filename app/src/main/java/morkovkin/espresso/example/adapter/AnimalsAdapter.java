package morkovkin.espresso.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

import example.ivi.ru.espressorecyclerview.R;
import morkovkin.espresso.example.model.Animal;


public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder> {
	
	private final List<Animal> mData;
	private OnAnimalClickListener mOnAnimalClickListener;
	
	public interface OnAnimalClickListener {
		
		void onRemove(int position);
	}
	
	public AnimalsAdapter(final List<Animal> animalList, OnAnimalClickListener onAnimalClickListener) {
		mData = animalList;
		mOnAnimalClickListener = onAnimalClickListener;
	}
	
	@Override
	public AnimalsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		final View v = LayoutInflater.from(parent.getContext()).inflate(
			R.layout.item_animal, parent, false);
		return new AnimalsViewHolder(v);
	}
	
	
	public void removeImte(final int position) {
		mData.remove(position);
		notifyItemRemoved(position);
	}
	
	@Override
	public void onBindViewHolder(final AnimalsViewHolder holder, final int position) {
		holder.bind(mData.get(position));
	}
	
	@Override
	public int getItemCount() {
		return mData.size();
	}
	
	public class AnimalsViewHolder extends RecyclerView.ViewHolder {
		
		private final TextView mName;
		private final ImageView ivAnimal;
		private ImageView ivAnimalType;
		
		private AnimalsViewHolder(final View itemView) {
			super(itemView);
			
			mName = itemView.findViewById(R.id.tvName);
			ivAnimal = itemView.findViewById(R.id.ivAnimal);
			itemView.findViewById(R.id.btnRemove).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(final View v) {
					mOnAnimalClickListener.onRemove(getLayoutPosition());
				}
			});
			ivAnimalType = itemView.findViewById(R.id.ivAnimalType);
			
		}
		
		private void bind(final Animal item) {
			mName.setText(item.getName());
			Picasso.get().load(item.getImageUrl()).into(ivAnimal);
			switch (item.getType()) {
				case PREDATOR: {
					ivAnimalType.setBackgroundResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
					break;
				}
				case HERBIVORE: {
					ivAnimalType.setBackgroundResource(R.drawable.ic_sentiment_very_satisfied_black_24dp);
					break;
				}
			}
			
			
		}
	}
}

