package com.xl.kuxingtx.inter;

public interface FInfoMvp {
    public interface View{}
    public interface Presenter{
        //查询用户信息功能
        public void query_info(String name,String password);
        //修改用户信息功能
        public void modify_info(String oname,String opassword,String nname,String npassword);
    }
    public interface Model{
        //查询用户信息功能

        //修改用户信息功能
    }
}
