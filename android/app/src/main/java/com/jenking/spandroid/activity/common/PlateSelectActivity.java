package com.jenking.spandroid.activity.common;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.PlateModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.PlatePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PlateSelectActivity extends BaseActivity {

    private Context context;
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List<PlateModel> data;
    private PlatePresenter platePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_select);
    }

    public void initData(){
        context = this;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<PlateModel>(this,data,R.layout.plate_select_item) {
            @Override
            protected void convert(BaseViewHolder helper, PlateModel item) {
                helper.setText(R.id.plate_name,item.getPlate_name());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);

        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (data.get(position) != null){
                    Intent intent = new Intent();
                    intent.putExtra("plate_id",data.get(position).getId());
                    intent.putExtra("plate_name",data.get(position).getPlate_name());
                    setResult(1200,intent);
                    finish();
                }
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        platePresenter = new PlatePresenter(this);
        platePresenter.setOnCallBack(new PlatePresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getList()!=null && resultModel.getList().size()>0){
                                Log.e("platePresenter",resultModel.getList().toString());
                                List<PlateModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });

        platePresenter.getAllPlate(RS.getBaseParams(context));

    }
}
