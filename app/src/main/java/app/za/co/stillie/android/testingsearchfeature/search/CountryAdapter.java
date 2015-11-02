package app.za.co.stillie.android.testingsearchfeature.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.za.co.stillie.android.testingsearchfeature.R;


public class CountryAdapter extends BaseAdapter implements Filterable {

    public Context mContext;
    public ArrayList<Countries> countryArrayList;
    public ArrayList<Countries> originalArrayList;

    public CountryAdapter(Context context, ArrayList<Countries> countryArrayList) {
        mContext = context;
        this.countryArrayList = countryArrayList;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return countryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return countryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CountryHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_results_row_list, parent, false);
            holder = new CountryHolder();
            holder.countryName = (TextView) convertView.findViewById(R.id.country_text);
            holder.countryFlag = (ImageView) convertView.findViewById(R.id.country_flag);
            convertView.setTag(holder);
        } else {
            holder = (CountryHolder) convertView.getTag();
        }
        holder.countryName.setText(countryArrayList.get(position).getCountryName());
//        holder.countryFlag.setImageResource(countryArrayList.get(position).getImgId());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Countries> results = new ArrayList<>();
                if (originalArrayList == null)
                    originalArrayList = countryArrayList;
                if (constraint != null) {
                    if (originalArrayList != null && originalArrayList.size() > 0) {
                        for (final Countries g : originalArrayList) {
                            if (g.getCountryName()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryArrayList = (ArrayList<Countries>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CountryHolder {
        TextView countryName;
        ImageView countryFlag;
    }

}
