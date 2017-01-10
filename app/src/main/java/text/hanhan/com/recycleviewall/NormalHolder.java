package text.hanhan.com.recycleviewall;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 韩永光
 * on 2017/1/9 16:48.
 */
public class NormalHolder extends BaseHolder {

    public TextView text1;

    public NormalHolder(View itemView) {
        super(itemView);
        text1 = (TextView) itemView.findViewById(android.R.id.text1);
    }

    @Override
    public void getHolder(Context context, Object o) {

    }


}
