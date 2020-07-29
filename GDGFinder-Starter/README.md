# Summary

- Use themes, styles, and attributes on views to change the appearance of views.
- Themes affect the styling of your whole app and come with many preset values for colors, fonts, and font sizes.
- An attribute applies to the view in which the attribute is set. Use attributes if you have styling that applies to only one view, such as padding, margins, and constraints.
- Styles are groups of attributes that can be used by multiple views. For example, you can have a style for all your content headers, buttons, or text views.
- Themes and styles inherit from their parent theme or style. You can create a hierarchy of styles.
- Attribute values (which are set in views) override styles. Styles override the default style. Styles override themes. Themes override any styling set by the `textAppearance` property.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-styles-and-themes/img/e7851427054b568d.png)

**Floating action buttons**

- The floating action button (FAB) floats above all other views. It is usually associated with a primary action the user can take on the screen. You add a click listener to a FAB in the same way as to any other UI element.
- To add a FAB to your layout, use a `CoordinatorLayout` as the top-level view group, then add the FAB to it.
- To scroll content inside a `CoordinatorLayout`, use the `androidx.core.widget.NestedScrollView.`

**Material Design**

- Material Design provides themes and styles that make your app beautiful and easier to use. Material Design provides detailed specs for everything, from how text should be shown, to how to lay out a screen. See [material.io](https://material.io/) for the complete specifications.
- To use Material components, you need to include the Material library in your gradle file.

```
implementation 'com.google.android.material:material:1.1.0-alpha04'
```

- Use `?attr` to apply material theme attributes to a view: `style="?attr/textAppearanceHeadline5"`

**Themes and styles**

- Use styles to override theme attributes.
- Use theme overlays to apply a theme to just a view and its children. Apply the theme to the parent view. For example:

```
android:theme="@style/ThemeOverlay.MaterialComponents.Dark.ActionBar"
```

Then use the `?attr` notation to apply attributes to a view. For example:

```
android:background="?attr/colorPrimaryDark"
```

**Colors**

- The [Color Tool](https://material.io/tools/color) helps you create a Material palette for your app and download the palette as a `color.xml` file.
- Setting a tint on the `ImageView` causes the `ImageView` to be "tinted" to the specified color. For example, you might use `android:tint="?attr/colorOnPrimary"`. The `colorOnPrimary` color is designed to look good on `colorPrimary`.

**Dimensions**

- Use dimension for measurements that apply to your whole app, such as margins, guidelines, or spacing between elements.

**Support RTL Languages**

>**Note:** Some `View` components need further customization to behave properly with RTL. In order to have more precise control over the UI there are 4 different APIs that you can use:
>
> - [`android:layoutDirection`](https://developer.android.com/reference/android/util/LayoutDirection) to set the direction of a component's layout.
> - [`android:textDirection`](https://developer.android.com/reference/android/view/View.html#attr_android:textDirection) to set the direction of a component's text.
> - [`android:textAlignment`](https://developer.android.com/reference/android/view/View.html#attr_android:textAlignment) to set the alignment of a component's text.
> - [`getLayoutDirectionFromLocale()`](https://developer.android.com/reference/android/support/v4/text/TextUtilsCompat#getlayoutdirectionfromlocale) is a method to programatically get the locale that specifies direction.

- In the Android Manifest, set `android:supportsRtl="true"`.
- You can preview RTL in the emulator, and you can use your own language to check screen layouts. On a device or emulator, open **Settings**, and in **Developer Options**, select **Force RTL layout.**
- Replace references to `Left` and `Right` with references to `Start` and `End`.
- Disable mirroring for drawables by deleting `android:autoMirrored="true"`.
- Choose **Refactor > Add RTL support where possible** to let Android Studio do the work for you.
- Use **values-"language code"** folders to store language-specific resources.

**Scan for accessibility**

- In the Play Store, get the [Accessibility Scanner by Google LLC](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor) and run it to scan for screen elements to improve.

**Design for TalkBack with content descriptions**

- Install the[ Android Accessibility Suite](https://play.google.com/store/apps/details?id=com.google.android.marvin.talkback), by Google, which includes TalkBack.
- Add content descriptions to all UI elements. For example:
  `android:contentDescription="@string/stage_image_description"`
- For an editable element such as an `EditText`, use an `android:hint` attribute in the XML to provide a hint to the user about what to type.
- Create content groups by wrapping related elements into a view group.
- Create a live region to give users additional feedback with `android:accessibilityLiveRegion`.

**Use Chips to implement a filter**

- *Chips* are compact elements that represent an attribute, text, entity, or action.
- To create a group of chips, use a `com.google.android.material.chip.ChipGroup`.
- Define the layout for one `com.google.android.material.chip.Chip`.
- If you want your chips to change colors, provide a color state list as a `<selector>` with stateful colors:
  `<item android:color="?attr/colorPrimaryVariant"android:state_selected="true" />`
- Bind the chips to live data by adding an observer to the data in the view model.
- To display the chips, create an inflator for the chip group:
  `LayoutInflater.from(chipGroup.context)`
- Create the chips, add a click listener that triggers the desired action, and add the chips to the chip group.

**Support dark mode**

- Use the `DayNight` `AppTheme` to support dark mode.
- You can set dark mode programmatically:
  `AppCompatDelegate.setDefaultNightMode()`
- Create a **res/values-night** resource folder to provide custom colors and values for dark mode.