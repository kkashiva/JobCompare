package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.graphics.Typeface;
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
        int rank = getItem(position).getRank();
        boolean isCurrentJob = getItem(position).isCurrentJob();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView rankView = convertView.findViewById(R.id.ranking);
        TextView titleView = convertView.findViewById(R.id.title);
        TextView companyView = convertView.findViewById(R.id.company);

        if (isCurrentJob) {
            rankView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            titleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            companyView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        }

        rankView.setText(String.format("%d", rank));
        titleView.setText(title);
        companyView.setText(company);

        return convertView;
    }
}
