package com.example.passwordmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.MyViewHolder>{

    Context context;
    ArrayList<PasswordModel> arrayList;


    ItemClickCallBack callback;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passwords_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PasswordModel pwdModel = arrayList.get(position);
        holder.position = position;
        holder.bind(pwdModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameView;
        TextView websiteView;
        TextView passwordView;
        int position;

        public MyViewHolder(final View itemView) {
            super(itemView);

            this.passwordView = (TextView) itemView.findViewById(R.id.textPassword);
            this.usernameView = (TextView) itemView.findViewById(R.id.textUsername);
            this.websiteView = (TextView) itemView.findViewById(R.id.textWebsite);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.menuButton);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopup(view);
                }
            });
        }



         /*Spinner spinner = (Spinner)itemView.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context.getApplicationContext(), R.array.options_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

           spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    callback.onSpinnerClickCallBack(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });*/


        public void bind(PasswordModel pwdModel) {
            usernameView.setText(pwdModel.getSavedUsername());
            passwordView.setText(pwdModel.getSavedPassword());
            websiteView.setText(pwdModel.getSavedWebsite());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClickCallBack(pwdModel);
                }
            });
        }
        public void showPopup(View v) {
            PopupMenu popup = new PopupMenu(context.getApplicationContext(), v);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    callback.onMenuClickCallBack(menuItem.getItemId(), position);
                    return true;
                }
            });
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu, popup.getMenu());
            popup.show();

        }

    }


    public PasswordAdapter(Context context, ArrayList<PasswordModel> arrayList, ItemClickCallBack callback)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.callback = callback;
    }

    public interface ItemClickCallBack
    {
        void onItemClickCallBack(PasswordModel item);
        void onMenuClickCallBack(int position, int id);
    }

}
