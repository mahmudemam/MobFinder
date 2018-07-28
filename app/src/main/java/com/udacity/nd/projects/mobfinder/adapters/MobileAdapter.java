package com.udacity.nd.projects.mobfinder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;
import com.udacity.nd.projects.mobfinder.utils.ProviderUtils;

import java.util.List;

/**
 * Created by noname on 7/16/18.
 */

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileViewHolder> {
    private Context mContext;
    private List<Mobile> mMobiles;
    private MobileAdapterClickListener mListener;

    public interface MobileAdapterClickListener {
        void onShareClicked(String text);

        void onFavoriteClicked(Mobile mobile, boolean favorite);

        void onMobileClicked(Mobile mobile);
    }

    public MobileAdapter(Context context, List<Mobile> mobiles, MobileAdapterClickListener listener) {
        mContext = context;
        mMobiles = mobiles;
        mListener = listener;
    }

    @NonNull
    @Override
    public MobileAdapter.MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mobileCard = LayoutInflater.from(mContext)
                .inflate(R.layout.mobile_card_view, parent, false);

        return new MobileViewHolder(mobileCard);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileAdapter.MobileViewHolder holder, int position) {
        holder.bind(mMobiles.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMobiles == null) return 0;

        return mMobiles.size();
    }

    class MobileViewHolder extends RecyclerView.ViewHolder {
        ImageView mobileImage;
        TextView mobileName;
        TextView vendorName;
        ImageButton shareButton;
        ImageButton favoriteButton;

        MobileViewHolder(final View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onMobileClicked((Mobile) view.getTag());
                }
            });

            mobileImage = view.findViewById(R.id.iv_mobile_img);
            mobileName = view.findViewById(R.id.tv_mobile_name);
            vendorName = view.findViewById(R.id.tv_mobile_vendor);
            shareButton = view.findViewById(R.id.ib_share);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View shareView) {
                    Mobile mobile = (Mobile) view.getTag();

                    mListener.onShareClicked(mobile.toString());
                }
            });

            setupFavoriteButton(view);
        }

        void bind(Mobile mobile) {
            itemView.setTag(mobile);

            NetworkUtils.loadImage(mContext, mobileImage, mobile);

            mobileName.setText(mobile.getDeviceName());
            vendorName.setText(mobile.getBrand());

            if (ProviderUtils.isStored(mContext, mobile.getDeviceName())) {
                favoriteButton.setSelected(true);
                favoriteButton.setImageResource(R.drawable.ic_favorite_selected);
            } else {
                favoriteButton.setSelected(false);
                favoriteButton.setImageResource(R.drawable.ic_favorite_border);
            }
        }

        void setupFavoriteButton(final View view) {
            favoriteButton = view.findViewById(R.id.ib_favorite);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View favView) {
                    if (favView.isSelected()) {
                        favView.setSelected(false);
                        favoriteButton.setImageResource(R.drawable.ic_favorite_border);
                    } else {
                        favView.setSelected(true);
                        favoriteButton.setImageResource(R.drawable.ic_favorite_selected);
                    }
                    Mobile mobile = (Mobile) view.getTag();

                    mListener.onFavoriteClicked(mobile, favView.isSelected());
                }
            });
        }
    }
}
