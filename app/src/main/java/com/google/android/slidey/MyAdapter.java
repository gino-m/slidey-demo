/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.slidey;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
  private String[] dataset;

  // Provide a reference to the views for each data item
  // Complex data items may need more than one view per item, and
  // you provide access to all the views for a data item in a view holder
  public static class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView textView;

    public MyViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  // Provide a suitable constructor (depends on the kind of dataset)
  public MyAdapter(String[] dataset) {
    this.dataset = dataset;
  }

  // Create new views (invoked by the layout manager)
  @Override
  public MyAdapter.MyViewHolder onCreateViewHolder(
    ViewGroup parent,
    int viewType) {
    // create a new view
    TextView v = new TextView(parent.getContext());
    v.setPadding(16, 32, 16, 32);
    MyViewHolder vh = new MyViewHolder(v);
    return vh;
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.textView.setText(dataset[position]);

  }

  // Return the size of your data set (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return dataset.length;
  }
}