package com.example.wenbiaozheng.dataapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class DataModule {
    private int mMaxSize = 5000; //最大容量。默认是5000个
    private LruCache mDataCache;
    private String mModuleTag = ""; //模块的标记，为对应数据的ClassName

    public DataModule(String tag) {
        this.mModuleTag = tag;
    }

    public void setMaxSize(int maxSize) {
        this.mMaxSize = maxSize;
        mDataCache = new LruCache(mMaxSize);
    }

    public int getMaxSize() {
        return mMaxSize;
    }

    public Object get(String key) {
        check();
        return mDataCache.get(key);
    }

    public List<Object> get(List<String> keyList) {
        check();
        List<Object> dataList = new ArrayList<>();
        for (String key : keyList) {
            dataList.add(mDataCache.get(key));
        }

        return dataList;
    }

    public List<Object> getAll() {
        check();
        return mDataCache.getAll();
    }

    public void clear() {
        mDataCache.clear();
    }

    public void save(String key, Object data) {
        if (TextUtils.isEmpty(key)) {
            throw new RuntimeException("Key not null");
        }

        if (!mModuleTag.equals(key)) {
            throw new RuntimeException("key not right");
        }
        check();
        mDataCache.put(key, data);
    }

    private void check() {
        if (mDataCache == null) {
            mDataCache = new LruCache(mMaxSize);
        }
    }

    public void get(String key, IModuleCallback callback) {
        Object data = get(key);
        if (data != null && callback != null) {
            callback.onSuccess(data);
        }

        getDbData(callback);
    }

    private void getDbData(IModuleCallback callback) {

    }
}
