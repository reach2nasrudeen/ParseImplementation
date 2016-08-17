package com.nasrudeen.parseimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String strValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.test);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("app id")
                .clientKey("client key")
                .server("url").build()
        );
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("NewsAndOffers");
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        Log.i("Came -->","Came");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> postList, ParseException e) {
//                Log.i("Test","Tesxt");
//                if (e == null) {
//                    // If there are results, update the list of posts
//                    // and notify the adapter
//                    strValue = postList.get(0).getString("foo");
//                    Log.i("String --->", strValue);
////                    textView.setText((CharSequence) postList);
////                    for (ParseObject post : postList) {
////                        Log.i("String --->", String.valueOf(postList));
////                    }
//                } else {
//                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
//                }
//            }
//        });


        ParseQuery query = new ParseQuery("NewsAndOffers");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("Brand", "Retrieved " + objects.size() + " Brands");
                    for (ParseObject dealsObject : objects) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        Log.i("Test came --->","Came 3");
                        Toast.makeText(getApplicationContext(),"Object -->"+dealsObject.getString("poster"),Toast.LENGTH_SHORT).show();
                        textView.append("\n"+dealsObject.getString("poster"));
                    }
                } else {
                    Log.d("Brand", "Error: " + e.getMessage());
                }

            }
        });

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        query.getInBackground("R9iUEz3jp3", new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    // object will be your game score
//                    strValue = object.getString("foo");
//                    Log.i("String --->", strValue);
//                } else {
//                    // something went wrong
//                }
//            }
//        });
//        textView.setText(strValue);
    }
}
