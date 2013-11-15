package com.cs2114.vttransit;

import com.cs2114.vttransit.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * To write.
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class RoutesFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_routes, container,
				false);

		// now you must initialize your list view
		ListView listview = (ListView) rootView.findViewById(R.id.list);

		// EDITED Code
		String[] items = new String[] { "Item 1", "Item 2", "Item 3" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_view, items);

		listview.setAdapter(adapter);

		return rootView;
	}

}
