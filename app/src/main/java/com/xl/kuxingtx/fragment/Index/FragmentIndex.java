package com.xl.kuxingtx.fragment.Index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.IndexMvp;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public class FragmentIndex extends Fragment implements View.OnClickListener, IndexMvp.View{
    private IndexMvp.Presenter indexPresenter = new IndexPresenter(this);
    private Unbinder unbinder;
    @BindView(R.id.home_city)
    public TextView home_city;


    @BindView(R.id.map)
    public MapView mMapView;

    /**
     * 以下是涛涛的变量
     */
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMap aMap = null;

    private LocationSource.OnLocationChangedListener mListener;

    //分界线

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_index, container, false);
        //View view = x.view().inject(this, inflater, container);
        unbinder = ButterKnife.bind(this, view);
        home_city.setOnClickListener(this);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mMapView.onCreate(savedInstanceState);
        setUpLocation();
        //aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        return view;
    }

    /**
     * 初始化定位条件
     */
    private void setUpLocation() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            //设置ui条件
        }
        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setMyLocationButtonEnabled(true);
        aMap.setLocationSource(mLocationSource);
        aMap.setMyLocationEnabled(true);
    }


    /**
     * 实现定位
     */
    public void setLocation() {
        mLocationClient = new AMapLocationClient(this.getContext());
        //设置监听回调
        mLocationClient.setLocationListener(mAMapLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
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
/*        switch (v.getId()){
            case R.id.home_city://地址
                startActivity(new Intent(getActivity(),CityActivity.class));
                break;
            case R.id.home_map://地图
                break;
            case R.id.home_search://搜索
                break;
        }*/
    }

    /**
     * 部分变量
     */
    public LocationSource mLocationSource = new LocationSource() {
        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {
            mListener = onLocationChangedListener;
            setLocation();
        }

        @Override
        public void deactivate() {
            mListener = null;
            if (mLocationClient != null) {
                mLocationClient.stopLocation();
                mLocationClient.onDestroy();
            }
            mLocationClient = null;
        }
    };

    public AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        /**
         * 设置监听位置改变
         *
         * @param aMapLocation
         */
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    mListener.onLocationChanged(aMapLocation);
                } else Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() +
                        ", errInfo:" + aMapLocation.getErrorInfo());
            }
        }
    };
}