# Kwargs for Kotlin

A implementation of some python features for kotlin.

# Import:

### Maven

Add `kwargs-for-kotlin` to your `<dependencies>` tag in POM file:

(pom.xml):

```xml

<dependency>
    <groupId>io.github.psunset</groupId>
    <artifactId>kwargs-for-kotlin</artifactId>
    <version>${kwargs_for_kotlin.version}</version>
</dependency>
```

Then setting `kwargs_for_kotlin.version` in your `<properties>` tag in the same file:

(pom.xml):

```xml

<properties>
    <kwargs_for_kotlin.version>x.y.z</kwargs_for_kotlin.version>
</properties>
```

Please replace the `x.y.z` to an available version by checking
out [releases](https://github.com/pSUNSET/KwargsForKotlin/releases).

Eventually, install the library:

```
mvn install
```

### Gradle

Add `kwargs-for-kotlin` to your dependencies in build file:

Groovy (build.gradle):

```groovy
dependencies {
    implementation "io.github.psunset:kwargs-for-kotlin:$kwargs_for_kotlin_version"
}
```

Kotlin (build.gradle.kts):

```kotlin
dependencies {
    implementation("io.github.psunset:kwargs-for-kotlin:$kwargs_for_kotlin_version")
}
```

Then setting `kwargs_for_kotlin_version` in your properties file:

(gradle.properties):

```properties
kwargs_for_kotlin_version=x.y.z
```

Please replace the `x.y.z` to an available version by checking
out [releases](https://github.com/pSUNSET/KwargsForKotlin/releases).

Eventually, build your project:

```
./gradlew build
```

### JAR File

Directly download the kwargs-for-kotlin-x.y.z.jar file
in [releases](https://github.com/pSUNSET/KwargsForKotlin/releases).
Then import it into your module.

# Features

### Kwargs

When you want to define a function in python, you can use `*args` and `**kwargs` as parameters.  
For example, in python:

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

Now you do the same thing in kotlin by:

```kotlin
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
fun main() {
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
}
```

### MutableKwargs

Sometimes, we would like to use a kwargs-like map or dict without a parameter.
What we want to do is just using a more convenient map or dict with string keys and values.
But the element in Kwargs is immutable, we can't use it smoothly.
Now, we need `MutableKwargs`.

At the example of `Kwargs`, we can't pop the element in kwargs.
But we can do that with `MutableKwargs`.
Let's modify that function:

```kotlin
// Set type of arg 'kwargs' to `MutableKwargs`
fun f(vararg args: Int, kwargs: MutableKwargs): Int {
    val method = kwargs / ("method" to "add")
    // "method" is key, "add" is defaultValue
    // Now, the key-value pair whose key is called "method" is removed.
    return when (method) {
        "add" -> args.sum()
        "mul" -> args.prod()
        else -> Int.MAX_VALUE
    }
}
```

The removing isn't necessary here.
I only want to show you guys where you can use `MutableKwargs` instead of `Kwargs`.

### Array with duplicated elements

When we want to create a list with so many duplicated elements.
We simply make a list multiply by an integer.

For example:

```python
l = [1] * 10
print(l) # Result: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
```

Now we can simply implement that in kotlin by:

```kotlin
fun main() {
    val l = listOf(1) * 10
    println(l) // Result: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
}
```

### Turn all objects into boolean

In python, you can directly put a non-boolean value after `if`.
Often, it stands for if self is not empty, non-zero, etc.

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

Now, let's convert it into kotlin:

```kotlin
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

`==` operator in python only checking the value of two numbers, regardless of their type.

For example:

```python
print(1.0 == 1) # Result: True
```

But the same code in kotlin:

```kotlin
println(1.0 == 1) // Error: Operator '==' cannot be applied to 'Double' and 'Int'
```

Uh..., it got something wrong.
Let's make it a `BigDecimal`.

```kotlin
import java.math.BigDecimal

println(BigDecimal.valueOf(1.0) == BigDecimal.valueOf(1)) // Result: false
```

As you see, the result is `false` because the `==` operator in kotlin
not only compares the value of the numbers but also checks out the type of them.

But the function named `valEq` can deal with two problems we met.
Let's replace `==` operator to `valEq` keyword:

```kotlin
println(1.0 valEq 1) // Result: true
println(BigDecimal.valueOf(1.0) valEq BigDecimal.valueOf(1)) // Result: true
```

It seems that this function works fine.

# Using in Java

Are you looking for the same one but in java? Check out [here](https://github.com/pSUNSET/KwargsForJava).  
But, honestly, kotlin one has more features than java one.  
So please use this one as you can.