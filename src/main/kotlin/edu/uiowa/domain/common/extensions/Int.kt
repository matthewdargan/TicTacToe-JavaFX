package edu.uiowa.domain.common.extensions

import java.util.concurrent.ThreadLocalRandom

/**
 * Gets a random number based on a lower and upper limit.
 *
 * @param lower
 *              the lower bound for choosing the random number
 * @param upper
 *              the upper bound for choosing the random number
 * @return a random number
 */
fun Int.Companion.random(lower: Int, upper: Int) = ThreadLocalRandom.current().nextInt(lower, upper + 1)