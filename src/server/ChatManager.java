package server;

/**
 * Created by Mr on 2019/4/23.
 */

import java.util.ArrayList;
import java.util.Vector;

//一个聊天服务器只能有一个manager，要单例化处理
public class ChatManager {

    private ChatManager(){}
    private static final ChatManager CM = new ChatManager();
    public static ChatManager getChatManager(){
        return CM;
    }

    MyServer server;
    public void setWindow(MyServer server) {
        this.server = server;
    }

    public Vector<ChatSocket> vector = new Vector<ChatSocket>();
    public String[] userOnlineList = new String[200];
    ArrayList<String> userOnlineList0 = new ArrayList<>();
    /*增加ChatSocket 实例到vector中*/
    public void add(ChatSocket cs){
        vector.add(cs);
    }
    public void addUsername(String username){
        System.out.println("in charmanager:" + username);
        userOnlineList0.add(0, username);
    }
    /*发布消息给其他客户端
     *ChatSocket cs： 调用publish的线程
     *msg：要发送的信息 */
    public void publish(ChatSocket cs, String msg){
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket csTemp = vector.get(i);
            if (!cs.equals(csTemp)) {
                csTemp.out(msg+"\n");//不用发送给自己。
            }
        }
    }

    //服务器响应客户端请求
    public void respond(ChatSocket cs, String msg){
        cs.out(msg+"\n");
        //只发送给自己
    }

    //服务器发送消息给客户端
    public void publish(String msg){
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket csTemp = vector.get(i);
                csTemp.out(msg+"\n");
        }
    }

    public Vector<ChatSocket> getVector() {
        return vector;
    }

    public void setVector(Vector<ChatSocket> vector) {
        this.vector = vector;
    }
}
