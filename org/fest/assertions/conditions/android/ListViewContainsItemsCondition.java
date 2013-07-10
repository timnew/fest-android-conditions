package org.fest.asstions.conditions.android;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class ListViewContainsItemsCondition extends Condition<View> {
    public static final String MESSAGE_TEMPLATE = "Has %d item(s).";
    private final int numberOfItems;

    public ListViewContainsItemsCondition(int numberOfItems) {
        super(format(MESSAGE_TEMPLATE, numberOfItems));
        this.numberOfItems = numberOfItems;
    }

    @Override
    public boolean matches(View view) {
        ListView listView = (ListView) view;
        int count = getCount(listView);
        return count == numberOfItems;
    }

    private int getCount(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        return adapter == null ? 0 : adapter.getCount();
    }

    public static ListViewContainsItemsCondition numberOfItems(int numberOfItems) {
        return new ListViewContainsItemsCondition(numberOfItems);
    }
}
