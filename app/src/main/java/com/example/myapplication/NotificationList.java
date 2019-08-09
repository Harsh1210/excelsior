package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotificationList extends ArrayAdapter {

    private Activity context;
    private List<Notification> NotificationList;

    public NotificationList(Activity context, List<Notification> NotificationList){
        super(context, R.layout.list_layout, NotificationList);
        this.context = context;
        this.NotificationList = NotificationList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewApp = (TextView) listViewItem.findViewById(R.id.textViewApp);
        TextView textViewNoti = (TextView) listViewItem.findViewById(R.id.textViewNoti);

        Notification notification = NotificationList.get(position);

        textViewApp.setText(notification.getAppName());
        textViewApp.setText(notification.getNotifi());

        return listViewItem;
    }
}
