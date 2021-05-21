package com.challenge.step1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.challenge.step1.activities.MainActivity;
import com.challenge.step1.interfaces.LoadListener;
import com.challenge.step1.models.Exercise;
import com.challenge.step1.models.Session;
import com.google.common.io.ByteStreams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LoadData {
    public static List<Session> mSessions = new ArrayList<>();
    private static final String TAG = LoadData.class.getSimpleName();

    public static class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private LoadListener mLoadListener;
        private static WeakReference<Context> mContext;

        public LoadDataAsyncTask(Context context) {
           this.mContext = new WeakReference<>(context);
            mLoadListener = MainActivity.mLoadListener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getSessionsData();

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mLoadListener.load(mSessions);

        }

        /**
         *Get sessions methods using a GET request*/
        private void getSessionsData() {
            HttpURLConnection urlConnection = null;
            try {
                String address = Constants.URL;
                URL url = new URL(address);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                int status = urlConnection.getResponseCode();
                Log.e("response code", status + "");
                if(status == 200) {
                    InputStream in = urlConnection.getInputStream();
                    String data = new String(ByteStreams.toByteArray(in));
                    byte[] buffer = new byte[1024];
                    while (in.read(buffer) != -1) {
                        String curr = new String(buffer, "UTF-8");
                        data += curr;
                    }
                    sessionJsonToObject(data);
                } else {
                    Log.e( "url_connection_error", urlConnection.getErrorStream() +"");
                    InputStream error = urlConnection.getErrorStream();
                    String error_data = new String(ByteStreams.toByteArray(error));
                    byte[] buffer = new byte[1024];
                    while (error.read(buffer) != -1) {
                        String curr = new String(buffer, "UTF-8");
                        error_data += curr;
                    }
                    Log.e("url_connection_error =>", " " + error_data);
                    JSONObject jo = new JSONObject(error_data);
                    Log.e("url_connection_error =>", " " + jo.toString());
                }

            } catch (JSONException jsonException) {
                Log.e(TAG, "JSON ERROR", jsonException);
            } catch (IOException ioException) {
                Log.e(TAG, "IOException ERROR", ioException);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

    }

    public static LoadListener setLoadListener(LoadListener loadListener) {
        return loadListener;
    }

    private static void sessionJsonToObject(String object) throws JSONException {
        JSONArray sessionObject = new JSONArray(object);
        for(int i =0; i < sessionObject.length(); i++){
            JSONObject obj = sessionObject.getJSONObject(i);
            Session session = new Session();
            session.setName(obj.getString("name"));
            session.setPracticedOnDate(parseDate(obj.getString("practicedOnDate"), "dd-MM-yyyy"));
            exerciseJsonToObject(obj.getString("exercises"), session);
            mSessions.add(session);
        }


    }

    private static String parseDate(String d, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
            Date date = dateFormat.parse(d);
            dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void exerciseJsonToObject(String s, Session session) throws JSONException {
        List<Exercise> exercises = new ArrayList<>();
        JSONArray sessionsExercises = new JSONArray(s);
        for(int i =0; i < sessionsExercises.length(); i++){
            JSONObject obj = sessionsExercises.getJSONObject(i);
            Exercise exercise = new Exercise();
            exercise.setName(obj.getString("name"));
            exercise.setPracticedAtBpm(obj.getInt("practicedAtBpm"));
            exercises.add(exercise);
        }
        session.setExercises(exercises);

    }

}
