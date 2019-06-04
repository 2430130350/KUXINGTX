package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.Friend;

import java.util.ArrayList;

public interface AllowFriendMvp {
    public interface View{
        //确认好友功能
        public void relation_confirmSuccess();
        //删除好友功能
        public void relation_delSuccess();
        //查询所有好友信息
        public void relation_my_all_qurSuccess();
        //查找好友关系
        public void relation_my_one_qurSuccess(ArrayList<Friend> Friends);
    }
    public interface Presenter{
        //确认好友功能
        public void relation_confirm(long uid,long fid,String nick_name,String description);
        public void relation_confirmSuccess();
        //删除好友功能
        public void relation_del(long uid,long fid);
        public void relation_delSuccess();
        //查询所有好友信息
        public void relation_my_all_qur(long uid);
        public void relation_my_all_qurSuccess();
        //查找好友关系
        public void relation_my_one_qur(long uid,long fid);
        public void relation_my_one_qurSuccess(ArrayList<Friend> Friends);
    }
    public interface Model{
        //确认好友功能
        public void relation_confirmPost(long uid,long fid,String nick_name,String description);
        //删除好友功能
        public void relation_delPost(long uid,long fid);
        //查询所有好友信息
        public void relation_my_all_qurPost(long uid);
        //查找好友关系
        public void relation_my_one_qurPost(long uid ,long fid);
    }
}
