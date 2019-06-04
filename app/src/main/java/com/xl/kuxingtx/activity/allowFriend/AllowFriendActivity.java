package com.xl.kuxingtx.activity.allowFriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.fragment.Note.NoteAdapter;
import com.xl.kuxingtx.fragment.Note.NoteBean;
import com.xl.kuxingtx.inter.AllowFriendMvp;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_allow_friend)
public class AllowFriendActivity extends AppCompatActivity implements View.OnClickListener, AllowFriendMvp.View {
    private AllowFriendMvp.Presenter allowFriendsPresenter = new AllowFriendPresenter(this);
    @ViewInject(R.id.allow_recycler)
    private RecyclerView allow_recycler;

    private AllowFriendAdapter allowFriendAdapter;
    private List<Friend> friends = new ArrayList<Friend>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_allow_friend);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        x.view().inject(this);


        Friend friend;
        for (int i = 0; i < 15; i++) {
            friend = new Friend();
            friend.setFid(999);
            friends.add(friend);
        }
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allow_recycler.setLayoutManager(layoutManager);

        //创建适配器
        allowFriendAdapter = new AllowFriendAdapter(R.layout.allow_friend_item, friends);

        //给RecyclerView设置适配器
        allow_recycler.setAdapter(allowFriendAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
