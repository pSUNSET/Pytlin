# KWArgs for Kotlin

A library for implementing the `kwargs` features of python!

# QuickStart:

### Maven

Add `kwargs-for-kotlin` to your dependencies tag in POM file:

(pom.xml):

```xml

<dependency>
    <groupId>io.github.psunset</groupId>
    <artifactId>kwargs-for-kotlin</artifactId>
    <version>${kwargs_for_kotlin.version}</version>
</dependency>
```

Then setting `kwargs_for_kotlin.version` in your properties tag in the same file:

(pom.xml):

```xml

<properties>
    <kwargs_for_kotlin.version>x.y.z</kwargs_for_kotlin.version>
</properties>
```

Please replace the `x.y.z` to an available version by checking
out [releases](https://github.com/pSUNSET/KWArgsForKotlin/releases).

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
out [releases](https://github.com/pSUNSET/KWArgsForKotlin/releases).

Eventually, build your project:

```
./gradlew build
```

### JAR File

Directly download the kwargs-for-kotlin-x.y.z.jar file
in [releases](https://github.com/pSUNSET/KWArgsForKotlin/releases).
Then import it into your module.

# Features

### Keyword Argument in Python

When you want to define a function in python, you can define `*args` and `**kwargs`.  
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
fun f(vararg args: Int, kwargs: KWArgs): Int {
    val method = kwargs / ("method" to "add")
    // "method" is key; "add" is defaultValue
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

# In Java

Are you looking for the same one but in java? Check out [here](https://github.com/pSUNSET/KWArgsForJava).  
But, honestly, kotlin one has more features than java one.  
So please use this one as you can.