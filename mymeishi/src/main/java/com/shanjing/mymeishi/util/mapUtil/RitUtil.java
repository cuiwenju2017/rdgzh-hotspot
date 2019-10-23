package com.shanjing.mymeishi.util.mapUtil;

import android.content.Context;


import com.shanjing.mymeishi.R;

import java.util.List;

/**
 * Created by LHP on 2018/8/7/007.
 */

public class RitUtil  extends RoutUtil{
    public RitUtil(Context context) {
//        super(context);
    }

//    private PolylineOptions mPolylineOptions;
//
//    private BitmapDescriptor walkStationDescriptor= null;
//
//    private RidePath ridePath;
//    /**
//     * 通过此构造函数创建骑行路线图层。
//     * @param context 当前activity。
//     * @param amap 地图对象。
//     * @param path 骑行路线规划的一个方案。详见搜索服务模块的路径查询包（com.amap.api.services.route）中的类 <strong><a href="../../../../../../Search/com/amap/api/services/route/WalkStep.html" title="com.amap.api.services.route中的类">WalkStep</a></strong>。
//     * @param start 起点。详见搜索服务模块的核心基础包（com.amap.api.services.core）中的类<strong><a href="../../../../../../Search/com/amap/api/services/core/LatLonPoint.html" title="com.amap.api.services.core中的类">LatLonPoint</a></strong>。
//     * @param end 终点。详见搜索服务模块的核心基础包（com.amap.api.services.core）中的类<strong><a href="../../../../../../Search/com/amap/api/services/core/LatLonPoint.html" title="com.amap.api.services.core中的类">LatLonPoint</a></strong>。
//     * @since V3.5.0
//     */
//    public RitUtil(Context context, AMap amap, RidePath path,
//                   LatLonPoint start, LatLonPoint end) {
//        super(context);
//        this.mAMap = amap;
//        this.ridePath = path;
//        startPoint = AmUtil.convertToLatLng(start);
//        endPoint = AmUtil.convertToLatLng(end);
//    }
//    /**
//     * 添加骑行路线到地图中。
//     * @since V3.5.0
//     */
//    public void addToMap() {
//
//        initPolylineOptions();
//        try {
//            List<RideStep> ridePaths = ridePath.getSteps();
//            mPolylineOptions.add(startPoint);
//            for (int i = 0; i < ridePaths.size(); i++) {
//                RideStep rideStep = ridePaths.get(i);
//                LatLng latLng = AmUtil.convertToLatLng(rideStep
//                        .getPolyline().get(0));
//
//                addRideStationMarkers(rideStep, latLng);
//                addRidePolyLines(rideStep);
//            }
//            mPolylineOptions.add(endPoint);
//            addStartAndEndMarker();
//
//            showPolyline();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * @param rideStep
//     */
//    private void addRidePolyLines(RideStep rideStep) {
//        mPolylineOptions.addAll(AmUtil.convertArrList(rideStep.getPolyline()));
//    }
//    /**
//     * @param rideStep
//     * @param position
//     */
//    private void addRideStationMarkers(RideStep rideStep, LatLng position) {
//        addStationMarker(new MarkerOptions()
//                .position(position)
//                .title("\u65B9\u5411:" + rideStep.getAction()
//                        + "\n\u9053\u8DEF:" + rideStep.getRoad())
//                .snippet(rideStep.getInstruction()).visible(nodeIconVisible)
//                .anchor(0.5f, 0.5f).icon(walkStationDescriptor));
//    }
//
//    /**
//     * 初始化线段属性
//     */
//    private void initPolylineOptions() {
//
//        if(walkStationDescriptor == null) {
//            walkStationDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.amap_ride);
//        }
//        mPolylineOptions = null;
//        mPolylineOptions = new PolylineOptions();
//        mPolylineOptions.color(getDriveColor()).width(getRouteWidth());
//    }
//    private void showPolyline() {
//        addPolyLine(mPolylineOptions);
//    }
}
