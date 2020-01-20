package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchCommodityActivity extends BaseActivity {

    @BindView(R.id.wish_name)
    EditText commodity_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String keyword = commodity_name.getText().toString();
        if (StringUtil.isNotEmpty(keyword)){
            Intent intent = new Intent(this,SearchCommodityResultActivity.class);
            intent.putExtra("keyword",keyword);
            startActivity(intent);
        }else{
            CommonTipsDialog.show(this,"提示","请输入关键词",false);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_commodity);
    }
}
