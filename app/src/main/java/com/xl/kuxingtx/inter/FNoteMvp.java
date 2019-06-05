package com.xl.kuxingtx.inter;

public interface FNoteMvp {
    public interface View{
        public void delNoteBeanSuccess();
    }
    public interface Presenter{
        public void delNoteBean(int position);

        public void delNoteBeanSuccess();

    }
    public interface Model{
        public void delNoteBeanToFile(int position);
    }
}
