package com.applligent.flick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardStackLayoutManager cardStackLayoutManager;
    private StackViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        cardStackLayoutManager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d("TAG", "onCardDragging: d="+ direction.name()+ " ratio = "+ ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {

                Log.d("TAG", "onCardSwiped: p="+ cardStackLayoutManager.getTopPosition() + "d = "+direction);
                if (direction == Direction.Right)
                {
                    Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left)
                {
                    Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top)
                {
                    Toast.makeText(MainActivity.this, "Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom)
                {
                    Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
                }

                //Pagination
                if(cardStackLayoutManager.getTopPosition() == adapter.getItemCount() - 5)
                {
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });

        cardStackLayoutManager.setStackFrom(StackFrom.None);
        cardStackLayoutManager.setVisibleCount(3);
        cardStackLayoutManager.setTranslationInterval(8.0f);
        cardStackLayoutManager.setScaleInterval(0.93f);
        cardStackLayoutManager.setSwipeThreshold(0.3f);
        cardStackLayoutManager.setMaxDegree(20.0f);
        cardStackLayoutManager.setDirections(Direction.FREEDOM);
        cardStackLayoutManager.setCanScrollHorizontal(true);
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Manual);
        cardStackLayoutManager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new StackViewAdapter(addList());

        cardStackView.setLayoutManager(cardStackLayoutManager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }

    private void paginate() {

        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallBack callBack = new CardStackCallBack(old,baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callBack);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);

    }

    private List<ItemModel> addList(){
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.a1,"Bill"));
        items.add(new ItemModel(R.drawable.a2, "edddy"));
        items.add(new ItemModel(R.drawable.a3, "Willams"));
        items.add(new ItemModel(R.drawable.a4, "HArry"));
        items.add(new ItemModel(R.drawable.a6, "Barry"));
        items.add(new ItemModel(R.drawable.a7, "Mark"));
        items.add(new ItemModel(R.drawable.a8,"Musk"));

        return items;
    }

}