package com.gy.amobile.hslibrary.router;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import com.gy.amobile.hslibrary.BaseApplication;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 路由操作
 */
public class Router {

    private final static String TAG = Router.class.getSimpleName();
    private static Router mInstance;
    private IRouterUri mRouterUri;
    private Context mContext;

    /**
     * 获取单例引用
     */
    public static Router getInstance(Context context) {
        Router inst = mInstance;
        if (inst == null) {
            synchronized (Router.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new Router(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 构造函数
     *
     * @param mContext application
     */
    private Router(Context mContext) {
        this.mContext = mContext;
        mRouterUri = create(IRouterUri.class);
    }

    /**
     * 返回Api
     */
    public IRouterUri routerUri() {
        return mRouterUri;
    }

    public IRouterUri create(Class<?> aClass) {
        return (IRouterUri) Proxy.newProxyInstance(aClass.getClassLoader(), new Class<?>[]{aClass},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
                        StringBuilder stringBuilder = new StringBuilder();
                        RouterUri reqUrl = method.getAnnotation(RouterUri.class);
                        Log.e(TAG, "IReqApi---reqUrl->" + reqUrl.routerUri());
                        stringBuilder.append(reqUrl.routerUri());
                        //Type[] parameterTypes = method.getGenericParameterTypes();//获取注解参数类型
                        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();//拿到参数注解
                        //Annotation[] annotation = method.getDeclaredAnnotations();
                        int pos = 0;
                        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                            Annotation[] annotations = parameterAnnotationsArray[i];
                            if (annotations != null && annotations.length != 0) {
                                if (pos == 0) {
                                    stringBuilder.append("?");
                                } else {
                                    stringBuilder.append("&");
                                }
                                pos++;
                                RouterParam reqParam = (RouterParam) annotations[0];
                                stringBuilder.append(reqParam.value());
                                stringBuilder.append("=");
                                stringBuilder.append(args[i]);
                                Log.e(TAG, "reqParam---reqParam->" + reqParam.value() + "=" + args[i]);
                                if ("urlKey".equals(reqParam.value())) {
                                    if (!args[i].equals(BaseApplication.urlKey)) {
                                        return null;
                                    }
                                }
                            }
                        }
                        //下面就可以执行相应的跳转操作
                        openRouterUri(stringBuilder.toString());
                        return null;
                    }
                });
    }

    /**
     * 通过uri跳转指定页面
     */
    private void openRouterUri(String url) {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        if (isValid) {
            mContext.startActivity(intent);
        }
    }

}
