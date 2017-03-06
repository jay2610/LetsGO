package edu.project.LetsGO;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Business> listItems;
    private Context context;

    public MyAdapter(List<Business> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.yelp_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Business listItem = listItems.get(position);
        //textfields
        holder.mTextViewName.setText(listItem.getName());
        holder.mTextViewAddress.setText(listItem.getAddress());
        holder.mTextViewCategory.setText(listItem.getCategory());

        //images
        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.mImageViewImageUrl);

        Picasso.with(context)
                .load(listItem.getRatingurl())
                .into(holder.mImageViewRatingImageUrl);

        //on click of card
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement opening of mobile url here
                String url = listItem.getMobileurl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                //   Toast.makeText(context, "You clicked " +listItem.getMobileurl(),Toast.LENGTH_SHORT).show();
            }
        });

        //select button
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement sending user to create event page.
                Intent intent2 = new Intent(context, CreateEvent.class);
                context.startActivity(intent2);
                //create new activity and send it there.
                Toast.makeText(context, "You selected " + listItem.getName() + " to create event.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewAddress;
        public TextView mTextViewCategory;
        public ImageView mImageViewImageUrl;
        public ImageView mImageViewRatingImageUrl;
        public LinearLayout mLinearLayout;
        public Button mButton;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.name_text);
            mTextViewAddress = (TextView) itemView.findViewById(R.id.address_text);
            mTextViewCategory = (TextView) itemView.findViewById(R.id.category_text);
            mImageViewImageUrl = (ImageView) itemView.findViewById(R.id.main_image);
            mImageViewRatingImageUrl = (ImageView) itemView.findViewById(R.id.rating_image);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
            mButton = (Button) itemView.findViewById(R.id.select_button);

        }
    }
}
