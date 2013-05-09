package si.solarb.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Blaž Šolar
 * Date: 5/6/13
 * Time: 8:17 PM
 */
public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FlowLayout, defStyle, 0);

		a.recycle();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		List<List<View>> lines = new ArrayList<List<View>>();
		List<Integer> lineHeights = new ArrayList<Integer>();

		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;
		List<View> lineViews = new ArrayList<View>();

		Log.v(VIEW_LOG_TAG, "count: " + getChildCount());

		for(int i = 0; i < getChildCount(); i++) {

			View child = getChildAt(i);

			if(child.getVisibility() == View.GONE) {
				continue;
			}

			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			if(lineWidth + childWidth > width) {
				lineHeights.add(lineHeight);
				lines.add(lineViews);

				lineHeight = 0;
				lineWidth = 0;
				lineViews = new ArrayList<View>();
			}

			lineWidth += childWidth;
			lineHeight = Math.max(lineHeight, childHeight);
			lineViews.add(child);
		}

		if(lineHeight > 0) {
			lineHeights.add(lineHeight);
			lines.add(lineViews);
		}

		int numLines = lineHeights.size();

		int left;
		int top = 0;

		for(int i = 0; i < numLines; i++) {

			lineViews = lines.get(i);
			left = 0;

			int children = lineViews.size();

			for(int j = 0; j < children; j++) {

				View child = lineViews.get(j);

				if(child.getVisibility() == View.GONE) {
					continue;
				}

				int childWidth = child.getMeasuredWidth();
				int childHeight = child.getMeasuredHeight();

				child.layout(left, top, left + childWidth, top + childHeight);

				Log.v(VIEW_LOG_TAG, left + " " + top);

				left += childWidth;

			}
			Log.v(VIEW_LOG_TAG, "line");
			top += lineHeights.get(i);
		}

		Log.v(VIEW_LOG_TAG, "done");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(widthMeasureSpec);

		int width = 0;
		int height = 0;

		int lineWidth = 0;
		int lineHeight = 0;

		for(int i = 0; i < getChildCount(); i++) {

			View child = getChildAt(i);

			if(child.getVisibility() == View.GONE) {
				continue;
			}

			child.measure(
					MeasureSpec.makeMeasureSpec(sizeWidth, (modeWidth == MeasureSpec.EXACTLY) ? MeasureSpec.AT_MOST : modeWidth),
					MeasureSpec.makeMeasureSpec(sizeHeight, (modeHeight == MeasureSpec.EXACTLY) ? MeasureSpec.AT_MOST : modeHeight)
			);

			int childWidth = child.getMeasuredWidth();

			if(lineWidth + childWidth > sizeWidth) {

				width = Math.max(width, lineWidth);
				lineWidth = 0;

				height += lineHeight;
				lineHeight = 0;

			} else {
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, child.getMeasuredHeight());
			}

		}

		setMeasuredDimension(
				(modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
				(modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
	}

	public class LayoutParams extends ViewGroup.LayoutParams {

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public LayoutParams(int width, int height) {
			super(width, height);
		}

		public LayoutParams(ViewGroup.LayoutParams source) {
			super(source);
		}

	}

}
