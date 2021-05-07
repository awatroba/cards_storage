package com.cardsStorage.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cardsStorage.R;
import com.cardsStorage.model.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CardRecyclerViewAdapter  extends RecyclerView.Adapter<CardRecyclerViewAdapter.CardsViewHolder> {
    private List<CreditCard> creditCardList = new ArrayList<>();

    public CardRecyclerViewAdapter(List<CreditCard> creditCardList) {
        this.creditCardList = creditCardList;
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_row,parent,false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRecyclerViewAdapter.CardsViewHolder
                                             holder, int position) {
        CreditCard creditCard = creditCardList.get(position);
        holder.exCvv.setText(creditCard.getCvv()+"");
        holder.exDate.setText(creditCard.getDate().toString());
        holder.exNumber.setText(creditCard.getNumber());
        holder.rowTxtName.setText(creditCard.getName());

        boolean isExpandable=creditCard.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }


    @Override
    public int getItemCount() {
        return creditCardList.size();
    }


    public class CardsViewHolder extends RecyclerView.ViewHolder {

        private TextView rowTxtName ;
        private TextView exNumber;
        private TextView exCvv;
        private TextView exDate ;

        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;

        public CardsViewHolder(@NonNull  View itemView) {
            super(itemView);
            rowTxtName = (TextView) itemView.findViewById(R.id.rowTxtName);
            exNumber = (TextView) itemView.findViewById(R.id.exNumber);
            exCvv = (TextView) itemView.findViewById(R.id.exCvv);
            exDate = (TextView) itemView.findViewById(R.id.exDate);

            linearLayout=itemView.findViewById(R.id.linearLayout);
            expandableLayout=itemView.findViewById(R.id.expandableLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreditCard creditCard = creditCardList.get(getAdapterPosition());
                    creditCard.setExpandable(!creditCard.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
