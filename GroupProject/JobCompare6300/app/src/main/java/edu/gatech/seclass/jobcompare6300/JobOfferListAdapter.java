package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseBooleanArray;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class JobOfferListAdapter extends ArrayAdapter<JobOffer> {
    private Context mContext;
    int mResource;

    private SparseBooleanArray checkedStates;

    public JobOfferListAdapter(@NonNull Context context, int resource, @NonNull List<JobOffer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.checkedStates = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String company = getItem(position).getCompany();
        int rank = getItem(position).getRank();
        boolean isCurrentJob = getItem(position).isCurrentJob();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(mResource, parent, false);

        TextView rankView = view.findViewById(R.id.ranking);
        TextView titleView = view.findViewById(R.id.title);
        TextView companyView = view.findViewById(R.id.company);
        CheckBox checkBox = view.findViewById(R.id.checkBox);

        if (isCurrentJob) {
            rankView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            titleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            companyView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        }

        rankView.setText(String.format("%d", rank));
        titleView.setText(title);
        companyView.setText(company);

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(checkedStates.get(position, false));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("onCheckedChanged @position" + position);
                checkedStates.put(position, isChecked);
                checkBox.setChecked(isChecked);
                updateCheckBoxes();
            }
        });

        return view;
    }

    private void updateCheckBoxes() {
        int checkedCount = 0;
        for (int i = 0; i < checkedStates.size(); i++) {
            if (checkedStates.valueAt(i)) {
                checkedCount++;
            }
        }

        System.out.println("CHECK COUNT " + checkedCount);
        for (int i = 0; i < getCount(); i++) {
            View view = getView(i, null, null);
            CheckBox checkBox = view.findViewById(R.id.checkBox);
            if (checkedCount >= 2 && !checkedStates.get(i, false)) {
                System.out.println("GOTTA GO IN HERE TO DISABLE CHECK BOX");
                checkBox.setEnabled(false);
            } else {
                checkBox.setEnabled(true);
            }
        }

        notifyDataSetChanged();
    }
}
