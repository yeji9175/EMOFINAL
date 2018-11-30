package com.trois.project.emo.test.Chatbot.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.trois.project.emo.test.Chatbot.Adapter.ChatMessageAdapter;
import com.trois.project.emo.test.Chatbot.Model.Chat;
import com.trois.project.emo.test.Chatbot.Model.Emotion;
import com.trois.project.emo.test.Chatbot.Utils.Constant;
import com.trois.project.emo.test.Chatbot.Utils.DateFormat;
import com.trois.project.emo.test.R;
import com.trois.project.emo.test.ui.FaceActivity;

import java.util.ArrayList;

public class ChatBotActivity extends AppCompatActivity {

    private static final String TAG = "[ChatActivity]";

    public static ArrayList<Chat> chats = new ArrayList<Chat>();
    public static ArrayList<Emotion> emotions = new ArrayList<>();

    public static ChatMessageAdapter mAdapter = new ChatMessageAdapter(chats);

    public static String current_name = "100";//현재방에서 내 아이디
    public static String current_room_no;//현재방 아이디
    public static String current_counter_name; //현재방에서 상대방 아이디

    Button btn_send_message;
    EditText edit_message;
    RecyclerView rv_chat_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        init_layout();
        init_system();
        //SocketClient sc = new SocketClient(getApplicationContext());

        Chat chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "안녕하세요. Emo에요~ 당신의 감정을 알려주세요", false, Constant.ACTION_START);
        chats.add(chat);


    }


    public void onSendClicked(View view) {

        Chat chat;
        if (!TextUtils.isEmpty(getMessage())) {
            try {

                chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), getMessage(), true, Constant.ACTION_TEXT);
                //mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                chats.add(chat);

                if (getMessage().contains(Constant.ACTION_START)) {
                    chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "안녕하세요. Emo에요~ 더 알고 싶은 것이 있나요?", false, Constant.ACTION_TEXT);
                }
                if (getMessage().contains(Constant.ACTION_DONE)) {
                    chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "당신 안녕", false, Constant.ACTION_TEXT);
                    //mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                    chats.add(chat);
                }
                if (getMessage().contains("사랑해")) {
                    chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "나도 사랑해", false, Constant.ACTION_TEXT);
                    //mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                    chats.add(chat);
                }
                if(getMessage().contains("check emotion")) {
                    Intent intent = new Intent(getApplicationContext(), FaceActivity.class);
                    startActivityForResult(intent, 50);
                }


                //네트워크 연결이 끊겼을때는 에러처리 스낵바를 띄운다
            } catch (Exception e) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                /*Snackbar.make(getWindow().getDecorView().getRootView(), "네트워크 연결상태를 확인해주세요", Snackbar.LENGTH_LONG).setAction("닫기", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();*/
            }

            scroll_to_bottom();
            edit_message.setText("");

        }
    }

    public void onWriteClicked(View view) {
        scroll_to_bottom();
    }

    //db_helper_chat.update_badge_to_zero(current_room_no);


    private void insert_date_line() {
        //if(!DateFormat.date_month_and_day().equals(db_helper_chat.get_last_date(current_room_no)))
        Chat chat = new Chat(current_name, current_room_no, DateFormat.date_month_day_time(), "----" + DateFormat.date_month() + "월" + DateFormat.date_day() + "일 ----", true, "dateline");
        //dbhelper
        chats.add(chat);
    }

    private void scroll_to_bottom() {
        rv_chat_message.scrollToPosition(chats.size() - 1);
    }

    private String getMessage() {
        return edit_message.getText().toString().trim();

    }

    private void init_layout() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edit_message = (EditText) findViewById(R.id.edit_message);
        btn_send_message = (Button) findViewById(R.id.btn_send_message);
        rv_chat_message = (RecyclerView) findViewById(R.id.rv_chat_message);

        rv_chat_message.setLayoutManager(new LinearLayoutManager(this));
        rv_chat_message.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
        rv_chat_message.setAdapter(new ChatMessageAdapter(chats));


    }

    //리시버 온
    //구성요소
    // {어레이리스트 객체, }

    /**
     * 초기 셋팅
     * 화면 초진입시 레이아웃 외 설정해야할 부분 관련 필요한 설정들 모아놓은 함수입니다
     */
    private void init_system() {

        //겟 인텐트 액션: 내이름, 상대 이름, 방이름
        //current_name = getIntent().getStringExtra(Constant.TAG_USER_NO);
        current_room_no = getIntent().getStringExtra(Constant.TAG_ROOM_NO);
        current_counter_name = getIntent().getStringExtra("guideName");


        //들어와있는 방의 fcm notification을 끈다(fcm 관련 작업 미완 --> 제거)
        // spf_notification.notiOff(current_room_no);

        //현위치 트래킹 함수 시작


        //채팅 메시지를 실시간으로 받는 리시버 켠다
        //chatActivityReceiver();

        //해당 방의 모든 메시지 히스트로를 디비로부터 가져온다
        chats.clear();
        //db_helper_chat.get_chat_history(current_room_no);

        //날짜선긋기
        insert_date_line();

        //액션이 {open}인 패킷을 서버로 보낸다
        // try {
        //    mWebSocketClient.send(ChatUtils.chat_to_json_connected(new Chat(current_name, current_room_no)));
        //} catch (WebsocketNotConnectedException e) {
        //    Toast.makeText(getApplicationContext(), "네트워크 오류입니다", Toast.LENGTH_LONG).show();
        //} catch (Exception e) {
        //  Toast.makeText(getApplicationContext(), "오류입니다", Toast.LENGTH_LONG).show();
        //}

        //툴바 헤더 이름을 현재 상대방이름이로 정한다
        setTitle(current_counter_name);
        scroll_to_bottom();

    }
}








