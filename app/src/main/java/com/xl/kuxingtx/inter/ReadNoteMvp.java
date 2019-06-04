package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.fragment.Around.TrendsBean;

public interface ReadNoteMvp {
    public interface View{}
    public interface Presenter{
        public void uploadTrends(String content);
    }
    public interface Model{
        public void uploadTrendsPost(TrendsBean trendsBean);
    }
}
