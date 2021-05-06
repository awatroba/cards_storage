package com.cardsStorage.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cardsStorage.R;
import com.cardsStorage.model.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends ArrayAdapter<CreditCard> {
    private Context context;
    private List<CreditCard> creditCardList = new ArrayList<>();

    public CardAdapter(@Nullable Context context, ArrayList<CreditCard> objects) {
        super(context,0, objects);
        this.context=context;
        this.creditCardList=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_card, parent, false);
        }

        CreditCard creditCard = creditCardList.get(position);

        TextView txtNumber = (TextView) listItem.findViewById(R.id.txtName);
        TextView txtCVV = (TextView) listItem.findViewById(R.id.txtNumber);

        txtNumber.setText("number");
        txtCVV.setText("Cvv");

        return listItem;
    }
}
