package org.qii.weiciyuan.ui.timeline;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.qii.weiciyuan.R;
import org.qii.weiciyuan.bean.TimeLineMsgList;
import org.qii.weiciyuan.bean.WeiboMsg;

/**
 * Created with IntelliJ IDEA.
 * User: qii
 * Date: 12-7-29
 * Time: 下午12:14
 * To change this template use File | Settings | File Templates.
 */
public abstract class TimeLineAbstractFragment extends Fragment {
    protected ListView listView;
    protected TimeLineMsgList list = new TimeLineMsgList();
    protected TimeLineAdapter timeLineAdapter;
    protected String token;

    public void setToken(String token) {
        this.token = token;
    }

    public void refresh() {

    }

    ;

    protected class TimeLineAdapter extends BaseAdapter {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        @Override
        public int getCount() {

            if (list.getStatuses() != null) {

                return list.getStatuses().size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return list.getStatuses().get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.fragment_listview_item_layout, parent, false);
                holder.screenName = (TextView) convertView.findViewById(R.id.username);
                holder.txt = (TextView) convertView.findViewById(R.id.content);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.pic = (ImageView) convertView.findViewById(R.id.pic);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            WeiboMsg msg = list.getStatuses().get(position);

            holder.screenName.setText(msg.getUser().getScreen_name());

            holder.txt.setText(msg.getText());

            holder.time.setText(msg.getCreated_at());

            holder.pic.setImageDrawable(getResources().getDrawable(R.drawable.app));

            return convertView;

        }
    }

    static class ViewHolder {
        TextView screenName;
        TextView txt;
        TextView time;
        ImageView pic;
    }
}
