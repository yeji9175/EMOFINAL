package com.trois.project.emo.test.Chatbot.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trois.project.emo.test.R;
import com.trois.project.emo.test.Chatbot.Model.Chat;
import com.trois.project.emo.test.Chatbot.Model.SectionDataModel;
import com.trois.project.emo.test.Chatbot.Model.SingleItemModel;
import com.trois.*;
import com.trois.project.emo.test.Chatbot.Utils.Constant;

import java.util.ArrayList;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private static final int RIGHT = 0, LEFT =1;

    private ArrayList<Chat> chats;


    public ChatMessageAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public int getItemViewType(int position) {
        //오른쪽은 내 메시지, 왼쪽은 상대 메시지

        if (chats.get(position).isIs_me()) //수정봤는데 오류터지면 여기 확인해야함
            return RIGHT;
        else
            return LEFT;
    }


    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch(viewType) {

            case RIGHT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
                break;
            case LEFT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
        }
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.bind(chat);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

}

class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    TextView tv_user_real_name;//한글로 메시지를 보낸 사람의 이름을 쓰는 텍스트뷰
    TextView txtMessage;//텍스트 메시지 내용을 보여주는 텍스트뷰
    TextView txtTime;//텍스트 메시지를 보낸 시간을 보여주는 텍스트뷰
    LinearLayout container_txt;//텍스트 메시지의 레이아웃
    TextView dateLine;//날짜 변경선을 보여주는 텍스트뷰
    Context context;
    RecyclerView rv_choice_card;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();


        txtMessage = (TextView) itemView.findViewById(R.id.tv_message);

        txtTime = (TextView) itemView.findViewById(R.id.timestamp);

        container_txt = (LinearLayout) itemView.findViewById(R.id.msg_container);
        dateLine = (TextView) itemView.findViewById(R.id.dateLine);
        tv_user_real_name = (TextView) itemView.findViewById(R.id.tv_user_real_name);
        rv_choice_card = (RecyclerView)itemView.findViewById(R.id.rv_choice_card);
    }


    /**
     * 들어오는 메시지의 종류마다 어떻게 화면에 출력할 지 정한다.
     * 1. 텍스트
     * 2. 이미지
     * 3. 내이미지
     * 4. 맵
     * 5. 날짜선
     */

    public void bind(final Chat chat) {
        switch(chat.getAction()) {

            case Constant.ACTION_START:
                container_txt.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);

                rv_choice_card.setVisibility(View.VISIBLE);

                ArrayList<SectionDataModel> array_action_start = new ArrayList<SectionDataModel>();
                ArrayList<SingleItemModel> singleItem_start = new ArrayList<SingleItemModel>();
                SectionDataModel dm_start = new SectionDataModel();

                SingleItemModel sm = new SingleItemModel("감정 기록하기", Constant.ACTION_START);
                singleItem_start.add(sm);

                dm_start.setAllItemsInSection(singleItem_start);
                array_action_start.add(dm_start);

                rv_choice_card.setHasFixedSize(true);
                RecyclerViewAdapter adapter_start = new RecyclerViewAdapter(context, array_action_start);
                rv_choice_card.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                rv_choice_card.setAdapter(adapter_start);
                break;

            case Constant.ACTION_TEXT:

                container_txt.setVisibility(View.VISIBLE);
                //           txtCheck.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);

                rv_choice_card.setVisibility(View.GONE);

                break;

            case Constant.ACTION_DONE:

                container_txt.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.VISIBLE);
                txtTime.setVisibility(View.VISIBLE);
                txtMessage.setText(chat.getMessage());
                txtTime.setText(chat.getTimestamp());
                dateLine.setVisibility(View.GONE);
                rv_choice_card.setVisibility(View.VISIBLE);

                ArrayList<SectionDataModel> array_action_yn = new ArrayList<SectionDataModel>();
                ArrayList<SingleItemModel> singleItem_yn = new ArrayList<SingleItemModel>();
                SectionDataModel dm_yn = new SectionDataModel();


                singleItem_yn.add(new SingleItemModel("응", Constant.ACTION_START )); //수정 필요!
                singleItem_yn.add(new SingleItemModel("아니", Constant.ACTION_START )); //수정 필요!


                dm_yn.setAllItemsInSection(singleItem_yn);
                array_action_yn.add(dm_yn);

                rv_choice_card.setHasFixedSize(true);
                RecyclerViewAdapter adapter_done = new RecyclerViewAdapter(context, array_action_yn);
                rv_choice_card.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                rv_choice_card.setAdapter(adapter_done);

                break;


            /**
             *
             * 보류 히히
             *
             case ACTION_SCHEDULE_OTHER:

             //txt + 물음상자
             // container_img.setVisibility(View.GONE);
             container_txt.setVisibility(View.VISIBLE);
             //           txtCheck.setVisibility(View.VISIBLE);
             txtMessage.setVisibility(View.VISIBLE);
             txtTime.setVisibility(View.VISIBLE);
             txtMessage.setText(chat.getMessage());
             txtTime.setText(chat.getTimestamp());
             dateLine.setVisibility(View.GONE);
             rv_choice_card.setVisibility(View.VISIBLE);


             ArrayList<SectionDataModel> array_action_start2 = new ArrayList<SectionDataModel>();
             ArrayList<SingleItemModel> ACTION_STARTsingleItem2 = new ArrayList<SingleItemModel>();
             SectionDataModel ACTION_STARTdm2 = new SectionDataModel();

             for(int i =0;i<emotions.size();i++) {
             ACTION_STARTsingleItem2.add(new SingleItemModel(roles.get(i).getRole_name(), ACTION_SCHEDULE_MY));
             }
             //
             ACTION_STARTsingleItem2.add(new SingleItemModel(roles.get(0).getRole_name(), ACTION_SCHEDULE_MY ));
             ACTION_STARTsingleItem2.add(new SingleItemModel(roles.get(1).getRole_name(), ACTION_ALARM ));
             ACTION_STARTsingleItem2.add(new SingleItemModel(roles.get(2).getRole_name(), ACTION_DONE ));
             ACTION_STARTsingleItem2.add(new SingleItemModel("teammate4", ACTION_SCHEDULE_OTHER ));//
             ACTION_STARTsingleItem2.add(new SingleItemModel("메뉴", ACTION_START));

             ACTION_STARTdm2.setAllItemsInSection(ACTION_STARTsingleItem2);
             array_action_start2.add(ACTION_STARTdm2);

             rv_choice_card.setHasFixedSize(true);
             RecyclerViewDataAdapter adapter2 = new RecyclerViewDataAdapter(context, array_action_start2);
             rv_choice_card.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
             rv_choice_card.setAdapter(adapter2);

             break;



             case ACTION_SCHEDULE_MY:

             rv_choice_card.setVisibility(View.GONE);
             // container_img.setVisibility(View.GONE);
             container_txt.setVisibility(View.VISIBLE);
             //           txtCheck.setVisibility(View.VISIBLE);
             txtMessage.setVisibility(View.VISIBLE);
             txtTime.setVisibility(View.VISIBLE);
             txtMessage.setText(chat.getMessage());
             txtTime.setText(chat.getTimestamp());
             dateLine.setVisibility(View.GONE);


             break;

             */
            //날짜선 케이스
            //쓰는 뷰
            //안쓰는 뷰
            case Constant.DATE_LINE:
                //      container_img.setVisibility(View.GONE);
                container_txt.setVisibility(View.GONE);
                //         txtCheck.setVisibility(View.GONE);
                txtMessage.setVisibility(View.GONE);
                txtTime.setVisibility(View.GONE);
                dateLine.setVisibility(View.VISIBLE);
                dateLine.setText(chat.getMessage());
                break;

        }
    }

}
