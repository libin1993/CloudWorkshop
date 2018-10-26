package cn.cloudworkshop.miaoding.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.cloudworkshop.miaoding.R;


/**
 * Author：Libin on 2018/10/26 10:50
 * Email：1993911441@qq.com
 * Describe：
 */
public class LoadingView extends FrameLayout implements View.OnClickListener {

    private LinearLayout error;
    private LinearLayout loading;
    private State state;
    private OnRetryListener listener;

    public interface OnRetryListener {
        void onRetry();
    }

    public enum State {
        ing, error, done
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public LoadingView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_loading_view, this);
        loading = findViewById(R.id.ll_loading);
        error = findViewById(R.id.ll_load_error);
        setOnClickListener(this);
        setState(State.ing);
    }

    public void setState(State state) {
        this.state = state;
        switch (state) {
            case ing:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case error:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            case done:
                setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null && state == State.error) {
            listener.onRetry();
        }
    }
}

