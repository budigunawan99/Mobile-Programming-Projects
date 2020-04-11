package id.ac.unsyiah.android.asynctask;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.SmartPhone;
import model.SmartPhoneList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends ListActivity {

    TextView output;
    ProgressBar pb;
//    List<MyTask> tasks;
    List<SmartPhone> phoneList;
    public static final String PHOTO_BASE_URL = "http://202.4.186.247/api/toko-hape/foto/";
    public static final String ENDPOINT = "http://202.4.186.247";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //inisialisasi TextView untuk scrolling vertikal
//        output = (TextView) findViewById(R.id.textView);
//        output.setMovementMethod(new ScrollingMovementMethod());

        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
//        tasks = new ArrayList<>();

//        for (int i = 1; i <= 100; i++) {
//            updateDisplay("Baris ke " + i);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_do_task){
            if (isOnline()) {
                requestData("http://202.4.186.247/api/toko-hape/rest/list/json");
            } else {
                Toast.makeText(this,"Tidak ada internet", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }

    private void requestData(String uri) {
//        MyTask task = new MyTask();
//       // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Param 1", "Param 2", "Param 3");
//        task.execute(uri);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT).addConverterFactory(GsonConverterFactory.create()).build();
        PhoneAPI api = retrofit.create(PhoneAPI.class);
        Call<SmartPhoneList> call = api.getFeed();
        call.enqueue(new Callback<SmartPhoneList>() {
            @Override
            public void onResponse(Call<SmartPhoneList> call, Response<SmartPhoneList> response) {
                if(response.isSuccessful()){
                    phoneList = response.body().getSmartPhone();
                    updateDisplay();
                }
            }

            @Override
            public void onFailure(Call<SmartPhoneList> call, Throwable t) {

            }
        });
    }

    protected void updateDisplay(){
//        if (phoneList != null) {
//            for(SmartPhone phone : phoneList){
//                output.append(phone.getProductName() + "\n");
//            }
//        }
//        output.append(message + "\n");

        //Gunakan kelas PhoneAdapter untuk menampilkan data
        PhoneAdapter adapter = new PhoneAdapter(this, R.layout.item_phone,phoneList);
        setListAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

//    private class MyTask extends AsyncTask<String, String, List<SmartPhone>> {
//        @Override
//        protected void onPreExecute() {
////            updateDisplay("Starting Task");
//
//            if (tasks.size() == 0) {
//                pb.setVisibility(View.VISIBLE);
//            }
//
//            tasks.add(this);
//
//        }
//
//        @Override
//        protected List<SmartPhone> doInBackground(String... params) {
////            for (int i = 0; i < params.length; i++) {
////                publishProgress("Sedang mengeksekusi " + params[i]);
////
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
//            String content = HttpManager.getData(params[0]);
//            phoneList = PhoneJsonParser.parseFeed(content);
//
////            for(SmartPhone phone : phoneList){
////                try{
////                    String imageUrl = PHOTO_BASE_URL + phone.getPhoto();
////                    InputStream in = (InputStream) new URL(imageUrl).getContent();
////                    Bitmap bitmap = BitmapFactory.decodeStream(in);
////                    phone.setBitmap(bitmap);
////                    in.close();
////                }catch(Exception e){
////                    e.printStackTrace();
////                }
////            }
//
//            return phoneList;
//
////            return "Task Complete!";
//        }
//
//        @Override
//        protected void onPostExecute(List<SmartPhone> result) {
////            phoneList = PhoneJsonParser.parseFeed(result);
////            updateDisplay();
//
//            tasks.remove(this);
//            if (tasks.size() == 0) {
//                pb.setVisibility(View.INVISIBLE);
//            }
//            if(result == null){
//                Toast.makeText(MainActivity.this, "Web Service is not available", Toast.LENGTH_LONG).show();
//                return;
//            }
//            phoneList = result;
//            updateDisplay();
//
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
////            updateDisplay(values[0]);
//        }
//    }

}
