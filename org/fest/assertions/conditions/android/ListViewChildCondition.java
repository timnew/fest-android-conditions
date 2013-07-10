package org.fest.asstions.conditions.android;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.fest.assertions.core.Condition;

import static java.lang.String.format;

public class ListViewChildCondition extends Condition<View> {
    public static final String MESSAGE_TEMPLATE = "Has item with %s";
    private final Condition<View> condition;

    public ListViewChildCondition(Condition<View> condition) {
        super(format(MESSAGE_TEMPLATE, condition.description()));
        this.condition = condition;
    }

    @Override
    public boolean matches(View view) {
        ListView listView = (ListView) view;
        ListAdapter adapter = listView.getAdapter();

        if (adapter == null)
            return false;

        for (int i = 0; i < adapter.getCount(); i++) {
            View itemView = adapter.getView(i, null, listView);
            if (condition.matches(itemView))
                return true;
        }

        return false;
    }

    public static ListViewChildCondition childWith(Condition<View> itemCondition) {
        return new ListViewChildCondition(itemCondition);
    }
}
