package samples.linhtruong.com.dagger2sample.home.tabs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import samples.linhtruong.com.dagger2sample.R;
import samples.linhtruong.com.schema.Transaction;

import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/10/17 - 12:13.
 * @organization VED
 */

@EViewGroup(R.layout.transaction_view)
public class TransactionView extends LinearLayout {

    @ViewById(R.id.transaction_list_view)
    protected RecyclerView mList;

    private TransactionAdapter mAdapter;
    private Context mContext;

    public TransactionView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        mContext = context;
    }

    public void init() {
        mAdapter = new TransactionAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(mAdapter);
    }

    public void loadData(List<Transaction> transactions) {
        mAdapter.setData(transactions);
    }
}
