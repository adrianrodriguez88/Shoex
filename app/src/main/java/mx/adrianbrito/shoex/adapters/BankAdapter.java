package mx.adrianbrito.shoex.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mx.adrianbrito.shoex.R;
import mx.adrianbrito.shoex.domain.Bank;
//import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AdrianRodriguez on 09/03/17.
 */

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    public static class BankViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtTitle, txtRoundImage;
        ImageView img;

        BankViewHolder(View view){
            super(view);

            cv = (CardView) view.findViewById(R.id.activity_bankselector);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            /*txtRoundImage = (TextView) view.findViewById(R.id.txtRoundImage);*/
            img = (ImageView) view.findViewById(R.id.imgRoundImage);
        }

        public void updateBitmap(Bitmap bm){
            img.setImageBitmap(bm);
        }
    }

    List<Bank> listBanks;

    public BankAdapter(List<Bank> listBanks){
        this.listBanks = listBanks;
    }

    @Override
    public int getItemCount(){
        return listBanks.size();
    }

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bankselector, viewGroup, false);

        BankViewHolder bvh = new BankViewHolder(view);
        return bvh;
    }

    @Override
    public void onBindViewHolder(BankViewHolder bankViewHolder, int i){
        bankViewHolder.txtTitle.setText(listBanks.get(i).getDescription());
        //bankViewHolder.txtRoundImage.setText(listBanks.get(i).getImg());

        //bankViewHolder.img.setImageURI(android.net.Uri.parse(listBanks.get(i).getImg()));
        //new ImageDownloader(BankAdapter.this).execute(new String[]{listBanks.get(i).getImg()});
        new ImageDownloader(bankViewHolder).execute(new String[]{listBanks.get(i).getImg()});

        /*android.net.Uri uri = android.net.Uri.parse(listBanks.get(i).getImg());

        Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
        bankViewHolder.img.setImageBitmap(bm);*/
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ImageDownloader extends android.os.AsyncTask<String, String, Bitmap> {

        private BankViewHolder parentActivity;


        public ImageDownloader(BankViewHolder parentActivity) {
            super();

            this.parentActivity = parentActivity;
        }

        protected Bitmap doInBackground(String... args) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((java.io.InputStream)new java.net.URL(args[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap image) {
            if(image != null){
                parentActivity.updateBitmap(image);
            }
        }
    }

    /*public class ImageDownloader extends android.os.AsyncTask<String, String, Bitmap> {

        private BankAdapter parentActivity;


        public ImageDownloader(BankAdapter parentActivity) {
            super();

            this.parentActivity = parentActivity;
        }

        protected Bitmap doInBackground(String... args) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((java.io.InputStream)new java.net.URL(args[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap image) {
            if(image != null){
                parentActivity.updateBitmap(image);
            }
        }
    }*/
}

