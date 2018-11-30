package com.trois.project.emo.test.Chatbot.Model;

public class Chat {
        private String user_no, room_no;// 메시지내에서 내 고유번호, 방 고유번호
        private String message, action;//메시지 내용, 메시지 종류
        private String timestamp, read_check;//메시지 보낸시간, 읽은 여부
        private boolean is_me;//내가 보낸 메시지인가


        //기본 텍스트 담을 구조체, 남이 보낸 이미지 담을 구조체
        public Chat(String user_no, String room_no, String timestamp, String message, boolean is_me, String action) {
            this.user_no = user_no;
            this.timestamp = timestamp;
            this.room_no = room_no;
            this.message = message;
            this.is_me = is_me;
            this.action = action;
        }
        //소켓 열 때 보내는 메시지 구조체
        public Chat(String user_no, String room_no ) {
            this.user_no = user_no;
            this.room_no = room_no;

        }

        public String getUser_no() {
            return user_no;
        }

        public void setUser_no(String user_no) {
            this.user_no = user_no;
        }

        public String getRoom_no() {
            return room_no;
        }

        public void setRoom_no(String room_no) {
            this.room_no = room_no;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getRead_check() {
            return read_check;
        }

        public void setRead_check(String read_check) {
            this.read_check = read_check;
        }

        public boolean isIs_me() {
            return is_me;
        }

        public void setIs_me(boolean is_me) {
            this.is_me = is_me;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
}
