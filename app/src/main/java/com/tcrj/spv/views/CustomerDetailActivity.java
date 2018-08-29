package com.tcrj.spv.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.CustomerDetailCallBack;
import com.tcrj.spv.model.CommonalityEntity;
import com.tcrj.spv.model.CustomerDetailEntity;
import com.tcrj.spv.model.ParameterEntity;
import com.tcrj.spv.model.PhoneListEntity;
import com.tcrj.spv.model.PublicListEntity;
import com.tcrj.spv.model.SubmitEntity;
import com.tcrj.spv.model.UserInfoEntity;
import com.tcrj.spv.presenter.CustomerDetailPresenter;
import com.tcrj.spv.views.adapter.FragmentAdapter;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.DialogAddressList;
import com.tcrj.spv.views.dialog.DialogTracedList;
import com.tcrj.spv.views.dialog.MessageDialogBuilder;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;
import com.tcrj.spv.views.utils.PermissionUtils;
import com.tcrj.spv.views.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户详情
 */
public class CustomerDetailActivity extends BaseActivity implements View.OnClickListener, CustomerDetailCallBack.View {
    private ImageButton imgCustomerBack;
    private TextView tvCustomerTitle;
    private LinearLayout layoutCustomerMore;
    private ImageButton imgCustomerMore;
    private LinearLayout layoutBar;
    private TabLayout tabList;
    private ViewPager viewPager;
    private List<Fragment> fragments = null;
    private CustomerDetailCallBack.Presenter presenter;
    private TextView tvCustomerUser;
    private TextView tvCustomerStatus;
    private String cusId = "";
    private LinearLayout layoutBackground;
    private LinearLayout layoutDetailsTelephone;
    private ImageView imgDetailsTelephone;
    private LinearLayout layoutDetailsMessage;
    private ImageView imgDetailsMessage;
    private LinearLayout layoutDetailsLocation;
    private ImageView imgDetailsLocation;
    private LinearLayout layoutDetailsPerson;
    private ImageView imgDetailsPerson;
    private LinearLayout layoutDetailsRecord;
    private ImageView imgDetailsRecord;
    private CustomerDetailFragment fragment1;
    private ContactsListFragment fragment2;
    private FollowRecordFragment fragment3;
    private CommonalityEntity commonality;
    private PopupWindow mPopTop;
    private TextView tvPopuCustomer;
    private TextView tvPopuReport;
    private TextView tvPopuShare;
    private TextView tvPopuMove;
    private TextView tvPopuSeas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetail);
        cusId = getIntent().getStringExtra("CustomerID");
        new CustomerDetailPresenter(this, 0);
        setCommonality();
        ImmerSive();
        initView();
        setPopuWindow();
        presenter.start();
    }

    private void setCommonality() {
        commonality = new CommonalityEntity();
        commonality.setLxrInfoall(null);
        commonality.setLxrInfosj(null);
        commonality.setXCode("");
        commonality.setYCode("");
        commonality.setLxrCount("");
        commonality.setInHighSeas("0");
        commonality.setCustomerName("");
        commonality.setPhoneNumber("");
        commonality.setEntityDetails(null);
    }

    /**
     * 沉浸式
     */
    private void ImmerSive() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            layoutBar = (LinearLayout) findViewById(R.id.ll_bar);
            layoutBar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight = Utils.getStatusBarHeight(this);
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutBar.getLayoutParams();
            params.height = statusHeight;
            layoutBar.setLayoutParams(params);
        }
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        tvCustomerUser = (TextView) findViewById(R.id.tv_customer_details_user);
        tvCustomerStatus = (TextView) findViewById(R.id.tv_customer_details_status);
        imgCustomerBack = (ImageButton) findViewById(R.id.img_customer_back);
        tvCustomerTitle = (TextView) findViewById(R.id.tv_customer_title);
        layoutCustomerMore = (LinearLayout) findViewById(R.id.layout_customer_more);
        layoutBackground = (LinearLayout) findViewById(R.id.layout_customer_background);
        imgCustomerMore = (ImageButton) findViewById(R.id.img_customer_more);
        tabList = (TabLayout) findViewById(R.id.tabs_customer_details);
        viewPager = (ViewPager) findViewById(R.id.pager_customer_details);
        layoutDetailsTelephone = (LinearLayout) findViewById(R.id.layout_details_telephone);
        imgDetailsTelephone = (ImageView) findViewById(R.id.img_details_telephone);
        layoutDetailsMessage = (LinearLayout) findViewById(R.id.layout_details_message);
        imgDetailsMessage = (ImageView) findViewById(R.id.img_details_message);
        layoutDetailsLocation = (LinearLayout) findViewById(R.id.layout_details_location);
        imgDetailsLocation = (ImageView) findViewById(R.id.img_details_location);
        layoutDetailsPerson = (LinearLayout) findViewById(R.id.layout_details_person);
        imgDetailsPerson = (ImageView) findViewById(R.id.img_details_person);
        layoutDetailsRecord = (LinearLayout) findViewById(R.id.layout_details_record);
        imgDetailsRecord = (ImageView) findViewById(R.id.img_details_record);
        imgDetailsPerson.setBackgroundResource(R.mipmap.img_address_select);
        tvCustomerTitle.setText("客户信息");
        layoutBackground.getBackground().setAlpha(150);
        imgCustomerBack.setOnClickListener(this);
        imgCustomerMore.setOnClickListener(this);
        layoutDetailsTelephone.setOnClickListener(this);
        layoutDetailsMessage.setOnClickListener(this);
        layoutDetailsLocation.setOnClickListener(this);
        layoutDetailsPerson.setOnClickListener(this);
        layoutDetailsRecord.setOnClickListener(this);
        loadFragment();
    }

    /**
     * 加载及刷新Fragment
     */
    private void loadFragment() {
        List<String> titles = new ArrayList<>();
        titles.add("客户详情");
        titles.add("联系人");
        titles.add("跟进记录");
        for (int i = 0; i < titles.size(); i++) {
            tabList.addTab(tabList.newTab().setText(titles.get(i)));
        }
        fragments = new ArrayList<>();
        fragment1 = new CustomerDetailFragment(cusId);
        fragment2 = new ContactsListFragment(cusId);
        fragment3 = new FollowRecordFragment(cusId);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        viewPager.setOffscreenPageLimit(3);
        tabList.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabList.setTabsFromPagerAdapter(adapter);
//      viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                indexNum = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    private int indexNum;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.e("TAG","客户信息回调函数执行");
        presenter.start();
        switch (requestCode) {
            case 101:
                Log.e("TAG","客户信息回调函数执行101");
                    fragment1.refresh();
                break;

            case 102:
                Log.e("TAG","客户信息回调函数执行102");
                fragment2.loadData();
                break;
            case 103:
                Log.e("TAG","客户信息回调函数执行103");
                fragment3.refresh();
//                if(indexNum > 0){
//                    fragment3.refresh();
//                }

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_customer_back:
                finish();
                break;
            case R.id.img_customer_more:
                mPopTop.showAsDropDown(imgCustomerMore);
                break;
            case R.id.layout_details_telephone:
                if (Utils.isStringEmpty(commonality.getLxrInfoall())) {
                    displayToast("暂无通讯号码");
                    return;
                } else {
                    List<PhoneListEntity> itemList = new ArrayList<>();
                    for (int i = 0; i < commonality.getLxrInfoall().size(); i++) {
                        PhoneListEntity entity = new PhoneListEntity();
                        entity.setPhoneName(commonality.getLxrInfoall().get(i).getLxrName());
                        entity.setPhoneCord(commonality.getLxrInfoall().get(i).getLxrNum());
                        itemList.add(entity);
                    }
                    DialogAddressList phone = DialogAddressList.getInstance(this, itemList);
                    phone.setDuration(700);
                    phone.setEffect(Effectstype.Slidetop);
                    phone.setOnItemClickListener(new DialogAddressList.IPhoneListCallBack() {
                        @Override
                        public void setOnClickListener(String number) {
                            commonality.setPhoneNumber(number);
                            callPhone(number);
                        }
                    });
                    phone.show();
                }
                break;
            case R.id.layout_details_message:
                if (Utils.isStringEmpty(commonality.getLxrInfosj())) {
                    displayToast("暂无联系手机");
                    return;
                } else {
                    List<PhoneListEntity> itemList = new ArrayList<>();
                    for (int i = 0; i < commonality.getLxrInfosj().size(); i++) {
                        PhoneListEntity entity = new PhoneListEntity();
                        entity.setPhoneName(commonality.getLxrInfosj().get(i).getLxrName());
                        entity.setPhoneCord(commonality.getLxrInfosj().get(i).getLxrphone());
                        itemList.add(entity);
                    }
                    DialogAddressList phone = DialogAddressList.getInstance(this, itemList);
                    phone.setDuration(700);
                    phone.setEffect(Effectstype.Slidetop);
                    phone.setOnItemClickListener(new DialogAddressList.IPhoneListCallBack() {
                        @Override
                        public void setOnClickListener(String number) {
                            commonality.setPhoneNumber(number);
                            sendSMS(number);
//                            callPhone(number);
                        }
                    });
                    phone.show();
                }
                break;
            case R.id.layout_details_location:
                if (Utils.isStringEmpty(commonality.getXCode())) {
                    displayToast("暂无位置信息");
                    return;
                } else {

                }
                break;
            case R.id.layout_details_person:
                Intent intent = new Intent(CustomerDetailActivity.this, ContactInputActivity.class);
                intent.putExtra("CustomerId", cusId);
                intent.putExtra("CustomerName", commonality.getCustomerName());
                startActivityForResult(intent,102);
                break;
            case R.id.layout_details_record:

                //TODO:没有联系人要去录入联系人信息才可以进行跟进的录入
                if (Utils.isStringEmpty(commonality.getLxrCount()) || commonality.getLxrCount().equals("0")) {

                    showLXRDialog();

                } else {
                    Intent record = new Intent(CustomerDetailActivity.this, FollowAddActivity.class);
                    record.putExtra("CustomerId", cusId);
                    record.putExtra("CustomerName", commonality.getCustomerName());
                    startActivityForResult(record,103);
                }
                break;
        }
    }



    private void showLXRDialog() {
        final MessageDialogBuilder message = MessageDialogBuilder.getInstance(this);
        message.setDuration(700);
        message.setEffect(Effectstype.Slidetop);
        message.setTitles("还没有录入联系人信息呢！");
        message.setContents("是否录入联系人信息");
        message.setCancel("否，谢谢");
        message.setSubmit("录入联系人");
        message.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
            @Override
            public void setSubmitListener() {
                message.dismiss();
                Intent intent = new Intent(CustomerDetailActivity.this, ContactInputActivity.class);
                intent.putExtra("CustomerId", cusId);
                intent.putExtra("CustomerName", commonality.getCustomerName());
                startActivityForResult(intent,102);
            }

            @Override
            public void setCancelListener() {
                message.dismiss();

            }
        });
        message.show();
    }

    @Override
    public void setPresenter(CustomerDetailCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }

    @Override
    public void getData(CustomerDetailEntity entity) {
        if (entity != null) {
            commonality.setEntityDetails(entity.getEntity());
            commonality.setYCode(entity.getEntity().getYCode());
            commonality.setXCode(entity.getEntity().getXCode());
            commonality.setLxrCount(entity.getEntity().getLxrCount());
            commonality.setCustomerName(entity.getEntity().getCusName());
            if (!Utils.isStringEmpty(entity.getEntity().getCusName())) {
                tvCustomerUser.setText(entity.getEntity().getCusName());
                tvCustomerStatus.setText(entity.getEntity().getCusStatus());
            }
            if (!Utils.isStringEmpty(entity.getEntity().getLxrInfoAll())) {
                commonality.setLxrInfoall(entity.getEntity().getLxrInfoAll());
                if (entity.getEntity().getLxrInfoAll().size() == 0) {
                    imgDetailsTelephone.setBackgroundResource(R.mipmap.img_linkway_normal);
                } else {
                    imgDetailsTelephone.setBackgroundResource(R.mipmap.img_linkway_select);
                }
            } else {
                imgDetailsTelephone.setBackgroundResource(R.mipmap.img_linkway_normal);
            }
            if (!Utils.isStringEmpty(entity.getEntity().getLxrInfosj())) {
                commonality.setLxrInfosj(entity.getEntity().getLxrInfosj());
                if (entity.getEntity().getLxrInfosj().size() == 0) {
                    imgDetailsMessage.setBackgroundResource(R.mipmap.img_message_normal);
                } else {
                    imgDetailsMessage.setBackgroundResource(R.mipmap.img_message_select);
                }
            } else {
                imgDetailsMessage.setBackgroundResource(R.mipmap.img_message_normal);
            }
            if (Utils.isStringEmpty(entity.getEntity().getXCode())) {
                imgDetailsLocation.setBackgroundResource(R.mipmap.img_location_normal);
            } else {
                imgDetailsLocation.setBackgroundResource(R.mipmap.img_location_select);
            }
            if (Utils.isStringEmpty(entity.getEntity().getLxrCount()) || entity.getEntity().getLxrCount().equals("0")) {
                imgDetailsRecord.setBackgroundResource(R.mipmap.img_remark_normal);
            } else {
                imgDetailsRecord.setBackgroundResource(R.mipmap.img_remark_select);
            }
        }
    }

    /**
     * 投入公海
     *
     * @param entity
     */
    @Override
    public void highSeas(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
            }
        }
    }

    /**
     * 客户报备
     *
     * @param entity
     */
    @Override
    public void report(SubmitEntity entity) {
        if (entity != null) {
            if (entity.getState() == 0) {
                displayToast(entity.getMsg());
            } else {
                displayToast(entity.getMsg());
            }
        }
    }

    /**
     * 参数
     *
     * @return
     */
    @Override
    public String cusId() {
        return cusId;
    }

    @Override
    public ParameterEntity getParameter() {
        UserInfoEntity user = BaseApplication.getUserInfoEntity();
        ParameterEntity entity = new ParameterEntity();
        entity.setInHighSeasReason(commonality.getInHighSeas());
        entity.setInHighSeasUserID(user.getEntity().getUserId());
        entity.setIds(cusId);
        entity.setCustomerId(cusId);
        return entity;
    }

    /**
     * 打电话权限
     */
    private void callPhone(String number) {
        //判断当前系统是否高于或等于6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //当前系统大于等于6.0
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                //具有权限直接用
                getPhoneNumber(number);
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PermissionUtils.REQUEST_CALL_PHONE);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + commonality.getPhoneNumber()));
            startActivity(intent);
        }
    }

    //发短信
    private void sendSMS(String smsBody) {

        Uri smsToUri = Uri.parse("smsto:"+smsBody);

        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

//        intent.putExtra("sms_body", smsBody);

        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.REQUEST_CALL_PHONE) {
            if (grantResults.length > 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + commonality.getPhoneNumber()));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } else {
                    displayToast("缺少权限，请您打开电话权限");
                    return;
                }
            }
        }
    }

    /**
     * 获取电话号码
     */
    private void getPhoneNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    /**
     * 弹出框样式设置
     */
    private void setPopuWindow() {
        mPopTop = new PopupWindow(this);
        int w = Utils.getWidth(this);
        mPopTop.setWidth(w / 2);
        mPopTop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopTop.setFocusable(true);//TODO 获取焦点
        mPopTop.setTouchable(true);
        mPopTop.setOutsideTouchable(true);//TODO 设置popupwindow外部可点击
        ColorDrawable dw = new ColorDrawable(0000000000);// TODO 实例化一个ColorDrawable颜色为半透明
        mPopTop.setBackgroundDrawable(dw);// TODO 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mPopTop.setAnimationStyle(R.style.mystyle);//TODO 设置显示和消失动画
        View conentView = Utils.getLayoutInflater(this).inflate(R.layout.item_popu_customer, null);
        setConentViewClickListener(conentView);
        mPopTop.setContentView(conentView);
    }

    /**
     * 事件
     *
     * @param v
     */
    private void setConentViewClickListener(View v) {
        tvPopuCustomer = (TextView) v.findViewById(R.id.tv_popu_customer);
        tvPopuReport = (TextView) v.findViewById(R.id.tv_popu_report);
        tvPopuShare = (TextView) v.findViewById(R.id.tv_popu_share);
        tvPopuMove = (TextView) v.findViewById(R.id.tv_popu_move);
        tvPopuSeas = (TextView) v.findViewById(R.id.tv_popu_seas);
        tvPopuCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                final MessageDialogBuilder msg = MessageDialogBuilder.getInstance(CustomerDetailActivity.this);
                msg.setTitles("编辑客户");
                msg.setContents("确定要编辑客户信息吗？");
                msg.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        msg.dismiss();
                        Intent intent = new Intent(CustomerDetailActivity.this, CustomerInputActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("entity", commonality.getEntityDetails());
                        intent.putExtras(bundle);
                        startActivityForResult(intent,101);
                    }

                    @Override
                    public void setCancelListener() {
                        msg.dismiss();
                    }
                });
                msg.show();
            }
        });
        tvPopuReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                final MessageDialogBuilder msg = MessageDialogBuilder.getInstance(CustomerDetailActivity.this);
                msg.setTitles("客户报备");
                msg.setContents("确定要客户报备吗？");
                msg.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        new CustomerDetailPresenter(CustomerDetailActivity.this, 2);
                        presenter.start();
                        msg.dismiss();
                    }

                    @Override
                    public void setCancelListener() {
                        msg.dismiss();
                    }
                });
                msg.show();
            }
        });
        tvPopuShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                Intent share = new Intent(CustomerDetailActivity.this, RelationManActivity.class);
                share.putExtra("CusID", cusId);
                share.putExtra("Type", 1);
                startActivity(share);
            }
        });
        tvPopuMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                Intent move = new Intent(CustomerDetailActivity.this, RelationManActivity.class);
                move.putExtra("CusID", cusId);
                move.putExtra("Type", 0);
                startActivity(move);
            }
        });
        tvPopuSeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopTop.dismiss();
                final MessageDialogBuilder msg = MessageDialogBuilder.getInstance(CustomerDetailActivity.this);
                msg.setDuration(700);
                msg.setEffect(Effectstype.Slidetop);
                msg.setTitles("友情提示");
                msg.setContents("确定此客户投入公海吗？");
                msg.setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        msg.dismiss();
                        final DialogTracedList dialog = new DialogTracedList(CustomerDetailActivity.this, getInitSea());
                        dialog.setDuration(700);
                        dialog.setEffect(Effectstype.Slidetop);
                        dialog.setTitleName("请选择客户分类");
                        dialog.setOnItemClickListener(new DialogTracedList.IListViewCallBack() {
                            @Override
                            public void setOnClickListener(PublicListEntity entity) {
                                commonality.setInHighSeas(String.valueOf(entity.getId()));
                                new CustomerDetailPresenter(CustomerDetailActivity.this, 1);
                                presenter.start();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }

                    @Override
                    public void setCancelListener() {
                        msg.dismiss();
                    }
                });
                msg.show();
            }
        });
    }

    /**
     * 公海数据
     *
     * @return
     */
    private List<PublicListEntity> getInitSea() {
        List<PublicListEntity> dataList = new ArrayList<>();
        CharSequence[] array = this.getResources().getStringArray(R.array.input_seas);
        for (int i = 0; i < array.length; i++) {
            PublicListEntity entity = new PublicListEntity();
            entity.setId(i + 1);
            entity.setpName(array[i].toString());
            dataList.add(entity);
        }
        return dataList;
    }
}
