package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.day36.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MoveFragment extends Fragment {
    private  String str;


    public void setText(String str){
        this.str=str;
    }
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view = ((LinearLayout) inflater.inflate(R.layout.fragment_item, null));
        TextView textView=new TextView(getContext());
        textView.setText(str);
        textView.setGravity(Gravity.CENTER);
        view.addView(textView);
        return view;
    }
}
