# Summary

## ConstraintLayout

A [`ConstraintLayout`](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) is a [`ViewGroup`](http://developer.android.com/reference/android/view/ViewGroup.html) that allows you to position and size child views in a flexible way. A constraint layout allows you to create large, complex layouts with flat view hierarchies (no nested view groups). To build a constraint layout, you can use the Layout Editor to add constraints, and to drag-and-drop views. You don't need to edit the XML.

**Note:** `ConstraintLayout` is available as a support library, which is available in API level 9 and higher.

## Constraints

A [constraint](https://developer.android.com/training/constraint-layout/#constraints-overview) is a connection or alignment between two UI elements. Each constraint connects or aligns one view to another view, to the parent layout, or to an invisible guideline. In a constraint layout, you position a view by defining at least one horizontal and one vertical constraint.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/be3c775dcb40cfe.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/ec6ab16929700158.png)Horizontal constraint: B is constrained to stay to the right of A. (In a finished app, B would need at least one vertical constraint in addition to this horizontal constraint.)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/b4e089773fc08bd9.png) Vertical constraint: C is constrained to stay below A. (In a finished app, C would need at least one horizontal constraint in addition to this vertical constraint.)

## Chains

A [chain](https://developer.android.com/training/constraint-layout/#constrain-chain) is a group of views that are linked to each other with bidirectional constraints. The views within a chain can be distributed either vertically or horizontally. For example, the following diagram shows two views that are constrained to each other, which creates a horizontal chain.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/690fa1708662a4a9.png)

### Head of the chain

The first view in a chain is called the *head* of the chain. The attributes that are set on the head of the chain control, position, and distribute all the views in the chain. For horizontal chains, the head is the left-most view. For vertical chains, the head is the top-most view. In each of the two diagrams below, "A" is the head of the chain.

| ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/e822baed8a72bf3a.png) | ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/559c8ae4c80cc90f.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |

### Chain styles

Chain styles define the way the chained views are spread out and aligned. You style a chain by assigning a chain style attribute, adding weight, or setting bias on the views.

There are three chains styles:

- **Spread:** This is the default style. Views are evenly spread in the available space, after margins are accounted for.

  ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/d57e8cdbe225181f.png)

- **Spread inside:** The first and the last views are attached to the parent on each end of the chain. The rest of the views are evenly spread in the available space.

  ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/8ee14c6b5164afef.png)

- **Packed:** The views are packed together, after margins are accounted for. You can then adjust the position of the whole chain by changing the bias of the chain's head view.

  ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/16bb057b065865c6.png) Packed chain

  ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/c893437f3a9c3f06.png) Packed chain with bias

- **Weighted:** The views are resized to fill up all the space, based on the values set in the [`layout_constraintHorizontal_weight`](https://developer.android.com/reference/android/support/constraint/ConstraintLayout) or `layout_constraintVertical_weight` attributes. For example, imagine a chain containing three views, A, B, and C. View A uses a weight of 1. Views B and C each use a weight of 2. The space occupied by views B and C is twice that of view A, as shown below.

  ![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/91ca5b204a0141ed.png)

## Baseline constraint

The baseline constraint aligns the baseline of a view's text with the baseline of another view's text. Aligning views that contain text can be a challenge, especially if the fonts are differently sized. Baseline constraint does the alignment work for you.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/7eca5c45d4b85a9.png)