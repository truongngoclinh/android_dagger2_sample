package samples.linhtruong.com.dagger2sample.home.tabs;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.devspark.robototextview.widget.RobotoTextView;
import samples.linhtruong.com.base.BaseAdapter;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.schema.Transaction;

import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/10/17 - 12:29.
 * @organization VED
 */
public class TransactionAdapter extends BaseAdapter<Transaction, TransactionAdapter.TransactionHolder> {

    private static final int TYPE_CONTENT = 0X01;

    @Override
    public int getItemViewType(int position) {
        return TYPE_CONTENT;
    }

    @Override
    public TransactionAdapter.TransactionHolder createHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CONTENT:
                return TransactionHolder.create(parent.getContext());

            default:
                return null;
        }
    }

    static class TransactionHolder extends BaseAdapter.ViewHolder<Transaction> {

        private RobotoTextView mTxtAmount;
        private RobotoTextView mTxtBranch;

        private TransactionHolder(View itemView) {
            super(itemView);

            mTxtAmount = (RobotoTextView) itemView.findViewById(R.id.amount);
            mTxtBranch = (RobotoTextView) itemView.findViewById(R.id.branch);
        }

        public static TransactionHolder create(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.transaction_view_item, null);
            return new TransactionHolder(view);
        }

        @Override
        public void bindData(Transaction data) {
            itemView.setTag(data);

            mTxtAmount.setText(String.valueOf(data.amount));
            mTxtBranch.setTag(data.branch);
        }
    }
}
