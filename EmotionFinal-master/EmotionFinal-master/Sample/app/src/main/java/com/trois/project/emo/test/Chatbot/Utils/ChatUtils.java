package com.trois.project.emo.test.Chatbot.Utils;

import android.content.Context;
import android.util.Log;

import com.trois.project.emo.test.Chatbot.Model.Chat;

import org.json.JSONException;
import org.json.JSONObject;

import static com.trois.project.emo.test.Chatbot.Activity.ChatBotActivity.current_counter_name;
import static com.trois.project.emo.test.Chatbot.Activity.ChatBotActivity.current_name;
import static com.trois.project.emo.test.Chatbot.Activity.ChatBotActivity.current_room_no;

public class ChatUtils {

    /**
     * [OUTLINE]
     * 목적에 따라 채팅 메시지를 어떤 형식으로 바꿔주는 유틸 메소드가 모여있는 클래스이다
     * <p>
     * Part1 [ json_to_string ]
     * json 형식을 string 로 바꾸는 함수
     * 바꾼 데이터는 데이터 베이스에 인서트
     * <p>
     * Part2 [ json_to_chat_front ]
     * json 형식을 message 객체로 바꾸는 함수
     * 바꾼 데이터는 ChatActivity에 뿌려준다
     * <p>
     * Part3 [ message_to_json ]
     * message 객체를 json 형식으로 바꿔준다
     * 채팅서버로 패킷을 보낼 때 쓴다
     */

    Context context;

    public ChatUtils(Context context) {
        this.context = context;

    }

    public boolean counter;
    private String TAG = "ChatUtils";

    public String json_to_string(String json) throws JSONException {

        //DBHelperChatting dbHelperChattingChat = new DBHelperChatting(context, "CHAT.db", null, 1);
        //        DBHelperChatting dbHelperChattingRoom = new DBHelperChatting(context, "ROOM.db", null, 1);

        JSONObject jsonObject = new JSONObject(json);

        //String chatUtilmyName = chat_id;
        String message_no = null;

        try {
            switch (jsonObject.getString(Constant.TAG_ACTION)) {

                case Constant.ACTION_TEXT:
                    message_no = jsonObject.getString((Constant.TAG_MESSAGE_NO));
                    //dbHelperChattingChat.insert_chat(
                    //                                chatUtilmyName,
                    //                                jsonObject.getString(TAG_ROOM_NO),
                    //                                jsonObject.getString(TAG_MESSAGE),
                    //                                read_or_unread,
                    //                                DateFormat.date_month_day_time(),
                    //                                "true",
                    //                                ACTION_TEXT, 0, 0
                    //                        );
                    //                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));
                    //
                    //                    } else if (!jsonObject.get(TAG_USER_NO).equals(chatUtilmyName)) {
                    //                        dbHelperChattingChat.insert_chat(
                    //                                dbHelperChattingRoom.get_guide_real_name(jsonObject.getString(TAG_ROOM_NO)),
                    //                                jsonObject.getString(TAG_ROOM_NO),
                    //                                jsonObject.getString(TAG_MESSAGE),
                    //                                read_or_unread,
                    //                                DateFormat.date_month_day_time(),
                    //                                "false",
                    //                                ACTION_TEXT, 0, 0);
                    //                        dbHelperChattingRoom.update_room_sequence(jsonObject.getString(TAG_ROOM_NO));
                    //                    }
                    return message_no;


                default:
                    return null;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return message_no;

    }

    public Chat json_to_chat_front(String json) throws JSONException {
        Log.e("[json_to_chat_front]", json);
        Chat chat = null;
        JSONObject jsonObject = new JSONObject(json);
        //String returnRead = "unread";
        //String chatUtilmyName = hori_id;

        try {
            switch (jsonObject.getString(Constant.TAG_ACTION)) {
                case Constant.ACTION_TEXT:
                    if (jsonObject.getString(Constant.TAG_ROOM_NO).equals(current_room_no)) {
                        if (jsonObject.get(Constant.TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(
                                    current_counter_name,
                                    jsonObject.getString(Constant.TAG_ROOM_NO),
                                    DateFormat.date_apm(),
                                    jsonObject.getString(Constant.TAG_MESSAGE),
                                    true, Constant.ACTION_TEXT);
                        } else if (!jsonObject.get(Constant.TAG_USER_NO).equals(current_name)) {
                            chat = new Chat(
                                    current_counter_name,
                                    jsonObject.getString(Constant.TAG_ROOM_NO),
                                    DateFormat.date_apm(),
                                    jsonObject.getString(Constant.TAG_MESSAGE),
                                    false, Constant.ACTION_TEXT);
                        }
                    }
                    return chat;

                case Constant.ACTION_DONE:
                    if (jsonObject.getString(Constant.TAG_ROOM_NO).equals(current_room_no)) {
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "오늘 일정 완료하시겠습니까?", false, Constant.ACTION_DONE);

                    }

                    return chat;

                case Constant.ACTION_START:
                    if (jsonObject.getString(Constant.TAG_ROOM_NO).equals(current_room_no)) {
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "무엇을 도와드릴까요?", false, Constant.ACTION_START);

                    }

                    return chat;


            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }
}

