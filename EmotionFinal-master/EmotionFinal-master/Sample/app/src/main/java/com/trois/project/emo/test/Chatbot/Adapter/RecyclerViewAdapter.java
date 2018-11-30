package com.trois.project.emo.test.Chatbot.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trois.project.emo.test.Chatbot.Model.SectionDataModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemRowHolder>{

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext()).inflate(com.trois.project.emo.test.R.layout.card_list_item, null);
        RecyclerViewAdapter.ItemRowHolder rh = new RecyclerViewAdapter.ItemRowHolder(v);
        return rh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);


       /*  itemRowHolder.recycler_view_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/

        itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();



            }
        });



    }



    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;



        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(com.trois.project.emo.test.R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(com.trois.project.emo.test.R.id.recycler_view_list);
            this.btnMore= (Button) view.findViewById(com.trois.project.emo.test.R.id.btnMore);


        }

    }
}

