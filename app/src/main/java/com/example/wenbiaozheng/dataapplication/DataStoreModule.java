package com.example.wenbiaozheng.dataapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class DataStoreModule {
    private ArrayMap<String, DataModule> mModuleMap = new ArrayMap<>();

    public void setModule(Class moduleClazz, DataModule module) {
        mModuleMap.put(moduleClazz.getName(), module);
    }

    public DataModule getModule(Class moduleClazz) {
        if (mModuleMap.containsKey(moduleClazz)) {
            return mModuleMap.get(moduleClazz.getName());
        } else {
            DataModule dataModule = new DataModule(moduleClazz.getName());
            mModuleMap.put(moduleClazz.getName(), dataModule);
            return dataModule;
        }
    }

    public void clearModule(Class moduleClazz) {
        if (mModuleMap.containsKey(moduleClazz)) {
            DataModule dataModule = mModuleMap.get(moduleClazz.getName());
            dataModule.clear();
            mModuleMap.remove(moduleClazz.getName());
        }
    }
}
