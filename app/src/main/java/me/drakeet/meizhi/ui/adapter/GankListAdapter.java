/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.drakeet.meizhi.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.model.Gank;
import me.drakeet.meizhi.ui.WebActivity;
import me.drakeet.meizhi.util.StringStyleUtils;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankListAdapter extends AnimRecyclerViewAdapter<GankListAdapter.ViewHolder> {

    private List<Gank> mGankList;


    public GankListAdapter(List<Gank> gankList) {
        mGankList = gankList;
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(v);
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Gank gank = mGankList.get(position);
        if (position == 0) {
            showCategory(holder);
        }
        else {
            boolean doesLastAndThis =
                    mGankList.get(position - 1).type.equals(mGankList.get(position).type);
            if (!doesLastAndThis) {
                showCategory(holder);
            }
            else if (holder.category.isShown()) holder.category.setVisibility(View.GONE);
        }
        holder.category.setText(gank.type);
        if (holder.gank.getTag() == null) {
            SpannableStringBuilder builder = new SpannableStringBuilder(gank.desc).append(
                    StringStyleUtils.format(holder.gank.getContext(), " (via. " + gank.who + ")",
                            R.style.ViaTextAppearance));
            CharSequence gankText = builder.subSequence(0, builder.length());
            holder.gank.setTag(gankText);
        }
        CharSequence text = (CharSequence) holder.gank.getTag();
        holder.gank.setText(text);
        showItemAnim(holder.gank, position);
    }


    @Override public int getItemCount() {
        return mGankList.size();
    }


    private void showCategory(ViewHolder holder) {
        if (!holder.category.isShown()) holder.category.setVisibility(View.VISIBLE);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_category) TextView category;
        @Bind(R.id.tv_title) TextView gank;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.ll_gank_parent) void onGank(View v) {
            Gank gank = mGankList.get(getLayoutPosition());
            Intent intent = WebActivity.newIntent(v.getContext(), gank.url, gank.desc);
            v.getContext().startActivity(intent);
        }
    }
}
