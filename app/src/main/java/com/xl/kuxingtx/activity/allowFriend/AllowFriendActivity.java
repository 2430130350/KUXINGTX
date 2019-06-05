package com.xl.kuxingtx.activity.allowFriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.FriendInfo;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.activity.myInfo.MyInfoActivity;
import com.xl.kuxingtx.activity.readNote.ReadNoteActivity;
import com.xl.kuxingtx.fragment.Note.NoteAdapter;
import com.xl.kuxingtx.fragment.Note.NoteBean;
import com.xl.kuxingtx.inter.AllowFriendMvp;
import com.xl.kuxingtx.utils.CodeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

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
    @ViewInject(R.id.titleBar)
    private TitleBar titleBar;

    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;

    private AllowFriendAdapter allowFriendAdapter;
    private List<Friend> friends = new ArrayList<Friend>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_allow_friend);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        x.view().inject(this);


/*        Friend friend;
        for (int i = 0; i < 15; i++) {
            friend = new Friend();
            friend.setFid(999);
            friends.add(friend);
        }*/
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allow_recycler.setLayoutManager(layoutManager);

        //创建适配器
        allowFriendAdapter = new AllowFriendAdapter(R.layout.allow_friend_item, friends);

        //给RecyclerView设置适配器
        allow_recycler.setAdapter(allowFriendAdapter);

        initData();
        initListener();
    }

    private void initData(){
        allowFriendsPresenter.relation_my_all_qur(UserInfo.getUserInfo().getId());
    }

    private void initListener(){
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowFriendActivity.this.finish();
            }
        });

        allow_recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                int itemViewId = view.getId();

                long mid = UserInfo.getUserInfo().getId();
                long fid = friends.get(position).getFid();
                switch (itemViewId) {
                    case R.id.allow_btn:
                        allowFriendsPresenter.relation_confirm(mid, fid, "测试", "测试描述");
                        break;
                    case R.id.reject_btn:
                        allowFriendsPresenter.relation_del(mid, fid);
                        break;
                }
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void relation_confirmSuccess() {
        Toast.makeText(this, "已同意对方申请、", Toast.LENGTH_SHORT).show();
        allowFriendsPresenter.relation_my_all_qur(UserInfo.getUserInfo().getId());
    }

    private void updateData(){
        FriendInfo.getFriendInfo().sortFriends();
        //下面是获得好友数据并填充到界面adapter中、
        List<Friend> tmpFriends = FriendInfo.getFriendInfo().getRequestFriends();
        this.friends.clear();
        for(int i = 0; i<tmpFriends.size(); i++){
            this.friends.add(tmpFriends.get(i));
        }
        refreshLayout.finishRefresh(500);
        Toast.makeText(this, "刷新成功、", Toast.LENGTH_SHORT).show();
        this.allowFriendAdapter.notifyDataSetChanged();
    }

    @Override
    public void relation_delSuccess() {
        Toast.makeText(this, "已拒绝对方申请、", Toast.LENGTH_SHORT).show();
        allowFriendsPresenter.relation_my_all_qur(UserInfo.getUserInfo().getId());
    }

    @Override
    public void relation_my_all_qurSuccess() {
        this.updateData();
    }

    @Override
    public void relation_my_one_qurSuccess( ArrayList<Friend> Friends) {

    }
}
