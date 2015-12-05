package br.com.trainning.pdv.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.trainning.pdv.R;
import br.com.trainning.pdv.domain.image.Base64Util;
import br.com.trainning.pdv.domain.model.ItemProduto;

public class CustomArrayAdapter extends ArrayAdapter<ItemProduto> {
    protected LayoutInflater inflater;
    protected int layout;

    public CustomArrayAdapter(Activity activity, int resourceId, List<ItemProduto> objects){
        super(activity, resourceId, objects);
        layout = resourceId;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(layout, parent, false);

        //O adapter não é um activity por isso não pode usar o ButterKnife
        TextView tv = (TextView)v.findViewById(R.id.item_label);
        ImageView foto = (ImageView)v.findViewById(R.id.image_view);

        tv.setText(getItem(position).getDescricao() + " Qtd" + getItem(position).getQuantidade());

        //foto.setImageBitmap(Base64Util.decodeBase64(produto.getFoto()));

        foto.setImageBitmap(Base64Util.decodeBase64(getItem(position).getFoto()));

        return v;
    }
}