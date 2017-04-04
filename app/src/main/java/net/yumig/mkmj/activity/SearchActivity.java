package net.yumig.mkmj.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.currency.library.controller.BaseActivity;
import com.currency.library.utils.ToastUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import net.yumig.mkmj.R;
import net.yumig.mkmj.adapter.HisWordAdapter;
import net.yumig.mkmj.adapter.HotWordsAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton mIbBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.rv_hotwords)
    RecyclerView mRvHotwords;

    String[] mWords = new String[]{"零食", "衣服", "洗衣机", "三只松鼠", "糖", "雨伞", "运动鞋", "手机"};

    @BindView(R.id.rv_lishiWords)
    RecyclerView mRvLishiWord;
    private HotWordsAdapter mHotWordsAdapter;
    private HisWordAdapter mLishiAdapter;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        initData();
        initView();
        initListener();
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        initData();

    }

    private void initData() {
        mLishiAdapter.notifyDataSetChanged();
        mHotWordsAdapter.notifyDataSetChanged();
    }

    private void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 4);
        mRvHotwords.setLayoutManager(gridLayoutManager);
        mRvHotwords.setHasFixedSize(true);
        mHotWordsAdapter = new HotWordsAdapter(SearchActivity.this, R.layout.rvlist_item_hotwords, Arrays.asList(mWords));
        mRvHotwords.setAdapter(mHotWordsAdapter);

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(SearchActivity.this, 4);
        mRvLishiWord.setLayoutManager(gridLayoutManager2);
        mRvLishiWord.setHasFixedSize(true);
        mLishiAdapter = new HisWordAdapter(SearchActivity.this, R.layout.rvlist_item_hotwords, Arrays.asList(mWords));
        mRvLishiWord.setAdapter(mLishiAdapter);


    }

    private void initListener() {
        mHotWordsAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //                ToastUtils.showToastShort(SearchActivity.this, "is " + position);
                String s = Arrays.asList(mWords).get(position);
                //                showToast("is " + position, 0);
                ToastUtils.showToastShort(mContext, "Click is " + s);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    ToastUtils.showToastShort(mContext, "search");
                }
                return false;
            }
        });
    }


    @OnClick({R.id.ib_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.tv_search:
                ToastUtils.showToastShort(mContext, "search");
                //                ToastUtils.showToastShort(SearchActivity.this, "search");
                break;
        }
    }


}
