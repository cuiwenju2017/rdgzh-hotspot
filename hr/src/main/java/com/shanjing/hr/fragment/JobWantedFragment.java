package com.shanjing.hr.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.JobWantedParticularsActivity;
import com.shanjing.hr.adapter.AddressChooseAdapter;
import com.shanjing.hr.adapter.AgeAdapter;
import com.shanjing.hr.adapter.ClassificationOfIndustryAdapter;
import com.shanjing.hr.adapter.EducationalRequirementsAdapter;
import com.shanjing.hr.adapter.ExperienceRequirementsAdapter;
import com.shanjing.hr.adapter.JobWantedAdapter;
import com.shanjing.hr.adapter.SalaryPackageAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 求职
 */
public class JobWantedFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rv_job_wanted;
    private LinearLayout ll_choose;
    private TextView tv_num, tv_choose;
    private int a, b, c, d, e, f, num;
    private ImageView iv_choose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_wanted, container, false);
        rv_job_wanted = view.findViewById(R.id.rv_job_wanted);
        ll_choose = view.findViewById(R.id.ll_choose);
        tv_num = view.findViewById(R.id.tv_num);
        tv_choose = view.findViewById(R.id.tv_choose);
        iv_choose = view.findViewById(R.id.iv_choose);
        ll_choose.setOnClickListener(this);
        initData();
        return view;
    }

    private List<String> listUsernamr = Arrays.asList("李蓝枫", "张雅", "赵四", "李白洁", "李王长胜", "白雪");
    private List<String> listSex = Arrays.asList("男", "女", "男", "女", "男", "女");

    private void initData() {
        //设置管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_job_wanted.setLayoutManager(linearLayoutManager);
        //设置适配器
        JobWantedAdapter jobWantedAdapter = new JobWantedAdapter(getActivity(), listUsernamr, listSex);
        rv_job_wanted.setAdapter(jobWantedAdapter);
        //查看详情的点击事件
        jobWantedAdapter.setOnItemClickListener(new JobWantedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<String> holder, int pos) {//跳转到求职详情页
                Intent intent = new Intent(getActivity(), JobWantedParticularsActivity.class);
                intent.putExtra("name", holder.get(0));
                startActivity(intent);
            }
        });
    }

    private Dialog dialog;
    private View inflate;
    private List<String> list = Arrays.asList("全部", "余杭区", "萧山区", "滨江区", "上城区",
            "下城区", "西湖区", "拱墅区", "江干区", "富阳区");

    private List<String> list2 = Arrays.asList("全部", "3k以下", "3-5k", "5-10k", "10-20k",
            "20-50k", "50k以上", "面议");

    private List<String> list3 = Arrays.asList("全部", "初中及以下", "中专/中技", "高中", "大专",
            "本科", "硕士", "博士");

    private List<String> list4 = Arrays.asList("全部", "在校生", "应届生", "1年以内", "1-3年",
            "3-5年", "5-10年", "10年以上");

    private List<String> list5 = Arrays.asList("全部", "电子商务", "游戏", "动漫", "互联网",
            "企业服务", "媒体", "广告营销", "旅游", "数据服务", "通讯", "金融", "其他");

    private List<String> list6 = Arrays.asList("全部", "18-24", "24-28", "28-32", "32-36",
            "36以上");
    private ClassificationOfIndustryAdapter classificationOfIndustryAdapter;
    private AddressChooseAdapter addressChooseAdapter;
    private SalaryPackageAdapter salaryPackageAdapter;
    private EducationalRequirementsAdapter educationalRequirementsAdapter;
    private ExperienceRequirementsAdapter experienceRequirementsAdapter;
    private AgeAdapter ageAdapter;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_choose) {//筛选
            //求职筛选弹窗
            dialog = new Dialog(getActivity(), R.style.ChooseDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choose_job_wanted, null);
            //获取控件
            LinearLayout ll_back = inflate.findViewById(R.id.ll_back);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            RecyclerView rv_address_choose = inflate.findViewById(R.id.rv_address_choose);
            RecyclerView rv_salary_package = inflate.findViewById(R.id.rv_salary_package);
            RecyclerView rv_educational_requirements = inflate.findViewById(R.id.rv_educational_requirements);
            RecyclerView rv_experience_requirements = inflate.findViewById(R.id.rv_experience_requirements);
            final RecyclerView rv_classification_of_industry = inflate.findViewById(R.id.rv_classification_of_industry);
            RecyclerView rv_age = inflate.findViewById(R.id.rv_age);
            LinearLayout ll_on_off = inflate.findViewById(R.id.ll_on_off);
            final ImageView iv = inflate.findViewById(R.id.iv);
            Button btn_affirm = inflate.findViewById(R.id.btn_affirm);
            tv_title.setText("筛选");

            //确认的点击事件
            btn_affirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num = a + b + c + d + e + f;
                    //显示筛选数字
                    if (num != 0) {
                        tv_num.setVisibility(View.VISIBLE);
                        tv_num.setText("" + num);
                        tv_choose.setTextColor(getResources().getColor(R.color.color_hr_blue));
                        iv_choose.setBackgroundResource(R.drawable.hr_icon_arrow_dropdown_actived);
                    } else {
                        tv_num.setVisibility(View.INVISIBLE);
                        tv_choose.setTextColor(getResources().getColor(R.color.color_text));
                        iv_choose.setBackgroundResource(R.drawable.hr_icon_arrow_dropdown_default);
                    }
                    dialog.dismiss();
                }
            });

            //取消弹窗的点击事件
            ll_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            //地区选择适配器
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_address_choose.setLayoutManager(linearLayoutManager);
            addressChooseAdapter = new AddressChooseAdapter(getActivity(), list);
            rv_address_choose.setAdapter(addressChooseAdapter);
            //地区选择点击事件
            addressChooseAdapter.setOnItemClickListener(new AddressChooseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {
                    if (pos != 0) {
                        a = 1;
                    } else {
                        a = 0;
                    }

                    //传到适配器  （适配器调用方法）
                    addressChooseAdapter.getIndex(pos);
                    //刷新适配器
                    addressChooseAdapter.notifyDataSetChanged();
                }
            });

            //薪资待遇适配器
            StaggeredGridLayoutManager linearLayoutManager2 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_salary_package.setLayoutManager(linearLayoutManager2);
            salaryPackageAdapter = new SalaryPackageAdapter(getActivity(), list2);
            rv_salary_package.setAdapter(salaryPackageAdapter);
            //地区选择点击事件
            salaryPackageAdapter.setOnItemClickListener(new SalaryPackageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {
                    if (pos != 0) {
                        b = 1;
                    } else {
                        b = 0;
                    }
                    //传到适配器  （适配器调用方法）
                    salaryPackageAdapter.getIndex(pos);
                    //刷新适配器
                    salaryPackageAdapter.notifyDataSetChanged();
                }
            });

            //学历要求适配器
            StaggeredGridLayoutManager linearLayoutManager3 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_experience_requirements.setLayoutManager(linearLayoutManager3);
            educationalRequirementsAdapter = new EducationalRequirementsAdapter(getActivity(), list3);
            rv_experience_requirements.setAdapter(educationalRequirementsAdapter);
            //学历选择点击事件
            educationalRequirementsAdapter.setOnItemClickListener(new EducationalRequirementsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {
                    if (pos != 0) {
                        c = 1;
                    } else {
                        c = 0;
                    }

                    //传到适配器  （适配器调用方法）
                    educationalRequirementsAdapter.getIndex(pos);
                    //刷新适配器
                    educationalRequirementsAdapter.notifyDataSetChanged();
                }
            });

            //经验要求适配器
            StaggeredGridLayoutManager linearLayoutManager4 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_educational_requirements.setLayoutManager(linearLayoutManager4);
            experienceRequirementsAdapter = new ExperienceRequirementsAdapter(getActivity(), list4);
            rv_educational_requirements.setAdapter(experienceRequirementsAdapter);
            //经验选择点击事件
            experienceRequirementsAdapter.setOnItemClickListener(new ExperienceRequirementsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {

                    if (pos != 0) {
                        d = 1;
                    } else {
                        d = 0;
                    }

                    //传到适配器  （适配器调用方法）
                    experienceRequirementsAdapter.getIndex(pos);
                    //刷新适配器
                    experienceRequirementsAdapter.notifyDataSetChanged();
                }
            });

            //行业分类适配器
            StaggeredGridLayoutManager linearLayoutManager5 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager5.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_classification_of_industry.setLayoutManager(linearLayoutManager5);
            classificationOfIndustryAdapter = new ClassificationOfIndustryAdapter(getActivity(), list5, ll_on_off, iv);
            rv_classification_of_industry.setAdapter(classificationOfIndustryAdapter);
            //行业分类点击事件
            classificationOfIndustryAdapter.setOnItemClickListener(new ClassificationOfIndustryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {
                    if (pos != 0) {
                        e = 1;
                    } else {
                        e = 0;
                    }

                    //传到适配器  （适配器调用方法）
                    classificationOfIndustryAdapter.getIndex(pos);
                    //刷新适配器
                    classificationOfIndustryAdapter.notifyDataSetChanged();
                }
            });

            //年龄段适配器
            StaggeredGridLayoutManager linearLayoutManager6 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager6.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_age.setLayoutManager(linearLayoutManager6);
            ageAdapter = new AgeAdapter(getActivity(), list6);
            rv_age.setAdapter(ageAdapter);
            //年龄段点击事件
            ageAdapter.setOnItemClickListener(new AgeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(List<String> holder, int pos) {
                    if (pos != 0) {
                        f = 1;
                    } else {
                        f = 0;
                    }

                    //传到适配器  （适配器调用方法）
                    ageAdapter.getIndex(pos);
                    //刷新适配器
                    ageAdapter.notifyDataSetChanged();
                }
            });

            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        }
    }
}
