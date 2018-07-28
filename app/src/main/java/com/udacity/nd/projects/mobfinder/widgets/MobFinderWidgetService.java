package com.udacity.nd.projects.mobfinder.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.nd.projects.mobfinder.MobileDetailsActivity;
import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.utils.ProviderUtils;

import java.util.List;

/**
 * Created by noname on 7/28/18.
 */

public class MobFinderWidgetService extends RemoteViewsService {
    private static final String TAG = MobFinderWidgetService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MobFinderRemoteViewsFactory(this);
    }

    class MobFinderRemoteViewsFactory implements RemoteViewsFactory {
        private Context mContext;
        List<Mobile> mobiles;

        MobFinderRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            Log.d(TAG, "onDataSetChanged");
            mobiles = ProviderUtils.getAllMobiles(mContext);
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (mobiles == null) return 0;

            Log.d(TAG, String.valueOf(mobiles.size()));

            return mobiles.size();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            Log.d(TAG, "getViewAt: " + i);

            Mobile mobile = mobiles.get(i);

            Log.d(TAG, "getViewAt: mobile=" + mobile.getDeviceName());

            RemoteViews rv = new RemoteViews(mContext.getPackageName(),
                    R.layout.widget_list_item);

            int resId = R.drawable.ic_launcher_foreground;
            switch (mobile.getBrand()) {
                case "Google":
                    resId = R.drawable.ic_google_g_logo;
                    break;
                case "Apple":
                    resId = R.drawable.ic_apple_logo;
                    break;
                case "HTC":
                    resId = R.drawable.ic_htc_logo;
                    break;
                case "Samsung":
                    resId = R.drawable.ic_samsung_logo;
                    break;
            }

            rv.setImageViewResource(R.id.iv_widget_mobile_image, resId);
            rv.setTextViewText(R.id.tv_widget_mobile_name, mobile.getDeviceName());
            rv.setTextViewText(R.id.tv_widget_mobile_status, mobile.getStatus());

            rv.setOnClickFillInIntent(R.id.widget_mobile_info, MobileDetailsActivity.getFillIntent(mobile));

            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
