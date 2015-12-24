package ie.gutierrez.franco.Round1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ie.gutierrez.franco.Round1.R;
import ie.gutierrez.franco.Round1.app.AppController;
import ie.gutierrez.franco.Round1.model.HotelModel;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * This class will update each item through the JsonObject and using the list_row.xml
 * Created by Franco on 20/12/2015.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<HotelModel> hotelModelItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<HotelModel> hotelModelItems) {
        this.activity = activity;
        this.hotelModelItems = hotelModelItems;
    }

    @Override
    public int getCount() {
        return hotelModelItems.size();
    }

    @Override
    public Object getItem(int location) {
        return hotelModelItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //In this part i will set up all values for each item.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        
        TextView title = (TextView) convertView.findViewById(R.id.title);

       // getting movie data for the row
        HotelModel m = hotelModelItems.get(position);

        title.setText(m.getPageTitle());

        return convertView;
    }

}
