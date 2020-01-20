package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CommodityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CommodityEditActivity extends BaseActivity {


    private String imagePath;//本地图像url
    private String imageUrl;//服务器图像url

    private CommodityModel model;
    private CommodityPresenter presenter;

    private String plate_id;
    private String plate_name;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.commodity_name)
    EditText commodity_name;

    @BindView(R.id.commodity_plate)
    TextView commodity_plate;

    @BindView(R.id.commodity_price)
    EditText commodity_price;

    @BindView(R.id.commodity_old_price)
    EditText commodity_old_price;

    @BindView(R.id.commodity_describe)
    EditText commodity_describe;

    @BindView(R.id.commodity_produce_time)
    EditText commodity_produce_time;

    @BindView(R.id.commodity_validity)
    EditText commodity_validity;

    @BindView(R.id.commodity_img)
    ImageView commodity_img;

    @BindView(R.id.loading)
    CommonLoading loading;


    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.commodity_img)
    void commodity_img(){
        selectCommodityImg();
    }

    @OnClick(R.id.commodity_plate)
    void select_commodity_plate(){
        Intent intent = new Intent(this,PlateSelectActivity.class);
        startActivityForResult(intent,1200);
    }

    @OnClick(R.id.submit)
    void submit(){
        if (StringUtil.isNotEmpty(imagePath)){
            setLoadingEnable(true);
            uploadFile(imagePath);
        }else{
            Toast.makeText(this, "未选择交易品图", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_edit);

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,CommodityModel.class);
            title.setText("修改交易品");
            showCommodity();
        }else{
            title.setText("发布交易品");
        }
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new CommodityPresenter(this);
        presenter.setOnCallBack(new CommodityPresenter.OnCallBack() {
            @Override
            public void add(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(CommodityEditActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void update(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(CommodityEditActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void delete(boolean isSuccess, Object object) {

            }

            @Override
            public void getAll(boolean isSuccess, Object object) {

            }

            @Override
            public void searchAllMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByPlateMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByProvider(boolean isSuccess, Object object) {

            }

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    void showCommodity(){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.commondity_default);
        Glide.with(this).load(BaseAPI.base_url+model.getCommodity_img()).apply(requestOptions).into(commodity_img);

        commodity_name.setText(model.getCommodity_name());
        commodity_price.setText(model.getCommodity_price());
        commodity_plate.setText(model.getPlate_name());
        commodity_old_price.setText(model.getCommodity_old_price());
        commodity_describe.setText(model.getCommodity_describe());
        commodity_produce_time.setText(model.getCommodity_produce_time());
        commodity_validity.setText(model.getCommodity_validity());

        plate_id = model.getPlate_id();
        plate_name = model.getPlate_name();
    }

//    uploadFile(imagePath);

    void uploadFile(String path){
        /**
         * 自定义实体参数类请参考:
         * 请求注解 {@link org.xutils.http.annotation.HttpRequest}
         * 请求注解处理模板接口 {@link org.xutils.http.app.ParamsBuilder}
         *
         * 需要自定义类型作为callback的泛型时, 参考:
         * 响应注解 {@link org.xutils.http.annotation.HttpResponse}
         * 响应注解处理模板接口 {@link org.xutils.http.app.ResponseParser}
         *
         * 示例: 查看 org.xutils.sample.http 包里的代码
         */
        RequestParams params = new RequestParams(BaseAPI.base_upload_url);
        //        params.setSslSocketFactory(...); // 设置ssl
        //        params.addQueryStringParameter("wd", "xUtils");
        params.setMultipart(true);
        params.addBodyParameter("uploadFile", new File(path));
        //        BaiduParams params = new BaiduParams();
        //        params.wd = "xUtils";
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        // params.setMultipart(true);
        // 上传文件方式 1
        // params.uploadFile = new File("/sdcard/test.txt");
        // 上传文件方式 2
        // params.addBodyParameter("uploadFile", new File("/sdcard/test.txt"));
        Callback.Cancelable cancelable
                = x.http().post(params,
                /**
                 * 1. callback的泛型:
                 * callback参数默认支持的泛型类型参见{@link org.xutils.http.loader.LoaderFactory},
                 * 例如: 指定泛型为File则可实现文件下载, 使用params.setSaveFilePath(path)指定文件保存的全路径.
                 * 默认支持断点续传(采用了文件锁和尾端校验续传文件的一致性).
                 * 其他常用类型可以自己在LoaderFactory中注册,
                 * 也可以使用{@link org.xutils.http.annotation.HttpResponse}
                 * 将注解HttpResponse加到自定义返回值类型上, 实现自定义ResponseParser接口来统一转换.
                 * 如果返回值是json形式, 那么利用第三方的json工具将十分容易定义自己的ResponseParser.
                 * 如示例代码{@link org.xutils.sample.http.BaiduResponse}, 可直接使用BaiduResponse作为
                 * callback的泛型.
                 *
                 * 2. callback的组合:
                 * 可以用基类或接口组合个种类的Callback, 见{@link Callback}.
                 * 例如:
                 * a. 组合使用CacheCallback将使请求检测缓存或将结果存入缓存(仅GET请求生效).
                 * b. 组合使用PrepareCallback的prepare方法将为callback提供一次后台执行耗时任务的机会,
                 * 然后将结果给onCache或onSuccess.
                 * c. 组合使用ProgressCallback将提供进度回调.
                 * ...(可参考{@link org.xutils.image.ImageLoader}
                 * 或 示例代码中的 {@link org.xutils.sample.download.DownloadCallback})
                 *
                 * 3. 请求过程拦截或记录日志: 参考 {@link org.xutils.http.app.RequestTracker}
                 *
                 * 4. 请求Header获取: 参考 {@link org.xutils.http.app.RequestInterceptListener}
                 *
                 * 5. 其他(线程池, 超时, 重定向, 重试, 代理等): 参考 {@link RequestParams}
                 *
                 **/
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess",""+result);
                        imageUrl = result;
                        doSubmit();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        setLoadingEnable(false);
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                        setLoadingEnable(false);
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {
//                        setLoadingEnable(false);
                    }
                });
    }

    void doSubmit(){
        if (!AccountTool.isLogin(this)){
            CommonTipsDialog.show(this,"提示","请登录后重试",true);
            finish();
        }
        String commodity_name_text =  commodity_name.getText().toString();
        String commodity_price_text =  commodity_price.getText().toString();
        String commodity_old_price_text =  commodity_old_price.getText().toString();
        String commodity_describe_text =  commodity_describe.getText().toString();
        String commodity_produce_time_text =  commodity_produce_time.getText().toString();
        String commodity_validity_text =  commodity_validity.getText().toString();

        if (StringUtil.isNotEmpty(commodity_name_text)
                && StringUtil.isNotEmpty(commodity_price_text)
                && StringUtil.isNotEmpty(commodity_old_price_text)
                && StringUtil.isNotEmpty(commodity_describe_text)
                && StringUtil.isNotEmpty(commodity_produce_time_text)
                && StringUtil.isNotEmpty(commodity_validity_text)
                && StringUtil.isNotEmpty(plate_id)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("plate_id",plate_id);
            params.put("plate_name",plate_name);
            params.put("commodity_img",imageUrl);
            params.put("commodity_name",commodity_name_text);
            params.put("commodity_price",commodity_price_text);
            params.put("commodity_old_price",commodity_old_price_text);
            params.put("commodity_describe",commodity_describe_text);
            params.put("commodity_produce_time",commodity_produce_time_text);
            params.put("commodity_validity",commodity_validity_text);
            params.put("commodity_provider",AccountTool.getLoginUser(this).getId()+"");
            params.put("commodity_provider_username",AccountTool.getLoginUser(this).getUsername()+"");
            if (model==null){
                presenter.add(params);
            }else{
                params.put("id",model.getId());
                presenter.update(params);
            }

        }else{
            setLoadingEnable(false);
            CommonTipsDialog.show(this,"提示","请完善交易品信息",true);
        }
    }

    void selectCommodityImg(){
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(2,1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(false)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("commodity_plate",resultCode+"");
        if (resultCode == 1200){
            if (StringUtil.isNotEmpty(data.getStringExtra("plate_id")+"")){
                plate_id = data.getStringExtra("plate_id");
                plate_name = data.getStringExtra("plate_name");
                commodity_plate.setText(plate_name);
            }
        }

        switch (requestCode){
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size()>0){
                    imagePath = selectList.get(0).getCutPath();
                    RequestOptions requestOptions = new RequestOptions();
                    Glide.with(this).load(imagePath).apply(requestOptions).into(commodity_img);
                    // setLoadingEnable(true);
                }else {
                    imagePath = "";
                }
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                break;
        }
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }
}
