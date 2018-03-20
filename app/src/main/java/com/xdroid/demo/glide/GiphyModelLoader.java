package com.xdroid.demo.glide;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

/**
 * A model loader that translates a POJO mirroring a JSON object representing a single image from Giphy's api
 * into an {@link InputStream} that can be decoded into an {@link android.graphics.drawable.Drawable}.
 */
public class GiphyModelLoader extends BaseGlideUrlLoader<Api.GifResult> {

    /**
     * The default factory for {@link com.bumptech.glide.samples.giphy.GiphyModelLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<Api.GifResult, InputStream> {

        @Override
        public ModelLoader<Api.GifResult, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new GiphyModelLoader(context);
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public GiphyModelLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(Api.GifResult model, int width, int height) {
        Api.GifImage fixedHeight = model.images.fixed_height_downsampled;
        int fixedHeightDifference = getDifference(fixedHeight, width, height);
        Api.GifImage fixedWidth = model.images.fixed_width_downsampled;
        int fixedWidthDifference = getDifference(fixedWidth, width, height);
        if (fixedHeightDifference < fixedWidthDifference && !TextUtils.isEmpty(fixedHeight.url)) {
            return fixedHeight.url;
        } else if (!TextUtils.isEmpty(fixedWidth.url)) {
            return fixedWidth.url;
        } else if (!TextUtils.isEmpty(model.images.original.url)) {
            return model.images.original.url;
        } else {
            return null;
        }
    }

    private static int getDifference(Api.GifImage gifImage, int width, int height) {
        return Math.abs(width - gifImage.width) + Math.abs(height - gifImage.height);
    }
}
