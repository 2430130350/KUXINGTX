package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.friend;

import java.util.ArrayList;

public interface FInfoMvp {
    public interface View{
        //查询用户信息功能
        public void query_infoSuccess();
        //修改用户信息功能
        public void modify_infoSuccess();
        //添加好友功能
        public void relation_addSuccess();
        //确认好友功能
        public void relation_confirmSuccess();
        //删除好友功能
        public void relation_delSuccess();
        //查询所有好友信息
        public void relation_my_all_qurSuccess();
        //查找好友关系
        public void relation_my_one_qurSuccess(ArrayList<friend> friends);
    }
    public interface Presenter{
        //查询用户信息功能
        public void query_info(String userName,String password);
        public void query_infoSuccess();
        //修改用户信息功能
        public void modify_info(String ouserName,String opassword,String nuserName,String npassword);
        public void modify_infoSuccess();
        //添加好友功能
        public void relation_add(long uid,long fid,String nick_name,String description);
        public void relation_addSuccess();
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
        public void relation_my_one_qurSuccess(ArrayList<friend> friends);
    }
    public interface Model{
        //查询用户信息功能
        public void query_infoPost(String userName,String password);
        //修改用户信息功能
        public void modify_infoPost(String ouserName,String opassword,String nuserName,String npassword);
        //添加好友功能
        public void relation_addPost(long uid,long fid,String nick_name,String description);
        //确认好友功能
        public void relation_confirmPost(long uid,long fid,String nick_name,String description);
        //删除好友功能
        public void relation_delPost(long uid,long fid);
        //查询所有好友信息
        public void relation_my_all_qurPost(long uid);
        //查找好友关系
        public void relation_my_one_qurPost(long uid,long fid);
    }
}
