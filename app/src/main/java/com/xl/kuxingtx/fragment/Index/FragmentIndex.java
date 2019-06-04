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
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.IndexMvp;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

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
    public boolean isFirstLoc = true;
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
        //设置定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.strokeWidth(1);
        myLocationStyle.interval(2000);
        aMap.setMyLocationStyle(myLocationStyle);
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
        PoiSearch.Query query = new PoiSearch.Query("武汉", "110000", "武汉");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this.getContext(), query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(mLocationClient.getLastKnownLocation().getLatitude(),
                mLocationClient.getLastKnownLocation().getLongitude()), 1000));//设置周边搜索的中心点以及半径
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                ArrayList<PoiItem> poiItemArrayList=poiResult.getPois();
                for(int j=0;j<10;j++){
                    Marker marker = aMap.addMarker(new MarkerOptions().position(new LatLng(poiItemArrayList
                            .get(j).getLatLonPoint().getLatitude(),poiItemArrayList
                            .get(j).getLatLonPoint().getLongitude()))
                            .title(poiItemArrayList.get(j).getTitle())
                            .snippet(poiItemArrayList.get(j).getSnippet()));
                    marker.showInfoWindow();
                }
                //aMap.setInfoWindowAdapter(minforWindow);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    //定义展示板样式
    public AMap.InfoWindowAdapter minforWindow = new AMap.InfoWindowAdapter() {
        private View infoWindow = null;
    @Override
    public View getInfoWindow(Marker marker) {
        if(infoWindow == null) {
            infoWindow = LayoutInflater.from(FragmentIndex.super.getContext()).inflate
                    (R.layout.fragment_fragment_index,null);
            TextView textView = infoWindow.findViewById(R.id.infowindow);
            textView.setText(marker.getId());
            textView.setText(marker.getTitle());
        }
        //render(marker, infoWindow);
        return infoWindow;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        //如果想修改自定义Infow中内容，请通过view找到它并修改

        }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
};
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
                    if(isFirstLoc)
                    {
                        //设置缩放级别
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                        //将地图移动到定位点
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(),
                                aMapLocation.getLongitude())));
                        //可在其中解析amapLocation获取相应内容。

                        //UserInfo.getUserInfo().setLocation("");
                        mListener.onLocationChanged(aMapLocation);
                        isFirstLoc = false;

                    }
                } else Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() +
                        ", errInfo:" + aMapLocation.getErrorInfo());
            }
        }
    };
}