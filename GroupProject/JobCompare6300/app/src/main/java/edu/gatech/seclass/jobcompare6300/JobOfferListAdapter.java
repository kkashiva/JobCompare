package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class JobOfferListAdapter extends ArrayAdapter<JobOffer> {
    private Context mContext;
    int mResource;

    public JobOfferListAdapter(@NonNull Context context, int resource, @NonNull List<JobOffer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String company = getItem(position).getCompany();
//        TODO: uncomment this line once comparison setting is hooked up to calculate score correctly
//        int score = getItem(position).getScore();
        int score = 100;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView rankView = convertView.findViewById(R.id.ranking);
        TextView titleView = convertView.findViewById(R.id.title);
        TextView companyView = convertView.findViewById(R.id.company);

        rankView.setText(String.format("%d", score));
        titleView.setText(title);
        companyView.setText(company);

        return convertView;
    }
}
