FlowLayout
==========

FlowLayout is an opensource Android library that alows developers to easily integrate flow layout into their app. FlowLayout is an layout that display its children in multiple rows depending on their size.

Dependencies
------------
### Gradle
```
compile "com.wefika:flowlayout:0.3.0-SNAPSHOT"
```

### Maven
```xml
<dependency>
    <groupId>com.wefika</groupId>
    <artifactId>flowlayout</artifactId>
    <version>0.3.0</version>
</dependency>
```

Usage
-----
```xml
<si.solarb.flowlayout.FlowLayout xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:flowlayout="http://schemas.android.com/apk/res/si.solarb.flowlayout"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:gravity="start|top">

	<View
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:layout_margin="0dp"
			android:layout_gravity="start|top" />

</si.solarb.flowlayout.FlowLayout>
```

Example
-------
![Framed example screenshot](images/framed_example_screenshot.png)
Source code with examples is included in repository.

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
