package id.ac.unsyiah.android.asynctask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.SmartPhone;

public class PhoneAdapter extends ArrayAdapter<SmartPhone> {

    private Context context;
    private List<SmartPhone> phoneList;
    LruCache<Integer, Bitmap> imageCache;

    public PhoneAdapter(Context context, int resource, List<SmartPhone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.phoneList = objects;
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/8;
        imageCache = new LruCache<>(cacheSize);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_phone, parent, false);

        //Display phone name in the TextView widget
        SmartPhone smartPhone = phoneList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(smartPhone.getProductName());

        Bitmap bitmap = imageCache.get(smartPhone.getProductId());
        //Tampilkan foto hp menggunakan widget imageview

        if (bitmap != null) {
            ImageView image = view.findViewById(R.id.imageView1);
            image.setImageBitmap(smartPhone.getBitmap());
        }else{
            PhoneAndView container = new PhoneAndView();
            container.phone = smartPhone;
            container.view = view;

            imageLoader loader = new imageLoader();
            loader.execute(container);
        }
        return view;

    }
    class PhoneAndView{
        public SmartPhone phone;
        public  View view;
        public Bitmap bitmap;
    }

    private class imageLoader extends AsyncTask<PhoneAndView, Void, PhoneAndView> {
        @Override
        protected PhoneAndView doInBackground(PhoneAndView... params) {
            PhoneAndView container = params[0];
            SmartPhone phone = container.phone;
            try{
                String imageUrl = MainActivity.PHOTO_BASE_URL + phone.getPhoto();
                InputStream in = (InputStream) new URL(imageUrl).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                phone.setBitmap(bitmap);
                in.close();
                container.bitmap = bitmap;
                return container;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(PhoneAndView result) {
            ImageView image = result.view.findViewById(R.id.imageView1);
            image.setImageBitmap(result.bitmap);
            //result.phone.setBitmap(result.bitmap);
            imageCache.put(result.phone.getProductId(), result.bitmap);
        }
    }

}
