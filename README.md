# KWArgs for Kotlin

A library for implementing the `kwargs` features of python!

# QuickStart:

### Maven

Add `kwargs-for-kotlin` to your dependencies in POM file:

(pom.xml):
```xml
<dependency>
    <groupId>io.github.psunset</groupId>
    <artifactId>kwargs-for-kotlin</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle

Add `kwargs-for-kotlin` to your dependencies in build file:

Groovy (build.gradle): 

```groovy
dependencies {
    implementation 'io.github.psunset:kwargs-for-kotlin:1.0.1'
}
```

Kotlin (build.gradle.kts):

```kotlin
dependencies {
    implementation("io.github.psunset:kwargs-for-kotlin:1.0.1")
}
```

Then build your project:

```
./gradlew build
```

### JAR File
Directly download the kwargs-for-kotlin-x.y.z.jar file in [release](https://github.com/pSUNSET/KWArgsForKotlin/releases).
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
    return result

sum_result = f(*range(1, 10), 15, 20, method='add')
print(sum_result) # Result: 80

mul_result = f(*range(1, 5), method='mul')
print(mul_result) # Result: 24
```

Now you do the same thing in kotlin by:

```kotlin
fun f(args: Iterable<Int>, kwargs: KWArgs): Int {
    val method = kwargs / Triple("method", "add", String::class)
    return when (method) {
        "add" -> args.sum()
        "mul" -> args.prod() // A feature in this library
        else -> Int.MAX_VALUE // Invalid method in kwargs
    }
}

val sum_result = f(
    listOf(*(1..<10).toList().toTypedArray(), 15, 20), kwargsOf(
        "method" to "add"
    )
)
println(sum_result) // Result: 80

val mul_result = f(
    listOf(*(1..<5).toList().toTypedArray()), kwargsOf(
        "method" to "mul"
    )
)
println(mul_result) // Result: 24
```
