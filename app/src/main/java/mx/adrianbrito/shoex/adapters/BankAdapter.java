package mx.adrianbrito.shoex.adapters;

import android.content.Context;
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

        BankViewHolder(View view){
            super(view);

            cv = (CardView) view.findViewById(R.id.activity_bankselector);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtRoundImage = (TextView) view.findViewById(R.id.txtRoundImage);
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
        bankViewHolder.txtRoundImage.setText(listBanks.get(i).getImg());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}


//=========

/*public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_bankselector, viewGroup, false);

        view.setOnClickListener(this);

        BankViewHolder bvh = new BankViewHolder(view);

        return bvh;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        if (listener != null)
            listener.onClick(view);
    }

}*/

//=========

/*public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    private List<mx.adrianbrito.shoex.domain.Bank> banks;
    Context mContext;

    public BankAdapter(List<mx.adrianbrito.shoex.domain.Bank> banks*//*, Context context*//*) {
        this.banks = banks;
        *//*this.mContext = context;*//*
    }

    public static class BankViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtRoundImage;

        public BankViewHolder(View view) {
            super(view);

            txtRoundImage = (TextView) view.findViewById(R.id.txtRoundImage);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        }
    }

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bankselector, parent, false);

        return new BankViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final BankViewHolder holder, int position) {
        final mx.adrianbrito.shoex.domain.Bank bank = banks.get(position);

        *//*Picasso
                .with(mContext)
                .load(bank.getImg())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(holder.imgBank);*//*

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CardFriend", "Friend id: " + bank.getUuid());
            }
        });

        holder.txtTitle.setText(bank.getDescription());
        holder.txtRoundImage.setText(bank.getImg());
    }

    @Override
    public int getItemCount() {
        if (this.banks == null) return 0;

        return this.banks.size();
    }

}*/
