package com.udacity.nd.projects.mobfinder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;

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
        void onFavoriteClicked();
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

        MobileViewHolder(final View view) {
            super(view);

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
        }

        void bind(Mobile mobile) {
            itemView.setTag(mobile);

            int placeHolder = R.drawable.ic_launcher_foreground;

            if (mobile.getBrand().equals(mContext.getString(R.string.vendor_google))) {
                placeHolder = R.drawable.ic_google_g_logo;
            } else if (mobile.getBrand().equals(mContext.getString(R.string.vendor_apple))) {
                placeHolder = R.drawable.ic_apple_logo;
            } else if (mobile.getBrand().equals(mContext.getString(R.string.vendor_htc))) {
                placeHolder = R.drawable.ic_htc_logo;
            } else if (mobile.getBrand().equals(mContext.getString(R.string.vendor_samsung))) {
                placeHolder = R.drawable.ic_samsung_logo;
            }

            Picasso.get()
                    .load(mobile.getImageURL())
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .into(mobileImage);

            mobileName.setText(mobile.getDeviceName());
            vendorName.setText(mobile.getBrand());
        }
    }
}
