package com.example.testmenu.nav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmenu.History;
import com.example.testmenu.MainActivity;
import com.example.testmenu.R;
import com.example.testmenu.SearchNationalize;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class RecyclerViewNavAdapter extends RecyclerView.Adapter<RecyclerViewNavAdapter.ViewHolder> {
    Context context;
    String[] navValues;

    /**
     * OnInt
     * @param context
     * @param nav
     */
    public RecyclerViewNavAdapter(Context context, String[] nav) {
        this.context = context;
        this.navValues = nav;
    }

    /**
     * Create a new Holder for the view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewNavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.activity_recycler_nav_view_items, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Detect new Changes in the View
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(navValues[position]);
    }

    /**
     * Check navValues Size
     * @String navValues
     * @return
     */
    @Override
    public int getItemCount() {
        return navValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textview1);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(textView.getText() == "Nueva Busqueda") {
                        Intent intent = new Intent(context, SearchNationalize.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        System.out.println("Redirect to " + textView.getText());
                    } else {
                        Intent intent = new Intent(context, History.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        System.out.println("Redirect to " + textView.getText());
                    }

                }
            });
        }
    }
}
