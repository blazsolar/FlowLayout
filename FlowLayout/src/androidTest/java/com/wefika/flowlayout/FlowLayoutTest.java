/*
 * Copyright 2014 Blaž Šolar
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

import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * Created by Blaž Šolar on 01/04/14.
 */
public class FlowLayoutTest extends AndroidTestCase {

    private FlowLayout mLayout;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mLayout = new FlowLayout(getContext());
    }

    public void testGenerateDefaultLayoutParams() throws Exception {

        FlowLayout.LayoutParams params = (FlowLayout.LayoutParams) mLayout.generateDefaultLayoutParams();
        Assert.assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, params.width);
        Assert.assertEquals(FlowLayout.LayoutParams.MATCH_PARENT, params.height);

    }
}
