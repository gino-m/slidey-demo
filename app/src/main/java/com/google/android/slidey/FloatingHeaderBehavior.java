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

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Custom behavior to manage position and alpha of floating header, and to link movements of
 * BottomSheetBehavior with header.
 */
public class FloatingHeaderBehavior extends CoordinatorLayout.Behavior {
  public FloatingHeaderBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onLayoutChild(
    @NonNull CoordinatorLayout parent, @NonNull View header, int layoutDirection) {
    ViewGroup recyclerView = parent.findViewById(R.id.recycler_view);
    header.setOnTouchListener((v, ev) -> recyclerView.dispatchTouchEvent(ev));
    return false;
  }

  @Override
  public boolean layoutDependsOn(
    @NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
    return dependency.getId() == R.id.bottom_sheet;
  }

  @Override
  public boolean onDependentViewChanged(
    @NonNull CoordinatorLayout parent, @NonNull View header, @NonNull View bottomSheet) {

    float sheetY = bottomSheet.getY();
    float sheetHeight = Math.max(parent.getHeight() - bottomSheet.getY(), 0);

    // Scroll the header together with the bottom sheet. This must be done programmatically
    // because CoordinatorLayout anchors cause bottom sheet height to expand, preventing header
    // from disappearing behind toolbar.
    header.setTranslationY(sheetY - header.getHeight());

    // Fade out header and bottom sheet starting when bottom sheet is the same height as the
    // header to when it is completely hidden. This prevents the header from sticking out above
    // the bottom of the screen when the bottom sheet is in hidden state.
    float headerHeight = header.getHeight();
    float alpha = Math.min(sheetHeight / headerHeight, 1.0f);
    header.setAlpha(alpha);
    bottomSheet.setAlpha(alpha);

    // Show/hide the header based on whether the bottom sheet is visible.
    header.setVisibility(sheetHeight == 0 ? View.GONE : View.VISIBLE);

    return true;
  }
}
