package com.xl.kuxingtx.fragment.Note;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.R;
import com.zzhoujay.richtext.RichText;

import java.util.List;

public class NoteAdapter extends BaseQuickAdapter<NoteBean, BaseViewHolder> {
    public NoteAdapter(int layoutResId, List data){
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, NoteBean item) {
        helper.setText(R.id.time, item.getDateString("yyyy.MM.dd"));
        TextView note_content = helper.getView(R.id.note_content);
        RichText.from(item.getPartialContent()).singleLoad(false).into(note_content);

        helper.addOnClickListener(R.id.note_content);

    }
}
