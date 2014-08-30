/*
 * Copyright 2014 Blaz Solar
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

package com.wefika.flowlayout.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wefika.flowlayout.FlowLayout;

/**
 * Created by Blaz Solar on 05/02/14.
 */
public class VisibilityActivity extends Activity {

    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visibility);

        mFlowLayout = (FlowLayout) findViewById(R.id.flow);
    }

    public void addItem(View view) {

        int color = getResources().getColor(R.color.holo_blue_dark);

        View newView = new View(this);
        newView.setBackgroundColor(color);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(100, 100);
        params.rightMargin = 10;
        newView.setLayoutParams(params);

        mFlowLayout.addView(newView);
    }

    public void removeItem(View view) {

        mFlowLayout.removeView(getLastView());

    }

    public void toggleItem(View view) {

        View last = getLastView();

        if(last.getVisibility() == View.VISIBLE) {
            last.setVisibility(View.GONE);
        } else {
            last.setVisibility(View.VISIBLE);
        }

    }

    private View getLastView() {
        return mFlowLayout.getChildAt(mFlowLayout.getChildCount() - 1);
    }

}
