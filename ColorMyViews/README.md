# Summary

## ConstraintLayout

A [`ConstraintLayout`](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) is a [`ViewGroup`](http://developer.android.com/reference/android/view/ViewGroup.html) that allows you to position and size child views in a flexible way. A constraint layout allows you to create large, complex layouts with flat view hierarchies (no nested view groups). To build a constraint layout, you can use the Layout Editor to add constraints, and to drag-and-drop views. You don't need to edit the XML.

**Note:** `ConstraintLayout` is available as a support library, which is available in API level 9 and higher.

## Constraints

A [constraint](https://developer.android.com/training/constraint-layout/#constraints-overview) is a connection or alignment between two UI elements. Each constraint connects or aligns one view to another view, to the parent layout, or to an invisible guideline. In a constraint layout, you position a view by defining at least one horizontal and one vertical constraint.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/be3c775dcb40cfe.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/ec6ab16929700158.png)Horizontal constraint: B is constrained to stay to the right of A. (In a finished app, B would need at least one vertical constraint in addition to this horizontal constraint.)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-constraint-layout/img/b4e089773fc08bd9.png) Vertical constraint: C is constrained to stay below A. (In a finished app, C would need at least one horizontal constraint in addition to this vertical constraint.)