package com.example.testmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmenu.database.NationalizeTable;

import java.util.List;

/**
 * @Adapter
 * @note Set Nationalize list history
 */

public class NationalizeListAdapter extends RecyclerView.Adapter<NationalizeListAdapter.MyViewHolder>{

    private Context context;
    private List<NationalizeTable> nationalizeList;

    /**
     * Constructor
     * @param context
     * @method void
     */
    public NationalizeListAdapter(Context context) {
        this.context = context;
    }

    /**
     * Set nationalize list after response Api
     * @param nationalizeList
     * @method void
     */
    public void setNationalizeList(List<NationalizeTable> nationalizeList) {
        this.nationalizeList = nationalizeList;
        for (int i = 0; i< nationalizeList.size(); i++) {
            nationalizeList.get(i);
            System.out.println("Lista" + nationalizeList.get(i).uri);
        }
        notifyDataSetChanged();
    }

    /**
     * Create View Holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NationalizeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);

    }

    /**
     * Bind View Holder
     * @param holder
     * @param position
     * @method void
     */
    @Override
    public void onBindViewHolder(@NonNull NationalizeListAdapter.MyViewHolder holder, int position) {
        System.out.println("lista 2" + this.nationalizeList.get(position).uri);
        int id = this.nationalizeList.get(position).id;
        String uri = this.nationalizeList.get(position).uri;
        holder.textView.setText(id + " " + uri);
    }

    /**
     * Count de nationalize size
     * @method int
     * @return
     */
    @Override
    public int getItemCount() {
        return this.nationalizeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.txtView);
        }
    }
}
