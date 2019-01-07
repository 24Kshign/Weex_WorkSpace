package com.weex.app.photo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weex.app.R;
import com.weex.app.recyclerview.BaseListAdapter;
import com.weex.app.recyclerview.BaseViewHolder;
import com.weex.app.util.ImageLoader;

import java.io.File;



/**
 * 选择相册adapter
 */
public class PhotoAlbumAdapter extends BaseListAdapter<PhotoAlbumLVItem> {

    Context context;

    public PhotoAlbumAdapter(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_album_name, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PhotoAlbumLVItem item = mList.get(position);
        //图片（缩略图）
        String filePath = item.getFirstImagePath();
        holder.firstImageIV.setTag(filePath);
        ImageLoader.with(context,filePath,holder.imageView);

        //文字
        holder.pathNameTV.setText(getPathNameToShow(item));
        holder.numTV.setText(String.valueOf(item.getFileCount()));
        return convertView;
    }

    static class ViewHolder extends BaseViewHolder {
        ImageView firstImageIV;
        ImageView imageView;
        TextView pathNameTV;
        TextView numTV;

        public ViewHolder(View view){
            super(view);
            firstImageIV = (ImageView) $(R.id.select_img_gridView_img);
            imageView = (ImageView) $(R.id.firstImage);
            pathNameTV= (TextView) $(R.id.select_img_gridView_path);
            numTV = (TextView) $(R.id.select_img_gridView_num);
        }
    }

    private String getPathNameToShow(PhotoAlbumLVItem item) {
        if (item.getPathName().equals(PhotoWallActivity.PATH_ALL)) {
            return "全部";
        }
        String absolutePath = item.getPathName();
        int lastSeparator = absolutePath.lastIndexOf(File.separator);
        return absolutePath.substring(lastSeparator + 1);
    }

}
