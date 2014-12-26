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

package com.wefika.flowlayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.ViewGroup;

import junit.framework.Assert;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Blaz Solar on 01/04/14.
 */
public class FlowLayoutTest extends ActivityUnitTestCase<FlowLayoutStubActivity> {

    private FlowLayout mLayout;

    public FlowLayoutTest() {
        super(FlowLayoutStubActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGenerateDefaultLayoutParams() throws Exception {

        init();

        FlowLayout.LayoutParams params = mLayout.generateDefaultLayoutParams();
        Assert.assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, params.width);
        Assert.assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, params.height);

    }

    public void testGenerateLayoutParams() throws Exception {

        init();

        ViewGroup.LayoutParams params = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        FlowLayout.LayoutParams generated = mLayout.generateLayoutParams(params);

        assertEquals(-1, generated.gravity);
        assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, generated.width);
        assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, generated.height);

    }

    public void testGravity() throws Exception {

        init();

        mLayout.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

        assertEquals(Gravity.BOTTOM | Gravity.RIGHT, mLayout.getGravity());

    }

    public void testGravityDefault() throws Exception {

        init();

        mLayout.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

        mLayout.setGravity(0);

        int horizontal = isIcs() ? Gravity.START : Gravity.LEFT;

        assertEquals(horizontal | Gravity.TOP, mLayout.getGravity());

    }

    private void init() {

        Intent intent = new Intent(getInstrumentation().getTargetContext(), FlowLayoutStubActivity.class);
        startActivity(intent, null, null);

        mLayout = (FlowLayout) getActivity().findViewById(com.wefika.flowlayout.test.R.id.layout);

    }

    private static boolean isIcs() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
}
