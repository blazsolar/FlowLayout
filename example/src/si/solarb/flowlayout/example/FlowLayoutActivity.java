/*
 * Copyright 2013 Blaž Šolar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package si.solarb.flowlayout.example;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import si.solarb.flowlayout.FlowLayout;

public class FlowLayoutActivity extends ListActivity {

	ExamplesAdapter mAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		mAdapter = new ExamplesAdapter(this);
		setListAdapter(mAdapter);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, mAdapter.getItem(position).activityClass);
		startActivity(intent);
	}

	private class ExamplesAdapter extends ArrayAdapter {

		private Item[] mItems = {
				new Item<BasicActivity>(R.string.activity_basic, BasicActivity.class),
				new Item<AllInOneActivity>(R.string.activity_all_in_one, AllInOneActivity.class)
		};

		private LayoutInflater mInflater;

		private ExamplesAdapter(Context context) {
			super(context, 0);

			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mItems.length;
		}

		@Override
		public Item getItem(int position) {
			return mItems[position];
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				v = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			}

			TextView text = (TextView) v.findViewById(android.R.id.text1);
			text.setText(getItem(position).name);

			return v;
		}

		private class Item<T extends Activity> {

			private int name;
			private Class<T> activityClass;

			public Item(int name, Class<T> activityClass) {
				super();
				this.name = name;
				this.activityClass = activityClass;
			}

		}

	}

}
