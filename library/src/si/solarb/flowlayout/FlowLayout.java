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

			LayoutParams lp = (LayoutParams) child.getLayoutParams();

			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			int childHeight = child.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;

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

		lineHeights.add(lineHeight);
		lines.add(lineViews);

		int numLines = lineHeights.size();

		int left;
		int top = 0;

		for(int i = 0; i < numLines; i++) {

			lineHeight = lineHeights.get(i);
			lineViews = lines.get(i);
			left = 0;

			int children = lineViews.size();

			for(int j = 0; j < children; j++) {

				View child = lineViews.get(j);

				if(child.getVisibility() == View.GONE) {
					continue;
				}

				LayoutParams lp = (LayoutParams) child.getLayoutParams();

				// if height is match_parent we need to remeasure child to line height
				if(lp.height == LayoutParams.MATCH_PARENT) {
					int childWidthMode = MeasureSpec.AT_MOST;
					int childWidthSize = lineWidth;

					if(lp.width == LayoutParams.MATCH_PARENT) {
						childWidthMode = MeasureSpec.EXACTLY;
					} else if(lp.width >= 0) {
						childWidthMode = MeasureSpec.EXACTLY;
						childWidthSize = lp.width;
					}

					child.measure(
							MeasureSpec.makeMeasureSpec(childWidthSize, childWidthMode),
							MeasureSpec.makeMeasureSpec(lineHeight - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY)
					);
				}

				int childWidth = child.getMeasuredWidth();
				int childHeight = child.getMeasuredHeight();

				child.layout(left + lp.leftMargin,
						top + lp.topMargin,
						left + childWidth + lp.leftMargin,
						top + childHeight + lp.topMargin);

				left += childWidth + lp.leftMargin + lp.rightMargin;

			}

			top += lineHeight;
		}

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

			LayoutParams lp = (LayoutParams) child.getLayoutParams();

			int childWidthMode = MeasureSpec.AT_MOST;
			int childWidthSize = sizeWidth;

			int childHeightMode = MeasureSpec.AT_MOST;
			int childHeightSize = sizeHeight;

			if(lp.width == LayoutParams.MATCH_PARENT) {
				childWidthMode = MeasureSpec.EXACTLY;
				childWidthSize -= lp.leftMargin + lp.rightMargin;
			} else if(lp.width >= 0) {
				childWidthMode = MeasureSpec.EXACTLY;
				childWidthSize = lp.width;
			}

			if(lp.height >= 0) {
				childHeightMode = MeasureSpec.EXACTLY;
				childHeightSize = lp.height;
			}

			child.measure(
					MeasureSpec.makeMeasureSpec(childWidthSize, childWidthMode),
					MeasureSpec.makeMeasureSpec(childHeightSize, childHeightMode)
			);

			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

			if(lineWidth + childWidth > sizeWidth) {

				width = Math.max(width, lineWidth);
				lineWidth = 0;

				height += lineHeight;
				lineHeight = 0;

			} else {
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
			}

		}

		setMeasuredDimension(
				(modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
				(modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
	}

	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p);
	}

	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(), attrs);
	}

	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	public class LayoutParams extends MarginLayoutParams {

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
