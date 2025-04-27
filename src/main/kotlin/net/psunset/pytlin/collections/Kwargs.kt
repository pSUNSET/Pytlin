@file:JvmName("KwargsUtil")

package net.psunset.pytlin.collections

import net.psunset.pytlin.lang.not

/**
 * A map whose keys are [String] and whose values are [Any]?.
 *
 * This is an implementation of python's kwargs.
 * In python, the feature of keyword arguments, as known as kwargs, is a very convenient way to store data.
 * Though can't directly use that as parameters in a function.
 * It can still be used to save some data with same format.
 */
interface Kwargs : Map<String, Any?> {
    /**
     * Creates a new read-only kwargs by replacing or adding an entry to this kwargs from a given key-value [pair].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     * The [pair] is iterated in the end if it has a unique key.
     *
     * @see Map.plus
     */
    operator fun plus(pair: Pair<String, Any?>): Kwargs =
        if (this.isEmpty()) kwargsOf(pair) else MutableKwargs(this).apply { plusAssign(pair) }

    /**
     * Creates a new read-only kwargs by replacing or adding entries to this kwargs from a given collection of key-value [pairs].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] collection.
     *
     * @see Map.plus
     */
    operator fun plus(pairs: Iterable<Pair<String, Any?>>): Kwargs = MutableKwargs(this).apply { plusAssign(pairs) }

    /**
     * Creates a new read-only kwargs by replacing or adding entries to this kwargs from a given array of key-value [pairs].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] array.
     *
     * @see Map.plus
     */
    operator fun plus(pairs: Array<out Pair<String, Any?>>): Kwargs = MutableKwargs(this).apply { plusAssign(pairs) }

    /**
     * Creates a new read-only kwargs by replacing or adding entries to this kwargs from a given sequence of key-value [pairs].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     * Those [pairs] with unique keys are iterated in the end in the order of [pairs] sequence.
     *
     * @see Map.plus
     */
    operator fun plus(pairs: Sequence<Pair<String, Any?>>): Kwargs = MutableKwargs(this).apply { plusAssign(pairs) }

    /**
     * Creates a new read-only kwargs by replacing or adding entries to this kwargs from another [map].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     * Those entries of another [map] that are missing in this kwargs are iterated in the end in the order of that [map].
     *
     * @param map A [Map] or a [Kwargs] to add to this kwargs.
     *
     * @see Map.plus
     */
    operator fun plus(other: Map<String, Any?>): Kwargs = MutableKwargs(this).apply { plusAssign(other) }

    /**
     * Returns a kwargs containing all entries of the original kwargs except the entry with the given [key].
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     *
     * @see Map.minus
     */
    operator fun minus(key: String): Kwargs = MutableKwargs(this).apply { minusAssign(key) }

    /**
     * Returns a kwargs containing all entries of the original kwargs except those entries
     * the keys of which are contained in the given [keys] collection.
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     *
     * @see Map.minus
     */
    operator fun minus(keys: Iterable<String>): Kwargs = MutableKwargs(this).apply { minusAssign(keys) }

    /**
     * Returns a kwargs containing all entries of the original kwargs except those entries
     * the keys of which are contained in the given [keys] array.
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     *
     * @see Map.minus
     */
    operator fun minus(keys: Array<out String>): Kwargs = MutableKwargs(this).apply { minusAssign(keys) }

    /**
     * Returns a kwargs containing all entries of the original kwargs except those entries
     * the keys of which are contained in the given [keys] sequence.
     *
     * The returned kwargs preserves the entry iteration order of the original kwargs.
     *
     * @see Map.minus
     */
    operator fun minus(keys: Sequence<String>): Kwargs = MutableKwargs(this).apply { minusAssign(keys) }
}

/**
 * Returns an empty new [Kwargs].
 */
inline fun kwargsOf(): Kwargs = MutableKwargs()

/**
 * Returns a new [Kwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun kwargsOf(vararg pairs: Pair<String, Any?>): Kwargs =
    if (!pairs) MutableKwargs() else pairs.toMap(MutableKwargs(mapCapacity(pairs.size)))

/**
 * Returns a new [Kwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * All the keys will be cast into [String].
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
@JvmName("kwargsOfByStrKeys")
fun <K : Any, V : Any?> kwargsOf(vararg pairs: Pair<K, V>): Kwargs =
    if (!pairs) MutableKwargs() else pairs.associateTo(MutableKwargs(mapCapacity(pairs.size))) { (key, value) ->
        key.toString().to(value)
    }

/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<out String, Any?>.toKwargs(): Kwargs = MutableKwargs(this)

/**
 * Returns a new [Kwargs] containing all key-value pairs from the original map.
 * All the keys will be cast into [String].
 *
 * The returned map preserves the entry iteration order of the original map.
 */
@JvmName("toKwargsByStrKeys")
fun <K : Any, V : Any?> Map<out K, V>.toKwargs(): Kwargs =
    MutableKwargs(this.mapKeys { it.toString() })

/**
 * A [LinkedHashMap] that implements [Kwargs].
 * @see Kwargs
 */
class MutableKwargs : LinkedHashMap<String, Any?>, Kwargs {
    constructor(initialCapacity: Int, loadFactor: Float) : super(initialCapacity, loadFactor)
    constructor(initialCapacity: Int) : super(initialCapacity)
    constructor() : super()
    constructor(m: Map<out String, Any?>) : super(m)
}

/**
 * Returns an empty new [MutableKwargs].
 */
inline fun mutableKwargsOf(): MutableKwargs = MutableKwargs()

/**
 * Returns a new [MutableKwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
fun mutableKwargsOf(vararg pairs: Pair<String, Any?>): MutableKwargs =
    if (!pairs) MutableKwargs() else pairs.toMap(MutableKwargs(mapCapacity(pairs.size)))

/**
 * Returns a new [MutableKwargs] with the specified contents, given as a list of pairs
 * where the first component is the key and the second is the value.
 *
 * All the keys will be cast into [String].
 * If multiple pairs have the same key, the resulting map will contain the value from the last of those pairs.
 *
 * Entries of the map are iterated in the order they were specified.
 */
@JvmName("mutableKwargsOfByStrKeys")
fun <K : Any, V : Any?> mutableKwargsOf(vararg pairs: Pair<K, V>): MutableKwargs =
    if (!pairs) MutableKwargs() else pairs.associateTo(MutableKwargs(mapCapacity(pairs.size))) { (key, value) ->
        key.toString().to(value)
    }

/**
 * Returns a new [MutableKwargs] containing all key-value pairs from the original map.
 *
 * The returned map preserves the entry iteration order of the original map.
 */
fun Map<out String, Any?>.toMutableKwargs(): MutableKwargs = MutableKwargs(this)

/**
 * Returns a new [MutableKwargs] containing all key-value pairs from the original map.
 * All the keys will be cast into [String].
 *
 * The returned map preserves the entry iteration order of the original map.
 */
@JvmName("toMutableKwargsByStrKeys")
fun <K : Any, V : Any?> Map<out K, V>.toMutableKwargs(): MutableKwargs =
    MutableKwargs(this.mapKeys { it.toString() })