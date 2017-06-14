package org.adroidtown.practiceapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by bomeeryu_c on 2017. 6. 14..
 */

public class CustomWidget extends AppWidgetProvider {
    Realm realm;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        realm = Realm.getDefaultInstance();
        RealmResults<Item> items = realm.where(Item.class).findAll();
        Item item = items.get(items.size() - 1);
//        =item.getContent();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.custom_widget);
        remoteViews.setTextViewText(R.id.text_widget_content, item.getContent());


        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pe = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.text_widget_content, pe);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

}
