/*
 * Copyright 2020 Google LLC
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

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {
  private static final String[] DATASET;

  static {
    DATASET = new String[200];
    for (int i = 0; i < 25; i++) {
      DATASET[i] = "Item " + (i + 1);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    // Setting this with android:background has no effect, so we do it here programmatically.
    CardView header = findViewById(R.id.header);
    header.setBackgroundResource(R.drawable.bg_header_card);

    // Set up scrolling list.
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new MyAdapter(DATASET));

    // Set up bottom sheet trigger.
    Button button = findViewById(R.id.button);
    button.setOnClickListener((v) -> showBottomSheet());
  }

  private void showBottomSheet() {
    BottomSheetBehavior<RecyclerView> bottomSheetBehavior =
      BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
  }
}
