Summary
==============================

## **REST web services**

- A *web service* is a service on the internet that enables your app to make requests and get data back.
- Common web services use a [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) architecture. Web services that offer REST architecture are known as *RESTful* services. RESTful web services are built using standard web components and protocols.
- You make a request to a REST web service in a standardized way, via URIs.
- To use a web service, an app must establish a network connection and communicate with the service. Then the app must receive and parse response data into a format the app can use.
- The [Retrofit](https://square.github.io/retrofit/) library is a client library that enables your app to make requests to a REST web service.
- Use converters to tell Retrofit what do with data it sends to the web service and gets back from the web service. For example, the `ScalarsConverter` converter treats the web service data as a `String` or other primitive.
- To enable your app to make connections to the internet, add the `"android.permission.INTERNET"` permission in the Android manifest.

## **JSON parsing**

- The response from a web service is often formatted in [JSON](https://www.json.org/), a common interchange format for representing structured data.
- A JSON object is a collection of key-value pairs. This collection is sometimes called a *dictionary*, a *hash map*, or an *associative array*.
- A collection of JSON objects is a JSON array. You get a JSON array as a response from a web service.
- The keys in a key-value pair are surrounded by quotes. The values can be numbers or strings. Strings are also surrounded by quotes.
- The [Moshi](https://github.com/square/moshi) library is Android JSON parser that converts a JSON string into Kotlin objects. Retrofit has a converter that works with Moshi.
- Moshi matches the keys in a JSON response with properties in a data object that have the same name.
- To use a different property name for a key, annotate that property with the `@Json` annotation and the JSON key name.

## **Retrofit and coroutines**

- Call adapters let Retrofit create APIs that return something other than the default `Call` class. Use the `CoroutineCallAdapterFactory` class to replace the `Call` with a coroutine `Deferred`.
- Use the `await()` method on the `Deferred` object to cause your coroutine code to wait without blocking until the value is ready, and then the value is returned.

## Showing images using Glide

- To simplify the process of managing images, use the [Glide library](https://github.com/bumptech/glide) to download, buffer, decode, and cache images in your app.
- Glide needs two things to load an image from the internet: the URL of an image, and an `ImageView` object to put the image in. To specify these options, use the `load()` and `into()` methods with Glide.
- [Binding adapters](https://developer.android.com/topic/libraries/data-binding/binding-adapters) are extension methods that sit between a view and that view's bound data. Binding adapters provide custom behavior when the data changes, for example, to call Glide to load an image from a URL into an `ImageView`.
- Binding adapters are extension methods annotated with the `@BindingAdapter` annotation.
- To add options to the Glide request, use the `apply()` method. For example, use `apply()` with `placeholder()` to specify a loading drawable, and use `apply()` with `error()` to specify an error drawable.
- To produce a grid of images, use a `RecyclerView` with a `GridLayoutManager`.
- To update the list of properties when it changes, use a binding adapter between the `RecyclerView` and the layout.

## **Binding expressions**

- Use [binding expressions](https://developer.android.com/topic/libraries/data-binding/expressions) in XML layout files to perform simple programmatic operations, such as math or conditional tests, on bound data.
- To reference classes inside your layout file, use the `<import>` tag inside the `<data>` tag.

## **Web service query options**

- Requests to web services can include optional parameters.
- To specify query parameters in the request, use the `@Query` annotation in [Retrofit](https://square.github.io/retrofit/).