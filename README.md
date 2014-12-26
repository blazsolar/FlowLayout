FlowLayout
==========

FlowLayout is an opensource Android library that alows developers to easily integrate flow layout into their app. FlowLayout is an layout that display its children in multiple rows depending on their size.

[![Build Status](https://api.travis-ci.org/blazsolar/FlowLayout.svg?branch=develop)](https://travis-ci.org/blazsolar/FlowLayout)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.wefika/flowlayout/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.wefika/flowlayout)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlowLayout-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1216)

Example
-------
![Framed example screenshot](https://raw.githubusercontent.com/blazsolar/FlowLayout/develop/images/framed_example_screenshot.png)
Source code with examples is included in repository.

Dependencies
------------
### Gradle
```
compile "com.wefika:flowlayout:<version>"
```

### Maven
```xml
<dependency>
    <groupId>com.wefika</groupId>
    <artifactId>flowlayout</artifactId>
    <version>[version]</version>
</dependency>
```

Usage
-----
```xml
<com.wefika.flowlayout.FlowLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="start|top">

	<View
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Lorem ipsum" />

</com.wefika.flowlayout.FlowLayout>
```

License
-------
	Copyright 2013 Blaž Šolar
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
