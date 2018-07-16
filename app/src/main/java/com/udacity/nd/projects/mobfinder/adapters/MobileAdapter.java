package com.udacity.nd.projects.mobfinder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;

import java.util.List;

/**
 * Created by noname on 7/16/18.
 */

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileViewHolder> {
    private Context mContext;
    private List<Mobile> mMobiles;

    public MobileAdapter(Context context, List<Mobile> mobiles) {
        mContext = context;
        mMobiles = mobiles;
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

        MobileViewHolder(View view) {
            super(view);

            mobileImage = view.findViewById(R.id.iv_mobile_img);
            mobileName = view.findViewById(R.id.tv_mobile_name);
            vendorName = view.findViewById(R.id.tv_mobile_vendor);
        }

        void bind(Mobile mobile) {
            mobileImage.setImageResource(R.drawable.ic_launcher_foreground);
            mobileName.setText(mobile.getDeviceName());
            vendorName.setText(mobile.getBrand());
        }
    }
}
