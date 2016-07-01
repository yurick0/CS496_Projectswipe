package inkyu.project_swipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

@SuppressLint("ValidFragment")
public class newFragment extends Fragment {
    Context mContext;
    String[] phone = new String[3];

    public newFragment(Context context) {
        mContext = context;
    }

    public void onStart(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        phone = getActivity().getResources().getStringArray(R.array.phone);
        ListView listViewExample = (ListView) view.findViewById(R.id.lv_name);


        listViewExample.setOnItemClickListener(hahahoho);



        String strJson =
                "{ " +
                        " \"name\":[ " +

                        "{" +
                        "\"humanname\": \"김철수\","+
                        "\"flag\": "+ R.drawable.chulsoo + ","+
                        "\"phonenumber\": \"010-1111-1111\","+
                        "\"job\": \"농부\"," +
                        "\"status\": {" +
                        "\"statuscontent\": \"좋은 하루\" " +
                        "}" +
                        "}, " +

                        "{" +
                        "\"humanname\": \"박영희\","+
                        "\"flag\": "+ R.drawable.younghee + ","+
                        "\"phonenumber\": \"010-2222-2222\","+
                        "\"job\": \"초등학교 교사\"," +
                        "\"status\": {" +
                        "\"statuscontent\": \"방학 시작~\" " +
                        "}" +
                        "}," +

                        "{" +
                        "\"humanname\": \"정대호\","+
                        "\"flag\": "+ R.drawable.daehoo + ","+
                        "\"phonenumber\": \"010-3333-3333\","+
                        "\"job\": \"대학생\"," +
                        "\"status\": {" +
                        "\"statuscontent\": \"F만 피하자\" " +
                        "}" +
                        "}" +

                        "]" +
                        "} ";



        /** The parsing of the xml data is done in a non-ui thread */
        ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();

        /** Start parsing xml data */
        listViewLoaderTask.execute(strJson);

        return view;
    }

    public class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter>{

        JSONObject jObject;
        /** Doing the parsing of xml data in a non-ui thread */
        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try{
                jObject = new JSONObject(strJson[0]);
                CountryJSONParser countryJsonParser = new CountryJSONParser();
                countryJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("JSON Exception1",e.toString());
            }

            CountryJSONParser countryJsonParser = new CountryJSONParser();

            List<HashMap<String, String>> countries = null;

            try{
                /** Getting the parsed data as a List construct */
                countries = countryJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }

            /** Keys used in Hashmap */
            String[] from = { "country","flag","details"};

            /** Ids of views in listview_layout */
            int[] to = { R.id.tv_name,R.id.iv_flag, R.id.tv_name_details};

            /** Instantiating an adapter to store each items
             *  R.layout.listview_layout defines the layout of each item
             */
            SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), countries, R.layout.lv_layout, from, to);

            return adapter;
        }

        /** Invoked by the Android system on "doInBackground" is executed completely */
        /** This will be executed in ui thread */
        @Override
        protected void onPostExecute(SimpleAdapter adapter) {

            /** Getting a reference to listview of main.xml layout file */
            ListView listView = ( ListView ) getActivity().findViewById(R.id.lv_name);

            /** Setting the adapter containing the country list to listview */
            listView.setAdapter(adapter);
        }
    }

    private AdapterView.OnItemClickListener hahahoho = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            switch(position){
                case 0:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phone[position]));
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phone[position]));
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phone[position]));
                    startActivity(intent);
                    break;
            }

        }
    };


}
