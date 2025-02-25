# Pytlin

A implementation of some python features for kotlin.

# Import

### Maven

Add `pytlin` to your `<dependencies>` tag in POM file:

(pom.xml):

```xml

<dependency>
    <groupId>io.github.psunset</groupId>
    <artifactId>pytlin</artifactId>
    <version>${pytlin.version}</version>
</dependency>
```

Then set `pytlin.version` in your `<properties>` tag in the same file:

(pom.xml):

```xml

<properties>
    <pytlin.version>x.y.z</pytlin.version>
</properties>
```

Please replace the `x.y.z` with an available version by checking
out [releases](https://github.com/pSUNSET/Pytlin/releases).

Eventually, install the library:

```
mvn install
```

### Gradle

Add `pytlin` to your `dependencies` in the build file:

Groovy (build.gradle):

```groovy
dependencies {
    implementation "io.github.psunset:pytlin:$pytlin_version"
}
```

Kotlin (build.gradle.kts):

```kotlin
dependencies {
    implementation("io.github.psunset:pytlin:$pytlin_version")
}
```

Then set `pytlin_version` in your properties file:

(gradle.properties):

```properties
pytlin_version=x.y.z
```

Please replace the `x.y.z` with an available version by checking
out [releases](https://github.com/pSUNSET/Pytlin/releases).

Eventually, build your project:

```
./gradlew build
```

### JAR File

Directly download the pytlin-x.y.z.jar file
in [releases](https://github.com/pSUNSET/Pytlin/releases).
Then import it into your module.

# Features

All the kotlin examples below are tested in a kotlin script (`.kts`) file.
So there's no `main` function in the code.
If you want to directly use the example code in a kotlin (`.kt`) file,
please remember to build a main function as an entry.

### Kwargs

When you define a function in python, you may use `*args` and `**kwargs` as parameters.  
For example:

```python
from functools import reduce
import operator


def f(*args, **kwargs) -> int:
    method = kwargs.pop('method', 'add')
    match(method):
        case 'add': result = sum(args)
        case 'mul': result = reduce(operator.mul, args, 1)
        case _: result = 2147483647 # Invalid method
    return result

sum_result = f(*range(1, 10), 15, 20, method='add')
print(sum_result) # Result: 80
prod_result = f(*range(1, 5), method='mul')
print(prod_result) # Result: 24
```

Now you can achieve the same purpose in kotlin with the following code.

```kotlin
import net.psunset.pytlin.collections.Kwargs
import net.psunset.pytlin.collections.kwargsOf
import net.psunset.pytlin.collections.prod

fun f(vararg args: Int, kwargs: Kwargs): Int {
    val method = kwargs["method", "add"]
    // "method" is key, "add" is defaultValue
    // Only get the value here, not pop the element.
    // Because the key-value pairs in Kwargs are immutable.
    return when (method) {
        "add" -> args.sum()
        "mul" -> args.prod() // A feature in this library
        else -> Int.MAX_VALUE // Invalid method
    }
}

val sum_result = f(
    *(1..<10).toList().toTypedArray().toIntArray(), 15, 20,
    kwargs = kwargsOf(
        "method" to "add"
    )
)
println(sum_result) // Result: 80

val prod_result = f(
    *(1..<5).toList().toTypedArray().toIntArray(),
    kwargs = kwargsOf(
        "method" to "mul"
    )
)
println(prod_result) // Result: 24
```

### MutableKwargs

Sometimes, we would like to use a kwargs-like map or dict 
which is a more convenient map or dict with string keys and values.
But the elements in `Kwargs` are all immutable,
we need `MutableKwargs` instead.

For example:

```kotlin
import net.psunset.pytlin.collections.mutableKwargsOf

val goodToPrice = mutableKwargsOf("apple" to 3, "orange" to 2, "banana" to 3)
val priceOfApple = goodToPrice / ("apple" to 0)
// "apple" is key, `0` is defaultValue
// using `/` operator to pop an element
val priceOfOrange = goodToPrice / ("orange" to 0)
println("Total price is ${priceOfApple + priceOfOrange}") // Result: Total price is 5.5
println(goodToPrice) // Result: {banana=3}
// That stands for that apple and orange has already got removed.
```

### Repeat all elements in a list

When we want to create a list by repeating another list,
we simply make a list multiplied by an integer.

For example:

```python
l = [1] * 10
print(l) # Result: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
ll = [1, 2] * 5
print(ll) # Result: [1, 2, 1, 2, 1, 2, 1, 2, 1, 2]
```

Now we can simply implement that in kotlin with the following code.

```kotlin
import net.psunset.pytlin.collections.times

val l = listOf(1) * 10
println(l) // Result: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
val ll = listOf(1, 2) * 5
println(ll) // Result: [1, 2, 1, 2, 1, 2, 1, 2, 1, 2]
```

### Turn all objects into boolean

In python, you can directly put a non-boolean variable after `if`.
Often, it implies if itself isn't empty, non-zero, etc.

For example:

```python
a = []
b = ''
c = 0
if a: print('a is True!')
if b: print('b is True!')
if c: print('c is True!')
# There is no output because a, b and c all stands for false.
```

Now, let's convert it into kotlin.

```kotlin
import net.psunset.pytlin.lang.not
import net.psunset.pytlin.lang.toBool

val a = listOf<Any>()
val b = ""
val c = 0
if (a.toBool()) println("a is true!")
if (b.toBool()) println("b is true!")
if (!!c) println("c is true!") // Also, `obj.toBool()` can be replaced to `!!obj`
// There's no output, either.
```

By the same token, `if not obj: ...` in python is equivalent to `if (!obj) { ... }`.

### Value equation of numbers

`==` operator in python only checks the value of two numbers, regardless of their type.

For example:

```python
print(1.0 == 1) # Result: True
```

But after converting the prociding code into kotlin.

```kotlin
println(1.0 == 1) // Error: Operator '==' cannot be applied to 'Double' and 'Int'
```

Uh..., there is an error with the preceding code.
Let's transform that into a `BigDecimal`.

```kotlin
import java.math.BigDecimal

println(BigDecimal.valueOf(1.0) == BigDecimal.valueOf(1)) // Result: false
```

As you see, the result is `false` because the `==` operator in kotlin
not only compares the value of the numbers but also checks whether they are in same type.

But the function named `valEq` can deal with two problems we met.
Let's replace `==` operators with `valEq` infix functions.

```kotlin
import net.psunset.pytlin.lang.valEq
import java.math.BigDecimal

println(1.0 valEq 1) // Result: true
println(BigDecimal.valueOf(1.0) valEq BigDecimal.valueOf(1)) // Result: true
```

It seems that the problems are solved.

### Tensors

This feature is unavailable in raw python.
That is enabled only when you install [numpy](https://github.com/numpy/numpy) or [pytorch](https://github.com/pytorch/pytorch) library.

A tensor can storage a multi-dimension array.
And the features of that are matrix multiplication, dot product and cross product of vectors, operation for all elements, etc.

But, in fact, the tensors in this library are incomplete and deprecated.
So if you want a more useful library for more features and better performance.
Please check out [mulkit](https://github.com/Kotlin/multik), which is a kotlin official library.

For more details, please check out the document in
[Tensors](./src/main/kotlin/net/psunset/pytlin/collections/Tensors.kt) source.

### Slices

In python, slices of a list can be simply implemented by:

```python
a = [1,2,3,4,5,6,7,8,9,10]
print(a[:5]) # Result: [1, 2, 3, 4, 5]
print(a[:]) # Result: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
print(a[::-1]) # Result: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
```

Now you can do the same thing in kotlin by making the slices in python surrounded by `"` to make it a string.
For example:

```kotlin
import net.psunset.pytlin.collections.get

val a = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
println(a[":5"]) // Result: [1, 2, 3, 4, 5]
println(a[":"]) // Result: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
println(a["::-1"]) // Result: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
```

# Using in Java

deprecated
