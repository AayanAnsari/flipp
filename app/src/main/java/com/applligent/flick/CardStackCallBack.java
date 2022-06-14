package com.applligent.flick;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CardStackCallBack extends DiffUtil.Callback {

    private List<ItemModel> old, baru;

    public CardStackCallBack(List<ItemModel> old, List<ItemModel> baru) {
        this.old = old;
        this.baru = baru;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return baru.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getImageId() == baru.get(newItemPosition).getImageId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == baru.get(newItemPosition);
    }
}
