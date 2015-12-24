package ie.gutierrez.franco.Round1;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ie.gutierrez.franco.Round1.adapter.CustomListAdapter;
import ie.gutierrez.franco.Round1.app.AppController;
import ie.gutierrez.franco.Round1.model.HotelModel;

public class MainActivity extends ActionBarActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // HotelModel json url
    private static final String url = "http://appcontent.hotelquickly.com/en/1/android/index.json";
    private ProgressDialog pDialog;
    private List<HotelModel> hotelModelList = new ArrayList<HotelModel>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, hotelModelList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        JsonArrayRequest hotelReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {//JSON passed should be a JSONArray not a JSONObject. Fixed it from the server side
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                HotelModel hotelModel = new HotelModel();
                                hotelModel.setUrl(obj.getString("url"));
                                hotelModel.setFilePath(obj.getString("filePath"));
                                hotelModel.setNameSpace(obj.getString("namespace"));
                                hotelModel.setCache(obj.getString("cache"));
                                hotelModel.setParams(obj.getString("params"));
                                hotelModel.setPageTitle(obj.getString("pageTitle"));
                                // templateLastUpdate is json array
                                JSONArray lastUpdateJsonArray = obj.getJSONArray("templateLastUpdated");
                                ArrayList<String> templateLastUpdatedArray = new ArrayList<String>();
                                for (int j = 0; j < lastUpdateJsonArray.length(); j++) {
                                    templateLastUpdatedArray.add((String) lastUpdateJsonArray.get(j));
                                }
                                hotelModel.setTemplateLastUpdated(templateLastUpdatedArray);

                                // adding hotelModel to hotelModelList array
                                hotelModelList.add(hotelModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(hotelReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }



}
