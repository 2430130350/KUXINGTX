package com.xl.kuxingtx.fragment.Mine.info;


import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xl.kuxingtx.FriendInfo;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.activity.allowFriend.AllowFriendActivity;
import com.xl.kuxingtx.activity.myInfo.MyInfoActivity;
import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.inter.FInfoMvp;
import com.xuexiang.xui.widget.edittext.ValidatorEditText;
import com.xuexiang.xui.widget.textview.MarqueeTextView;
import com.xuexiang.xui.widget.textview.label.LabelButtonView;
import com.xuexiang.xui.widget.textview.label.LabelTextView;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import org.xutils.x;

import java.util.ArrayList;

import java.util.List;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

@ContentView(R.layout.fragment_fragment_info)//加载的xml文件
public class FragmentInfo extends Fragment implements View.OnClickListener, FInfoMvp.View{
    private FInfoMvp.Presenter infoPresenter = new InfoPresenter(this);
    @ViewInject(R.id.tv_marquee)
    private MarqueeTextView tv_marquee;
    @ViewInject(R.id.friend_recycler)
    private RecyclerView friend_recycler;
    @ViewInject(R.id.mine_username)
    private LabelTextView mine_username;
    @ViewInject(R.id.new_friend_btn)
    private LabelButtonView new_friend_btn;
    @ViewInject(R.id.friend_apply_btn)
    private LabelButtonView friend_apply_btn;
/*    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;*/

    private FriendAdapter friendAdapter;
    private List<FriendBean> friendDatas = new ArrayList<>();
    private List<String> tvDatas = new ArrayList<String>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fragment_info, null);
        //ViewUtils.inject(getActivity());
        View view = x.view().inject(this, inflater, container);

        tvDatas.add("内测用户、");
        tvDatas.add("VIP");
        tv_marquee.startSimpleRoll(tvDatas);


/*        FriendBean friendBean;
        for (int i = 0; i < 15; i++) {
            friendBean = new FriendBean();
            friendBean.setUserName("测试、");
            friendDatas.add(friendBean);
        }*/
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        friend_recycler.setLayoutManager(layoutManager);

        //创建适配器
        friendAdapter = new FriendAdapter(R.layout.friend_item, friendDatas);

        //给RecyclerView设置适配器
        friend_recycler.setAdapter(friendAdapter);
        initData();
        initListener();

        return view;
    }

   private void initListener() {
       mine_username.setOnClickListener(this);
       new_friend_btn.setOnClickListener(this);
       friend_apply_btn.setOnClickListener(this);

   }

   private void initData(){
        infoPresenter.relation_my_all_qur(UserInfo.getUserInfo().getId());
   }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.mine_username://进入个人信息详情页面、
               startActivity(new Intent(getActivity(), MyInfoActivity.class));
               break;
           case R.id.new_friend_btn:
               AnyLayer.with(this.getActivity())
                       .contentView(R.layout.new_friend_dialog)
                       .gravity(Gravity.CENTER)
                       .onVisibleChangeListener(new LayerManager.OnVisibleChangeListener() {
                           @Override
                           public void onShow(AnyLayer anyLayer) {
                               /*EditText editText = anyLayer.getView(R.id.et_dialog_content);
                               anyLayer.compatSoftInput(editText);*/
                           }

                           @Override
                           public void onDismiss(AnyLayer anyLayer) {
                               anyLayer.removeSoftInput();
                           }
                       })
                       .contentAnim(new LayerManager.IAnim() {
                           @Override
                           public Animator inAnim(View content) {
                               return AnimHelper.createZoomAlphaInAnim(content);
                           }

                           @Override
                           public Animator outAnim(View content) {
                               return AnimHelper.createZoomAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.no)
                       .onClick(R.id.yes, new LayerManager.OnLayerClickListener() {
                           @Override
                           public void onClick(AnyLayer anyLayer, View v) {
                               anyLayer.dismiss();
                               ValidatorEditText friend_id_edit = anyLayer.getView(R.id.friend_id_edit);
                               ValidatorEditText friend_nickname_edit = anyLayer.getView(R.id.friend_nickname_edit);
                               ValidatorEditText friend_description_edit = anyLayer.getView(R.id.friend_description_edit);
                               long friend_id = Long.parseLong(friend_id_edit.getInputValue());
                               String friend_nickname = friend_nickname_edit.getInputValue();
                               String friend_description = friend_description_edit.getInputValue();

                               long my_id = UserInfo.getUserInfo().getId();
                               infoPresenter.relation_my_all_qur(my_id);
                               FriendInfo.getFriendInfo().sortFriends();
                               infoPresenter.relation_add(my_id, friend_id, friend_nickname, friend_description);
                               /*EditText et = anyLayer.getView(R.id.et_dialog_content);
                               Toast.makeText(FullScreenActivity.this, et.getText().toString(), Toast.LENGTH_SHORT).show();*/
                           }
                       })
                       .show();
               break;
           case R.id.friend_apply_btn:
               Intent intent = new Intent(this.getContext(), AllowFriendActivity.class);
               startActivity(intent);
               break;
        }
    }

    @Override
    public void query_infoSuccess() {

    }

    @Override
    public void modify_infoSuccess() {

    }

    @Override
    public void relation_addSuccess() {
        Toast.makeText(getActivity(), "发起申请成功、", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void relation_confirmSuccess() {

    }

    @Override
    public void relation_delSuccess() {

    }

    private void updateData(){
        FriendInfo.getFriendInfo().sortFriends();
        //下面是获得好友数据并填充到界面adapter中、
        List<Friend> tmpFriends = FriendInfo.getFriendInfo().getAlreadyFriends();
        this.friendDatas.clear();
        for(int i = 0; i<tmpFriends.size(); i++){
            Friend tmpFriend = tmpFriends.get(i);
            FriendBean tmpFriendBean = new FriendBean(
                    tmpFriend.getUid(),
                    tmpFriend.getFid(),
                    tmpFriend.getMconfirm(),
                    tmpFriend.getFconfirm(),
                    tmpFriend.getNick_name(),
                    tmpFriend.getDescription()
            );

            this.friendDatas.add(tmpFriendBean);
        }
        this.friendAdapter.notifyDataSetChanged();
    }

    @Override
    public void relation_my_all_qurSuccess() {
        this.updateData();
    }

    @Override
    public void relation_my_one_qurSuccess(ArrayList<Friend> Friends) {

    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
    }

}


